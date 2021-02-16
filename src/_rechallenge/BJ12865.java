package _rechallenge;
// 평범한 배낭 https://www.acmicpc.net/problem/12865
// 왜 틀렸는지 모름. 찾을 수가 없다.

// 프린트 찍어서 찾았다. 초기값 구성에 문제가 있었다. 초기값에 주의하자!!
// 답을 기준으로 d를 구성할 필요는 없다!
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
        //모든 조합 2^100은 너무 큼, 무게 k 넘는건 고려할 필요x, 이전 조합 활용 => DP
        //무게가 k 이하일때만 고려, 무게 당 최대 가치만 기록
        //물건 중복 사용x => i번 째까지 하나하나 고려
        int[][] d = new int[n + 1][k + 1];//d[i][j]: i번 째까지 고려했을 때, 무게 j의 최대 가치
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= k; j++) {
                d[i][j] = d[i - 1][j];
                if (j - w[i] >= 0)
                    d[i][j] = Math.max(d[i][j], d[i - 1][j - w[i]] + v[i]);
            }

        System.out.println(d[n][k]);
    }
}
