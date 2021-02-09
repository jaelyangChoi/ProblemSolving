package DP.Knapsack;
//기타리스트
//0,1을 넣으므로 0,1 knapsack
//skill: 2차원 확산표 그리기!
import java.util.Scanner;

public class BJ1495 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int m = sc.nextInt();
        int[] v = new int[n + 1];
        for (int i = 1; i <= n; i++)
            v[i] = sc.nextInt();
        boolean[][] d = new boolean[n + 1][m + 1]; //d[i][j]: i번째 곡, 볼륨 j가 가능한가
        d[0][s] = true;
        for (int i = 1; i <= n; i++)
            for (int j = 0; j <= m; j++)
                if (d[i - 1][j]) { // 확산
                    if (j + v[i] <= m) d[i][j + v[i]] = true;
                    if (j - v[i] >= 0) d[i][j - v[i]] = true;
                }
        int ans = -1;
        for (int j = 0; j <= m; j++)
            if (d[n][j]) ans = j;
        System.out.println(ans);
    }
}
