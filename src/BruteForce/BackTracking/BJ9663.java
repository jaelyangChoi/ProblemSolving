package BruteForce.BackTracking;
//N-Queen
//다시 풀어서 중복 계산을 줄이는 아이디어 연습하기
//O(1) 시간만에 한번에 조회할 순 없을까? 이 라인에 있는지 없는지.
import java.util.Scanner;

public class BJ9663 {
    static int n;
    static boolean[] check_col;
    static boolean[] check_lu;
    static boolean[] check_ru;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        check_col = new boolean[n];
        check_lu = new boolean[2 * n - 1];
        check_ru = new boolean[2 * n - 1];
        System.out.println(go(0));
    }

    private static int go(int row) {
        if (row == n) return 1;
        int cnt = 0;
        for (int col = 0; col < n; col++) {
            if (!check(row, col)) {
                check_col[col] = true;
                check_lu[col - row + n - 1] = true;
                check_ru[row + col] = true;

                cnt += go(row + 1);

                check_col[col] = false;
                check_lu[col - row + n - 1] = false;
                check_ru[row + col] = false;
            }
        }
        return cnt;
    }

    private static boolean check(int row, int col) {
        return check_col[col] || check_lu[col - row + n - 1] || check_ru[row + col];
    }
}
