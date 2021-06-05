package DFS;

import java.util.Scanner;

/* 최적화할 것
1. 문자열은 객체다. 매번 새로 생성하는 것보다 char 배열 원소를 바꾸자
2. 굳이 조건 검사 안해도 되는 부분들
 */
public class BJ12969 {
    static char[] ans;
    static boolean[][][][] check;
    static int n, k;

    static boolean dfs(int i, int a, int b, int pair) {
        if (i == n)
            return pair == k;
        if (pair > k || check[i][a][b][pair])
            return false;
        check[i][a][b][pair] = true;

        // A 추가
        ans[i] = 'A';
        if (dfs(i + 1, a + 1, b, pair)) return true;
        // B 추가
        ans[i] = 'B';
        if (dfs(i + 1, a, b + 1, pair + a)) return true;
        // C 추가
        ans[i] = 'C';
        if (dfs(i + 1, a, b, pair + a + b)) return true;

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        check = new boolean[n + 1][n + 1][n + 1][k + 1];
        ans = new char[n];
        if (dfs(0, 0, 0, 0))
            System.out.println(new String(ans));
        else
            System.out.println(-1);
    }
}
