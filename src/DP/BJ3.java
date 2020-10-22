package DP;

import java.util.Arrays;
import java.util.Scanner;

//틀린 이유: 로직은 맞았으나, 출력문에 시간이 많이 걸린다는 것을 간과
public class BJ3 {
    static int[][] d;//arr[s~e]가 팰린드롬인가
    static int[] a;

    private static int f(int s, int e) {
        if (s >= e) return 1;
        if (d[s][e] != -1) return d[s][e];
        if (a[s] != a[e]) return d[s][e] = 0; //오호 이런 return도 가능하구나
        else return d[s][e] = f(s + 1, e - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        a = new int[n];
        d = new int[n][n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            Arrays.fill(d[i], -1);
        }
        int m = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            sb.append(f(s - 1, e - 1)).append("\n");
        }
        System.out.println(sb);
    }
}
