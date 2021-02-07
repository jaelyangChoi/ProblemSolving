package DP;
//크리보드
/* DP 공략법
    1. 점화식의 정의를 그대로 글로 적는다.
    2. 모든 상황을 그려본다.
 */

/* skill
    1. 그룹으로 생각한다 [Ctrl-A,Ctrl-C,Ctrl-V]
    2. 일반화
*/
import java.util.Scanner;

//패인 1: 속단. 시간에 쫓겨 반례를 찾아보지 않고 지례짐작.. 성급한 일반화..
//패인 2: 이전에 풀었던 문제랑 비슷한 것 같아서 그 방식으로만 제자리 걸음..
public class BJ11058 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] d = new long[n + 1]; //n<=100 ->cv 33번 -> 2^33
        //d[i]:i번 눌렀을 때 화면 A 갯수 최대 값
        //Ctrl-V를 누르려면 Ctrl-A,Ctrl-C가 선행되야 의미가 있다.
        for (int i = 1; i <= n; i++) {
            d[i] = d[i - 1] + 1;
            for (int j = 1; j <= i - 3; j++) {
                long cur = d[i - j - 2] * (j + 1);
                if (cur > d[i]) d[i] = cur;
            }
        }
        System.out.println(d[n]);
    }
}
