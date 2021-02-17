package _rechallenge;
// 괄호 https://www.acmicpc.net/problem/10422
// bottom-up: 1900ms. 불필요한 연산, d[5000]를 구할 때 d[4999]는 필요도 없는데 연산
// top-up: 불필요한 연산x, static으로 재활용.

import java.util.Arrays;
import java.util.Scanner;

public class BJ10422 {
    static final long MOD = 1000000007L; //long과 연산하니까
    static long[] d = new long[5001]; //static으로 재활용

    static long f(int l) {
        if (l == 0) return 1;
        if (d[l] == -1) {
            d[l] = 0;
            for (int k = 2; k <= l; k += 2) { //홀수는 0이므로
                d[l] += f(k - 2) * f(l - k);
                d[l] %= MOD;
            }
        }
        return d[l];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        Arrays.fill(d, -1);
        while (t-- > 0) {
            int l = sc.nextInt();
            if (l % 2 == 0) System.out.println(f(l));
            else System.out.println(0);
        }
    }
}
