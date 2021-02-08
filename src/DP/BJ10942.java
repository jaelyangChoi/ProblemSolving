package DP;
//펠린드롬?
//BufferedIO를 안 써서 시간 초과가 난 것.
//top-down, bottom-up 둘 다 풀 줄 알아야 한다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//일반적인 방식으로 배열을 채우지 않기 때문에 재귀 사용
/*
bottom-up 방식으로 하려면 길이가 1일때, 2일때, 3일때 차례대로 수행하면 된다.
    boolean[][] d = new boolean[n][n];
        for (int i = 0; i < n; i++) //길이 1
            d[i][i] = true;
        for (int i = 0; i < n - 1; i++) //길이 2
            if (a[i] == a[i + 1]) d[i][i + 1] = true;


        for (int k = 2; k < n; k++) //길이 k
            for (int i = 0; i < n - k; i++) {
                int j = i + k;
                if (a[i] == a[j] && d[i + 1][j - 1]) d[i][j] = true;
            }
*/
public class BJ10942 {
    static int[] arr;
    static int[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        int i = 1;
        for (String s : br.readLine().split(" "))
            arr[i++] = Integer.parseInt(s);
        int m = Integer.parseInt(br.readLine());

        d = new int[n + 1][n + 1]; //d[s][e]=d[s+1]d[s-1]&&arr[s]==arr[e]
        for (i = 1; i <= n; i++)
            Arrays.fill(d[i], -1);

        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            String[] idx = br.readLine().split(" ");
            sb.append(f(Integer.parseInt(idx[0]), Integer.parseInt(idx[1]))).append('\n');
        }
        System.out.print(sb);
    }

    private static int f(int s, int e) {
        if (s >= e) return 1; //base case
        if (d[s][e] == -1)
            d[s][e] = arr[s] == arr[e] ? f(s + 1, e - 1) : 0;
        return d[s][e];
    }
}
