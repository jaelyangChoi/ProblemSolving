package BruteForce.BackTracking;
//부등호 - 백트래킹 ver

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//인자를 간단하게 전달하자. (static 어느정도 써도 된다.)
//문자<->숫자<->문자열 변경 자유롭게 해라.. 자잘한 연산에 기겁하지마.
//답을 반환하기 좋은 형태로 담아라..!
public class BJ2529 {
    static int k;
    static char[] op;
    static ArrayList<String> ans = new ArrayList<>();
    static boolean[] check = new boolean[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        op = new char[k];
        for (int i = 0; i < k; i++)
            op[i] = sc.next().charAt(0);

        go(0, new StringBuilder());
        Collections.sort(ans);
        System.out.println(ans.get(ans.size() - 1));
        System.out.println(ans.get(0));
    }

    private static void go(int idx, StringBuilder sb) {
        if (idx == k + 1) {
            ans.add(sb.toString());
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (check[i]) continue;
            if (idx != 0 && !valid(i, sb.charAt(idx - 1) - '0', op[idx - 1])) continue; //백트래킹

            check[i] = true;
            go(idx + 1, sb.append(i));
            sb.delete(sb.length() - 1, sb.length());
            check[i] = false;
        }
    }

    private static boolean valid(int cur, int prev, char op) {
        if (op == '<') return prev < cur;
        return prev > cur;
    }
}