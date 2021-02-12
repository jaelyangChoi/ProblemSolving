package BFS;
//탈옥 - 접근법 외울것
//틀린 이유: if (d[0][i][j] == -1 || d[1][i][j] == -1 || d[2][i][j] == -1) continue; 빼먹어서. arr[i][j]=='*'이 아니더라도 도달할 수 없는 경우..
//형식을 파괴하니까 어렵고 꼬이는 것이었다.
//BFS는 시작점에서 여러 도착점들로의 최소 비용을 구하는 것.
//[시작점1->중간지점 + 시작점2->중간지점 + 도착점->중간지점] 형태로 기존 틀을 활용한다.

/*
* bfs 응용하기: 기본 틀을 활용.
  bfs는 시작점 -> 도착점들로의 최단 거리를 구하는 것. => 시작점은 고정해야 한다!!!
  ->시작점을 고정시킨 채로 bfs를 진행해야 하니, 여러 기준(시작점)에서 최소 비용을 구할 것!
  ->시작점이 여러 개인 경우 -> 중간 지점을 모두의 도착점으로
* 비용 하나만 구하는게 아니라, 시작점에서 모든 노드로의 최소 비용을 기록하기도 한다.
* 벽을 만날때까지 계속 이동하게 아니라.. => 가중치가 0,1 인 문제.
*/

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class BJ9376 {
    static final int[] di = {0, 0, -1, 1};
    static final int[] dj = {1, -1, 0, 0};

    static class Position {
        int i, j;

        Position(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        //최소 문 따기. # 비용 1, . 비용 0 -> 가중치가 다른 bfs ->deque
        //시작점 아무대나, 도착점 $ -> 시작점을 $로 고정. 도착점은 임의로 (0,0)
        //시작점 2개 -> 만나는 중간점이 있을 것.
        //=> $1->X, $2->X, (0,0)->X : bfs로 각각 시작점에서의 비용 구하고 최소값을 찾는다.
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            char[][] arr = new char[h + 2][w + 2]; //padding
            Position[] s = new Position[3];
            s[0] = new Position(0, 0);
            for (int i = 1; i <= h; i++) {
                arr[i][0] = arr[i][w + 1] = '.';
                String temp = sc.next();
                for (int j = 1; j <= w; j++) {
                    arr[i][j] = temp.charAt(j - 1);
                    if (arr[i][j] == '$') {
                        arr[i][j] = '.';
                        if (s[1] == null) s[1] = new Position(i, j);
                        else s[2] = new Position(i, j);
                    }
                }
            }
            for (int j = 0; j <= w + 1; j++)
                arr[0][j] = arr[h + 1][j] = '.';

            //지점을 시작점으로 모든 지점의 비용 계산
            int[][][] d = new int[3][h + 2][w + 2];
            for (int k = 0; k < 3; k++)
                d[k] = bfs(arr, s[k]);

            //최소값을 갖는 중간 지점 결정
            int ans = w * h; //max
            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    if (arr[i][j] == '*') continue;
                    /*답보고 해결했다. 무조건 코드 줄일게 아니라 중복계산 하더라도 꼼꼼하게 배제하자..*/
                    //arr[i][j]=='*'이 아니더라도 도달할 수 없는 경우.. #으로 둘러쌓인 곳이라던가..
                    if (d[0][i][j] == -1 || d[1][i][j] == -1 || d[2][i][j] == -1) continue; //이것 때문에 틀림
                    int mid = 0;
                    for (int k = 0; k < 3; k++)
                        mid += d[k][i][j];
                    if (arr[i][j] == '#') mid -= 2; //중복 계산 제거
                    ans = Math.min(ans, mid);
                }
            }
            System.out.println(ans);
        }
    }

    private static int[][] bfs(char[][] arr, Position start) {
        ArrayDeque<Position> deq = new ArrayDeque<>();
        int h = arr.length, w = arr[0].length;
        int[][] d = new int[h][w];
        for (int[] a : d)
            Arrays.fill(a, -1);
        d[start.i][start.j] = 0;
        deq.offerFirst(start);
        while (!deq.isEmpty()) {
            Position pos = deq.pollFirst();
            int i = pos.i;
            int j = pos.j;
            for (int k = 0; k < 4; k++) {
                int ni = i + di[k];
                int nj = j + dj[k];
                if (ni < 0 || ni >= h || nj < 0 || nj >= w) continue;
                if (d[ni][nj] != -1) continue; //이미 방문
                if (arr[ni][nj] == '*') continue;
                if (arr[ni][nj] == '.') {//가중치 0
                    d[ni][nj] = d[i][j];
                    deq.offerFirst(new Position(ni, nj));
                } else if (arr[ni][nj] == '#') {//가중치 1
                    d[ni][nj] = d[i][j] + 1;
                    deq.offerLast(new Position(ni, nj));
                }
            }
        }
        return d;
    }
}
