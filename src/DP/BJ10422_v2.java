package DP;
//괄호 - 2차원 배열 풀이
/* 중요한 교훈
    피상적 현상만 보고 성급한 일반화하지 말 것.
    예외 찾기는 힘들다.
    논리적이라면 예외를 찾을 필요가 없다
*/
import java.util.Scanner;

//길이 l일 때 괄호 case를 구하고, 그 중 올바른 경우를 구한다.
public class BJ10422_v2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final long mod = 1000000007L;
        long d[][] = new long[5001][5001]; //d[l][x]. 길이 l, 짝이 맞지 않는 여는 괄호 갯수 x. x='('개수-')'개수
        d[0][0] = 1;
        for (int l = 1; l <= 5000; l++)
            for (int x = 0; x <= l; x++) {
                if (x - 1 >= 0) d[l][x] = d[l - 1][x - 1];
                if (x + 1 <= l) d[l][x] += d[l - 1][x + 1];
                d[l][x] %= mod;
            }

        int t = sc.nextInt();
        while (t-- > 0)
            System.out.println(d[sc.nextInt()][0]);
    }
}
