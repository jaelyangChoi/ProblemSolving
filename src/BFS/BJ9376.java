package BFS;
//탈옥 다시 풀것. 어렵다

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/*
1. bfs는 가중치를 고려해야 한다. -> 가중치 2개 -> 단계별 큐 or 덱
2. bfs는 시작점, 도착점이 필요하다. 없으면 임의로 만든다. <- 패딩 기법 활용 가능
3. 시작점이 두 군데 -> 만나는 점을 다시 시작점으로 -> 도착점에서 만나는 점으로
 */

class Pair {
    int i, j;

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

public class BJ9376 {
    static final int[] di = {0, 0, 1, -1};
    static final int[] dj = {1, -1, 0, 0};

    static int[][] bfs(String[] a, int startI, int startJ) {
        int n = a.length;
        int m = a[0].length();
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], -1);
        ArrayDeque<Pair> deq = new ArrayDeque<>();
        deq.offer(new Pair(startI, startJ));
        dist[startI][startJ] = 0;
        while (!deq.isEmpty()) {
            Pair p = deq.pollFirst();
            int i = p.i;
            int j = p.j;
            for (int k = 0; k < 4; k++) {
                int ni = i + di[k];
                int nj = j + dj[k];
                if (ni < 0 || ni >= n || nj < 0 || nj >= m || dist[ni][nj] != -1) continue;
                if (a[ni].charAt(nj) == '*') continue;
                else if (a[ni].charAt(nj) == '#') {//가중치 1
                    deq.offerLast(new Pair(ni, nj));
                    dist[ni][nj] = dist[i][j] + 1;
                } else {//가중치 0
                    deq.offerFirst(new Pair(ni, nj));
                    dist[ni][nj] = dist[i][j];
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            sc.nextLine();// 숫자 입력 라인 개행문자 버림
            String[] a = new String[h + 2];//굳이 문자로 쪼갤 필요 없구나..
            for (int i = 1; i <= h; i++) {
                a[i] = sc.nextLine();
                a[i] = "." + a[i] + ".";
            }
            a[0] = a[h + 1] = "";//이거 안하면 null에 . 더하는 꼴
            for (int i = 0; i < w + 2; i++) {
                a[0] += ".";
                a[h + 1] += ".";
            }

            //(0,0)~교차점,$1~교차점,$2~교차점을 찾기 위해 모든 지점의 가중치 계산
            int[][] d0 = bfs(a, 0, 0);//(0,0)~모든 지점의 가중치 계산
            int[][] d1 = null, d2 = null;//$1,2로 부터 모든 지점의 가중치 계산
            //출발 위치인 $ 찾기
            for (int i = 1; i <= h; i++)
                for (int j = 1; j <= w; j++)
                    if (a[i].charAt(j) == '$') {
                        if (d1 == null) d1 = bfs(a, i, j);
                        else d2 = bfs(a, i, j);
                    }

            int answer = h * w;
            for (int i = 0; i < h + 2; i++)
                for (int j = 0; j < w + 2; j++) {
                    char c = a[i].charAt(j);
                    if (c == '*') continue;
                    int d = d0[i][j] + d1[i][j] + d2[i][j];
                    if (c == '#') d -= 2;//문 따기 2회 중복
                    if (answer > d) answer = d;//교차점은 비용이 최소인 곳
                }
            System.out.println(answer);
        }
    }
}
