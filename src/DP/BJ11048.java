package DP;
//이동하기

import java.util.Scanner;

public class BJ11048 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) //i-1,j-1 범위 검사를 하지 않기 위해!
            for (int j = 1; j <= m; j++)
                arr[i][j] = sc.nextInt();
        int[][] d = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]) + arr[i][j];
        System.out.println(d[n][m]);
    }
}
