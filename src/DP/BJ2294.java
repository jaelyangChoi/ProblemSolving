package DP;
//동전 2

import java.util.Arrays;
import java.util.Scanner;

public class BJ2294 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] v = new int[n];
        for (int i = 0; i < n; i++)
            v[i] = sc.nextInt();

        int[] c = new int[k+1];//c[i]: i를 만들 때 사용한 동전 갯수
        Arrays.fill(c,-1);
        c[0]=0; //->c[i]=c[0]+{i} 가 1로 표현됨!
        for (int i = n - 1; i >= 0; i--)//큰 동전부터
            for (int j = 1; j <= k; j++)
                if (j - v[i] >= 0 && c[j - v[i]] != -1)
                    if (c[j] == -1 || c[j] > c[j - v[i]] + 1)
                        c[j] = c[j - v[i]] + 1;

        System.out.println(c[k]);
    }
}
