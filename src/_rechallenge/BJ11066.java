package _rechallenge;
//파일 합치기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//직전 상황만. 점화식을 믿어라.
//일반화
public class BJ11066 {
    static int[] arr;
    static int[][] d; //d[i][j] = i~j 합치는 최소 비용. static으로 하면 남아있다!

    static int f(int s, int e) {
        if (s == e) return 0;
        if (d[s][e] == 0) {
            d[s][e] = Integer.MAX_VALUE;
            int sum = 0;
            for (int i = s; i <= e; i++)
                sum += arr[i];
            for (int k = s; k <= e - 1; k++)
                d[s][e] = Math.min(d[s][e], f(s, k) + f(k + 1, e) + sum);
        }
        return d[s][e];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n];
            int i = 0;
            for (String s : br.readLine().split(" "))
                arr[i++] = Integer.parseInt(s);
            d = new int[n][n];
            System.out.println(f(0, n - 1)); //for문을 어떻게 돌려야할지 애매 => 재귀!
        }
    }
}
