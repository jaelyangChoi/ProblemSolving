package BFS;
//이모티콘

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BJ1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //정점: (화면 이모티콘 수, 클립보드 이모티콘 수)
        boolean[][] check = new boolean[1001][1001];
        int[][] dist = new int[1001][1001];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);//s:화면
        q.offer(0);//c: 클립보드
        check[1][0] = true;
        while (!q.isEmpty()) {
            int s = q.poll();
            int c = q.poll();
            //클립보드에 복사
            if (s <= 1000 && !check[s][s + c]) {
                q.offer(s);
                q.offer(s);
                check[s][s] = true;
                dist[s][s] = dist[s][c] + 1;
            }
            //화면에 복사
            if (s + c <= 1000 && !check[s + c][c]) {
                q.offer(s + c);
                q.offer(c);
                check[s + c][c] = true;
                dist[s + c][c] = dist[s][c] + 1;
            }
            //화면에서 하나 삭제
            if (s - 1 >= 0 && !check[s - 1][c]) {
                q.offer(s - 1);
                q.offer(c);
                check[s - 1][c] = true;
                dist[s - 1][c] = dist[s][c] + 1;
            }
        }
        //정답 찾기. 화면 갯수가 n일 때 클립보드 상태는 0~1000까지 다양.
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= 1000; i++)
            if (check[n][i] && min > dist[n][i])
                min = dist[n][i];
        System.out.println(min);
    }
}
