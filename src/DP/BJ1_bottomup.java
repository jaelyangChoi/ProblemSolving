package DP;
//이동하기

import java.util.Scanner;

public class BJ1_bottomup {
    static int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] maze = new int[n + 1][m + 1];//i-1,j-1 범위 검사를 하지 않기 위해!
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                maze[i][j] = sc.nextInt();

        //(1,1)->(n,m)까지 이동하며 얻을 수 있는 최대 사탕 수
        //bottom-up 방식
        int[][] d = new int[n+1][m+1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                d[i][j] = max3(d[i - 1][j], d[i][j - 1], d[i - 1][j - 1]) + maze[i][j];
        System.out.println(d[n][m]);
    }
}
