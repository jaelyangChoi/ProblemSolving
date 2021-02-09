package DP;
//평범한 배낭

//왜 틀린지 모르겠다.. => 문제 잘못 이해. 조건엔 없었지만 물건이니까 하나만 넣을 수 있겠구나..

/* 상황을 잘못 그렸다.
    n번째 답 도출 직전 상황(이전 답 활용해서)만 봐라. 그 상황에서 일반화가 필요하면 한다.
    n번째 답 도출 직전 상황: 주연은 답(k)이 아니라 구성 원소일 수 있다. 'i번째 원소까지 고려했을 때' 무게별 최대 가치
*/

/* 점화식이 틀렸다
    직관적으로, '무게 k일때 최대 가치'로 설정하면 i번째 물건이 중복 선택된다!
    중복 선택을 막기위해, 'i번째까지 고려했을 때, 무게당 최대가치'
*/
//일차원 배열을 사용하고 뒷놈이 앞놈을 사용할때, i번째를 중복해서 쓰지 않으려면 뒤에서부터 채워야한다!
import java.util.Scanner;

public class BJ12865 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] w = new int[n + 1];
        int[] v = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
//        int[][] d = new int[n+1][k + 1];//d[i][j] = i번째까지 고려했을 때, 무게별 최대 가치
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= k; j++) {
//                d[i][j] = d[i - 1][j]; //i번째 선택x
//                if (j - w[i] >= 0) d[i][j] = Math.max(d[i][j], d[i - 1][j - w[i]] + v[i]); //i번째 선택
//            }
//        }
//        System.out.println(d[n][k]);

        /*d[i][j] = d[i - 1][j]; 고대로 내려오고 가끔 갱신 -> 일차원 배열로 표현 가능!*/
        int[] d = new int[k + 1];
        for (int i = 1; i <= n; i++)
            //for (int j = 1; j <= k; j++) //i번째 물건을 중복해서 사용: d[3]=d[2]+v[3] (v[3] 2번 포함)
            for (int j = k; j >= 1; j--) //따라서 뒤에서부터 채운다: i번째 채울때 i-1까지 고려한 것 사용
                if (j - w[i] >= 0) d[j] = Math.max(d[j], d[j - w[i]] + v[i]);
        System.out.println(d[k]);
    }
}
