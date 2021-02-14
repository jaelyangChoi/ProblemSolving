package _rechallenge;
//N-Queen

import java.util.Scanner;

public class BJ9663 {
    static int n;
    static boolean[] check_col;
    static boolean[] check_r;
    static boolean[] check_l;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        check_col = new boolean[n];
        check_r = new boolean[2 * n - 1];
        check_l = new boolean[2 * n - 1];
        System.out.println(go(0));
    }

    private static int go(int row) {
        if (row == n) return 1;
        int ans = 0;
        for (int col = 0; col < n; col++) {
            if (possible(row, col)) { //백트래킹
                check_col[col] = check_r[col - row + (n - 1)] = check_l[row + col] = true;
                ans += go(row + 1);
                check_col[col] = check_r[col - row + (n - 1)] = check_l[row + col] = false;
            }
        }
        return ans;
    }

    private static boolean possible(int row, int col) {
        if (check_col[col]) return false;
        if (check_r[col - row + (n - 1)]) return false;
        if (check_l[row + col]) return false;
        return true;
    }
}
