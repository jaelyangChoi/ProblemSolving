package DP;
//점프

import java.util.Scanner;

public class BJ1890_topdown {
    static long f(long[][] d, int[][] arr, int i, int j) {
        if (d[i][j] == 0) {//메모 되어 있지 않다면 재귀로 구함.
            for (int k = 0; k < i; k++)  //아래쪽
                if (arr[k][j] != 0 && k + arr[k][j] == i)
                    d[i][j] += f(d, arr, k, j);
            for (int k = 0; k < j; k++)  //오른쪽
                if (arr[i][k] != 0 && k + arr[i][k] == j)
                    d[i][j] += f(d, arr, i, k);
        }
        return d[i][j];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = sc.nextInt();

        long[][] d = new long[n][n];//d[i][j]=(i,j)에 올 수 있는 경로의 갯수
        d[0][0] = 1;
        System.out.println(f(d, arr, n - 1, n - 1));
    }
}
