package DP;
//점프

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//틀에서 벗어나기: 꼭 d[i][j]가 왼쪽일 필요는 없다!
public class BJ2_ver2 {
    static long[][] d;//경로가 너무 클 수 있으므로 long..
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++)
                board[i][j] = Integer.parseInt(temp[j]);
        }
        //d[i][j]: (0,0)->(i,j)로 가는 경로 갯수
        d = new long[n][n];
        d[0][0] = 1;
        //작은 문제로 큰 문제 풀기: d[i][j]->d[i+board[i][j]] / d[i][j]->d[i][j+board[j]]
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) continue;
                //한 점에서 이동할 수 있는 경우 2가지 모두 이동
                if (j + board[i][j] < n) d[i][j + board[i][j]] += d[i][j];//꼭 d[i][j]가 왼쪽일 필요는 없다!
                if (i + board[i][j] < n) d[i + board[i][j]][j] += d[i][j];
            }
        System.out.println(d[n - 1][n - 1]);
    }
}
