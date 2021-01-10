package DP;
//점프 - topdown

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1890 {
    static private long[][] d;//d[i][j]: (i,j)에 도달할 수 있는 경로의 수
    static private int[][] arr;

    public static long f(int i, int j) {
        if (i == 0 && j == 0) return 1;
        if (d[i][j] == 0) {//메모 되어 있지 않다면 재귀로 구함.
            //아래 방향
            for (int k = 1; k <= i; k++)
                if (arr[i - k][j] == k)
                    d[i][j] += f(i - k, j);
            //위 방향
            for (int k = 1; k <= j; k++)
                if (arr[i][j - k] == k)
                    d[i][j] += f(i, j - k);
        }
        return d[i][j];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        d = new long[n][n];
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            int j = 0;
            for (String s : br.readLine().split(" "))
                arr[i][j++] = Integer.parseInt(s);
        }

        System.out.println(f(n - 1, n - 1));
    }
}
