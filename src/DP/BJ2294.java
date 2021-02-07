package DP;
//동전 2

import java.util.Arrays;
import java.util.Scanner;

public class BJ2294 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int[] d = new int[k + 1]; //d[i]: i를 만드는데 필요한 최소 동전 수. d[i]=min(d[i-k])+1
        Arrays.fill(d, -1);
        d[0] = 0;
        for (int i = 1; i <= k; i++)
            for (int x : arr)
                if (i - x >= 0 && d[i - x] != -1) { //존재하지 않는 경우 제외
                    if (d[i] == -1 || d[i] > d[i - x] + 1) //최소 값으로 갱신
                        d[i] = d[i - x] + 1;
                }
        System.out.println(d[k]);
    }
}
