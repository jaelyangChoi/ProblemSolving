package DP;
//동전 1
import java.util.Scanner;

public class Bj2293 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] v = new int[n];
        for (int i = 0; i < n; i++)
            v[i] = sc.nextInt();

        int[] d = new int[k + 1];//d[i]=i를 만드는 방법의 수
        d[0] = 1;//그래야 d[1]이 0이되든 1이되든 한다.

        //중복x->순서
        for (int j = 0; j < n; j++)
            for (int i = 1; i <= k; i++)
                if (i - v[j] >= 0) d[i] += d[i - v[j]];

        System.out.println(d[k]);
    }
}
