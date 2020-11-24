package DP;
//123 더하기 4

import java.util.Scanner;

//중복x -> 임의로 순서를 정한다
public class BJ15989 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int limit = 10000;
        int[] d = new int[limit + 1];//d[i]: i를 만드는 방법의 갯수 d[i]=d[i-1]+d[i-2]+d[i-3]
        d[0] = 1;//0 만드는 방법을 하나로 본다.. 아무런 수를 사용하지 않는 방법..-> 1 만드는 방법은 d[0]+1
        for (int k = 1; k <= 3; k++)//1로 끝나는 걸로 채우고, 2로 끝나는 걸로 채우고,,,
            for (int i = 1; i <= limit; i++)
                if (i - k >= 0) d[i] += d[i - k];//d[3]:d[2]에다가 1을 붙인다+d[1]에다가 2를 붙인다

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            System.out.println(d[n]);
        }
    }
}
