package etc;

import java.util.Scanner;

public class BJ12970 {
    static int n, k;
    static char[] ans;
    static boolean[][][] check;

    static boolean dfs(int i, int a, int pair) {
        if (i == n)
            return pair == k;
        if (pair > k || check[i][a][pair])
            return false;
        check[i][a][pair] = true;

        //A 추가
        ans[i] = 'A';
        if (dfs(i + 1, a + 1, pair)) return true;
        //B 추가
        ans[i] = 'B';
        return dfs(i + 1, a, pair + a);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        check = new boolean[n][n][k + 1];
        ans = new char[n];
        if (dfs(0, 0, 0))
            System.out.println(new String(ans));
        else
            System.out.println(-1);
    }
}
