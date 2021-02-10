package DP.Knapsack;
//1학년

import java.util.Scanner;

//확산표 & i번째까지~
public class BJ5557 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt() - 1;
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        int goal = sc.nextInt();

        long[][] d = new long[n][21]; //i번 째까지 고려했을 때, 중간 합 갯수
        d[0][a[0]] = 1;
        for (int i = 1; i < n; i++)
            for (int j = 0; j <= 20; j++) {
                if (j - a[i] >= 0) d[i][j] += d[i - 1][j - a[i]]; //0이 더해질 수 있음
                if (j + a[i] <= 20) d[i][j] += d[i - 1][j + a[i]];
            }

        System.out.println(d[n - 1][goal]);
    }
}
