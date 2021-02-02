package BruteForce.BackTracking;
//스도쿠
/*check 배열 3형제의 힘!*/
/*배열에서 나누기는 row, 나머지는 col!*/

import java.util.Scanner;

//for문 연산 함께할 수 있으면 같이 처리해버리자. 간결해지게.
//복잡한 계산은 함수화
public class BJ2580 {
    static final int n = 9;

    static int square(int row, int col) {
        return 3 * (row / 3) + col / 3;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[n][n];
        boolean[][][] check = new boolean[3][n][n + 1];//row,col,square, n개, value:1~9
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] != 0) {
                    check[0][i][arr[i][j]] = true;
                    check[1][j][arr[i][j]] = true;
                    check[2][square(i, j)][arr[i][j]] = true;
                }
            }
        go(arr, check, 0); //0번째 칸부터 81개 훓겠다.
    }

    private static boolean go(int[][] arr, boolean[][][] check, int k) {
        if (k == n * n) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    System.out.print(arr[i][j] + " ");
                System.out.println();
            }
            return true;
        }
        int i = k / n;
        int j = k % n;
        if (arr[i][j] != 0) return go(arr, check, k + 1);
        else {
            for (int num = 1; num <= n; num++) {
                if (check[0][i][num] || check[1][j][num] || check[2][square(i, j)][num]) continue; //백트래킹
                check[0][i][num] = check[1][j][num] = check[2][square(i, j)][num] = true;
                arr[i][j] = num;
                if (go(arr, check, k + 1)) return true; //아니면 계속 진행
                arr[i][j] = 0;
                check[0][i][num] = check[1][j][num] = check[2][square(i, j)][num] = false;
            }
        }
        return false;
    }
}