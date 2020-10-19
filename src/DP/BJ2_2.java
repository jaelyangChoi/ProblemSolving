package DP;
//점프 - top-down ver
import java.util.Scanner;

public class BJ2_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = sc.nextInt();

        long[][] d = new long[n][n];
        d[0][0] = 1;
        System.out.println(f(d, a, n - 1, n - 1));
    }

    //d[i][j]를 return.
    private static long f(long[][] d, int[][] a, int i, int j) {
        if (i < 0 || j < 0) return 0;
        if (d[i][j] == 0) {//메모 되어 있지 않다면 재귀로 구함.
            for (int k = 0; k < i; k++) //아래쪽
                if (a[k][j] != 0 && k + a[k][j] == i) d[i][j] += f(d, a, k, j);
            for (int k = 0; k < j; k++) //오른쪽
                if (a[i][k] != 0 && k + a[i][k] == j) d[i][j] += f(d, a, i, k);
        }
        return d[i][j];
    }
}
