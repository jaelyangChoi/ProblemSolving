package _rechallenge;
// 0과 1 https://www.acmicpc.net/problem/8111

//2^100은 너무 많다..
/*
    x%n==1이고 xy%n==1이면, 더 진행해봐야(0,1 을 붙여봐야) 의미 없다.
    나머지 0~n-1만 보면 된다!
    나머지에만 관심 있으므로, 진행과정에 나머지를 넣는다.
    - (x*10+y)%n = (x*10%n+y%n)%n 이므로
*/

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BJ8111 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            boolean[] check = new boolean[n];//나머지 0~n-1
            char[] how = new char[n];
            int[] from = new int[n];
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(1 % n);
            check[1 % n] = true;
            from[1 % n] = -1;
            how[1 % n] = '1';
            while (!q.isEmpty()) {
                int r = q.poll();
                if (r == 0) break;
                for (int k = 0; k <= 1; k++) {
                    int nr = (r * 10 + k) % n;
                    if (check[nr]) continue;
                    check[nr] = true;
                    from[nr] = r;
                    how[nr] = k == 0 ? '0' : '1';
                    q.offer(nr);
                }
            }
            if (!check[0]) System.out.println("BRAK");
            else {
                StringBuilder answer = new StringBuilder();
                for (int i = 0; i != -1; i = from[i])
                    answer.append(how[i]);
                System.out.println(answer.reverse());
            }
        }
    }
}
