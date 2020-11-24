package DP;
//점프 O(N^2) 풀이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1890_bottomup {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            int j = 0;
            for (String s : br.readLine().split(" "))
                arr[i][j++] = Integer.parseInt(s);
        }

        long[][] d = new long[n][n]; //d[i][j]: (0,0)->(i,j)로 가는 경로 갯수
        d[0][0] = 1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (arr[i][j] != 0) {
                    if (i + arr[i][j] < n) d[i + arr[i][j]][j] += d[i][j]; //아래로 점프
                    if (j + arr[i][j] < n) d[i][j + arr[i][j]] += d[i][j];//오른쪽으로 점프
                }

        System.out.println(d[n - 1][n - 1]);
    }
}
