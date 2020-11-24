package DP;
//123 더하기
import java.util.Scanner;

public class BJ9095 {
    /* 틀린 이유: 전제가 틀림. d[1]=1이라고 봤다. 그럼 d[2]=d[0](+2)는 0이된다.
    static int f(int[] d, int n) {
        if (n <= 0) return 0;
        if (d[n] == 0)
            for (int i = 1; i <= 3; i++)
                d[n] += f(d,n - i);
        return d[n];
    }*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int limit = 11;
        int[] d = new int[limit + 1];//d[i]: i를 만드는 방법의 갯수 d[i]=d[i-1]+d[i-2]+d[i-3]
        d[0] = 1;//0 만드는 방법을 하나로 본다.. 아무런 수를 사용하지 않는 방법..-> 1 만드는 방법은 d[0]+1
        for (int i = 1; i <= limit; i++)
            for (int k = 1; k <= 3; k++)
                if (i - k >= 0) d[i] += d[i - k];//d[3]:d[2]에다가 1을 붙인다+d[1]에다가 2를 붙인다

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            System.out.println(d[n]);
        }
    }
}
