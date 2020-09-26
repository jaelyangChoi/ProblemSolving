package DP;

import java.util.Scanner;

//가장 긴 증가하는 수열 LIS
public class Q0 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        //d[i]는 i 포함 가장 긴 수열. d[~i-1]까지 중 작은 것 업어+1
        int[] d = new int[n];
        //bottom-up 방식
        for (int i = 0; i < n; i++) {
            d[i] = 1;
            for (int j = i - 1; j >= 0; j--)
                if (arr[j] < arr[i] && d[i] < d[j] + 1)
                    d[i] = d[j] + 1;
        }
        //d[i] 중 답을 구함
        int max = d[0];
        for (int i = 1; i < n; i++)
            if (max < d[i]) max = d[i];
        System.out.println(max);
    }
}
