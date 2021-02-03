package DP;
//1,2,3 더하기 4
import java.util.Scanner;

/*중복 제거하는 법 -> 순서를 정한다*/
public class BJ15989_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        //사실 n이 10000까지이더라도 미리 구하는게 더 빠르다!

        while (t-- > 0) {
            int n = sc.nextInt();
            int[] d = new int[n + 1];
            d[0] = 1;//->d[1]=(1), d[2]=(2), d[3]=(3)
            //1만 이용, 2까지 이용, 3도 이용,,
            //111,12,3 / 1111,112,22,13
            for (int k = 1; k <= 3; k++)
                for (int i = 1; i <= n; i++)
                    if (i - k >= 0) d[i] += d[i - k];
            System.out.println(d[n]);
        }
    }
}
