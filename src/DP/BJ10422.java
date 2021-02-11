package DP;
//괄호

//왜 틀렸는지 모르겠다..
/*틀린 이유: 예외를 생각하지 못했다.
    1. 직전 상황의 모든 케이스를 정의해 일반화했다.
    2. 예외 상황을 발견하지 못 했다. 예외는 찾기 힘들다.
    3. 논리가 빈약했기 때문에 예외를 찾아야 하는 것이다. 논리가 명백하면 예외를 찾을 필요가 없다.
*/

import java.util.Arrays;
import java.util.Scanner;

public class BJ10422 {
    static final long mod = 1000000007L;
    static long[] d = new long[5001]; //필요한 데이터만 연속해서 쓸 수 있다!

    static long f(int l) {
        if (l == 0) return 1;
        if (d[l] == -1) {
            d[l] = 0;
            for (int i = 2; i <= l; i += 2) {
                d[l] += f(i - 2) * f(l - i);
                d[l] %= mod;
            }
        }
        return d[l];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Arrays.fill(d, -1);
        int t = sc.nextInt();
        while (t-- > 0) {
            int l = sc.nextInt();
            if (l % 2 == 0) System.out.println(f(l));
            else System.out.println(0);
        }
    }
}
