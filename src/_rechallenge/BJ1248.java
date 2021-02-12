package _rechallenge;
//맞춰봐 https://www.acmicpc.net/problem/1248
/*
    배열로 표현하면 접근하기 쉬울 수 있다.
    계산을 효율적으로 하기 위해 고민해본다. tip: 방향을 바꿔 생각해보기
*/
import java.util.Scanner;

public class BJ1248 {
    static int n;
    static int[][] res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] ans = new int[n];
        res = new int[n][n];
        String temp = sc.next();
        int idx = 0;
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++) {
                char c = temp.charAt(idx++);
                if (c == '+') res[i][j] = 1;
                else if (c == '-') res[i][j] = -1;
                else res[i][j] = 0;
            }
        //-10~10까지의 수를 채워넣어 조건에 부합하는지 확인
        go(ans, 0);
        for (int i = 0; i < n; i++)
            System.out.print(ans[i] + " ");
    }

    private static boolean go(int[] ans, int idx) {
        if (idx == n) return true;
        //idx번째 숫자 선택
        int sign = res[idx][idx];
        if (sign == 0) {
            ans[idx] = 0;
            //테스트 통과 시 다음 인덱스 선택으로
            return valid(ans, idx) && go(ans, idx + 1);
        } else {
            for (int i = 1; i <= 10; i++) {
                ans[idx] = i * sign;
                if (valid(ans, idx) && go(ans, idx + 1)) return true;
            }
        }
        return false;
    }

    //테스트 for 백트래킹
    private static boolean valid(int[] ans, int idx) {
        int sum = 0;
        for (int i = idx; i >= 0; i--) {
            sum += ans[i];
            int sign = res[i][idx];
            if (sign == 0 && sum != 0) return false;
            else if (sign > 0 && sum <= 0) return false;
            else if (sign < 0 && sum >= 0) return false;
        }
        return true;
    }
}
