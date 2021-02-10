package BFS;
//점프 게임
//BFS(단계별 탐색) - 최단 거리 유무=>할 수 있냐 없냐도 구할 수 있다.

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

//이동 - 2차원 배열을 활용할 수도 있다 / if else 문을 간결하게!
//d가 무엇일지 생각해보자. 직관적이지 않더라도 필요조건과 연관지으면 활용 가능
public class BJ15558 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String[] map = new String[2];
        map[0] = sc.next();
        map[1] = sc.next();

        final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, k}}; //2차원 배열로 넣으면 되는구나!
        int[][] d = new int[2][n];//방문한 초 <- 시작점 지워지는 것과 연관지을 수 있다!
        Queue<Integer> q = new ArrayDeque<>();

        Arrays.fill(d[0], -1);
        Arrays.fill(d[1], -1);
        d[0][0] = 0;
        q.offer(0);//line
        q.offer(0);//pos
        //q.offer(0);//start line. for문 아래(이동 후)에 매번 증가시키면 시간이 안 맞는다!

        //1초마다 모든 칸으로 이동해본다. => BFS
        while (!q.isEmpty()) {
            int line = q.poll();
            int pos = q.poll();
            for (int i = 0; i < 3; i++) {//이동
                int nline = (line + dirs[i][0]) % 2, npos = pos + dirs[i][1];
                if (npos < d[line][pos] + 1) continue;//start line보다 아래면 안된다
                if (npos >= n) {
                    System.out.println(1);
                    return;
                }
                if (map[nline].charAt(npos) == '0') continue;//0이면 못 간다.
                if (d[nline][npos] != -1) continue;
                q.offer(nline);
                q.offer(npos);
                d[nline][npos] = d[line][pos] + 1;//start line 갱신
            }
        }
        System.out.println(0);
    }
}
