package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

//미로 탐색
public class Q0 {
    static int n, m;
    static int[] di = {0, 0, -1, 1};
    static int[] dj = {-1, 1, 0, 0};
    static char[][] map;

    static int bfs() {
        int[][] d = new int[n][m];//거리 기록 & 방문 check
        Queue<Integer> q = new ArrayDeque<>();
        //시작점
        q.offer(0);
        q.offer(0);
        d[0][0] = 1;

        while (!q.isEmpty()) {
            int i = q.poll();
            int j = q.poll();
            if (i == n - 1 && j == m - 1) return d[i][j];
            for (int k = 0; k < 4; k++) {//4방향 이동
                int ni = i + di[k];
                int nj = j + dj[k];
                //이동 가능성 따져봄
                if (ni < 0 || nj < 0 || ni >= n || nj >= m) continue;
                if (map[ni][nj] == '0') continue;
                //최소비용이므로 재방문x
                if (d[ni][nj] != 0) continue;
                //이동 가능 -> 이동
                d[ni][nj] = d[i][j] + 1;
                q.offer(ni);
                q.offer(nj);
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            map[i] = s.toCharArray();
        }


        System.out.println(bfs());
    }
}
