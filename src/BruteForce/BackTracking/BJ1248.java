package BruteForce.BackTracking;
//맞춰봐
//못품..
//시간초과 -> 시간계산을 안하고 시작했다.

import java.util.Scanner;
//배열로 표현하면 접근하기 쉬울 수 있다.
//계산을 효율적으로 하기 위해 고민해본다. tip: 방향을 바꿔 생각해보기
//시간 초과 -> 백트래킹
//skill: A && B A가 false면 B는 시도도 안한다!

public class BJ1248 {
    static int n;
    static int[] ans;
    static int[][] sign;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        char[] s = sc.next().toCharArray();
        ans = new int[n];
        //skill: 배열로 표현하기 및 문자<->숫자
        sign = new int[n][n];
        int k = 0;
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++, k++)
                if (s[k] == '0') sign[i][j] = 0;
                else if (s[k] == '+') sign[i][j] = 1;
                else sign[i][j] = -1;

        go(0);
        for (int i = 0; i < n; i++)
            System.out.print(ans[i] + " ");
    }

    private static boolean go(int idx) {
        if (idx == n) return true; //return 값: 그저 종료시키기 위한 것.

        if (sign[idx][idx] == 0) {
            ans[idx] = 0;
            return check(idx) && go(idx + 1); //백트래킹. 아니면 더 진행 x
        } else {
            for (int i = 1; i <= 10; i++) { //건너 뛰기. 21->10개
                ans[idx] = i * sign[idx][idx]; //sign[idx][idx]는 idx번째 숫자의 부호
                if (check(idx) && go(idx + 1)) return true;
            }
        }
        return false;
    }

    private static boolean check(int idx) {
        int sum = 0;
        for (int i = idx; i >= 0; i--) {
            sum += ans[i];
            if (sign[i][idx] == 0 && sum != 0) return false;
            if (sign[i][idx] > 0 && sum <= 0) return false;
            if (sign[i][idx] < 0 && sum >= 0) return false;
        }
        return true;
    }
}

/*
static int n;
    static int[] ans;
    static char[] s;
    static char[] candidates;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = sc.next().toCharArray();
        ans = new int[n];
        candidates = new char[n];

        for (int i = 0, idx = 0; i < n; idx += n - i, i++)
            candidates[i] = s[idx];

        go(0);
        for (int i = 0; i < n; i++)
            System.out.print(ans[i] + " ");
    }

    //-10~10까지 21개의 정수 중 n개의 수 만들어 낸다.
    //조건에 맞는지 체크
    private static boolean go(int idx) {
        if (idx == n) {
            return valid();
        }

        //백트래킹이 아니라 건너뛰기
        int start = 0;
        if (candidates[idx] == '+') start = 1;
        if (candidates[idx] == '-') start = -10;
        for (int i = start; i <= 10; i++) {
            if (!check(idx, i)) continue; //백트래킹
            ans[idx] = i;
            if (go(idx + 1)) return true; //답 찾으면 조기 종료
        }

        return false;
    }

    private static boolean check(int idx, int num) {
        int t = idx;
        int i = idx;
        int start=0;
        while (t-- > 0) {
            if (s[i] == '+') {
                for(int k=start;k<=idx;k++);
            }
            i += t;
            start++;
        }
    }

    private static boolean valid() {
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += ans[j];
                if (s[idx] == '-' && sum >= 0) return false;
                if (s[idx] == '+' && sum <= 0) return false;
                if (s[idx++] == '0' && sum != 0) return false;
            }
        }
        return true;
    }
*/