package BruteForce.TwoPointers;
//소수의 연속합

import java.util.ArrayList;
import java.util.Scanner;

public class BJ1644 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> prime = getPrimeEra(n);

        //연속합
        int s = 0, e = 0, cnt = 0, len = prime.size();
        int sum = len == 0 ? 0 : prime.get(0);
        while (s <= e && e < len) {
            if (sum <= n) {
                if (sum == n) cnt++;
                if (++e < len)
                    sum += prime.get(e);
            } else
                sum -= prime.get(s++);
        }
        System.out.println(cnt);
    }

    //에라토스테네스의 체
    // O(N*loglogN)
    static ArrayList<Integer> getPrimeEra(int n) {
        boolean[] check = new boolean[n + 1]; //에라토스테네스의 체
        ArrayList<Integer> prime = new ArrayList<>();
        for (int num = 2; num <= n; num++) {
            if (!check[num]) {//소수의 배수는 소수가 아니다
                prime.add(num);
                //for (int i = num * num; i <= n; i += num) //num의 크기에 따라 2로 바꾼다. n이 백만일 경우 int 범위를 넘어감
                for (int i = num * 2; i <= n; i += num) //num*(num-1)까지는 이전 2~(num-1)의 배수로 지워졌다.
                    check[i] = true;
            }
        }
        return prime; //check를 반환하면 어떤 수 x가 소수인지 아닌지 O(1) 시간만에 알 수 있다.
    }
}
