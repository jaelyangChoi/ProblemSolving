package DP;
//1,2,3 더하기

import java.util.Scanner;

public class BJ9095 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] d = new int[11];
        d[0] = 1; // ->d[1]=1, 2로 2를 만드는 방법 수,,
        for (int i = 0; i < 11; i++)
            for (int k = 1; k <= 3; k++)
                if (i - k >= 0) d[i] += d[i - k]; //1,2,1 / 1,1,2 이렇게 순서가 다르면 다른 것 취급

        int t = sc.nextInt();
        while (t-- > 0)
            System.out.println(d[sc.nextInt()]);
    }
}
