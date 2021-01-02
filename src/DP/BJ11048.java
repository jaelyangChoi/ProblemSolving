package DP;
//이동하기

import java.util.Arrays;
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
/*
public class Topdown {
    static int[][] maze;
    static int[][] d;

    static int f(int i, int j) {
        if (i < 1 || j < 1) return 0;
        if (d[i][j] == -1) //메모이제이션
            d[i][j] = max3(f(i - 1, j), f(i, j - 1), f(i - 1, j - 1)) + maze[i][j];
        return d[i][j];
    }

    static int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        maze = new int[n + 1][m + 1];//i-1,j-1 범위 검사를 하지 않기 위해!
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                maze[i][j] = sc.nextInt();

        //(1,1)->(n,m)까지 이동하며 얻을 수 있는 최대 사탕 수
        // top-down 방식
        d = new int[n + 1][m + 1];
        for (int[] arr : d)
            Arrays.fill(arr, -1);//-1로 초기화
        System.out.println(f(n, m));
    }
}
*/