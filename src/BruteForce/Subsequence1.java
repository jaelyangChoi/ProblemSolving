package BruteForce;

import java.util.Scanner;

//부분 수열의 합1. n<=20 이므로 2^20. 약 백만. 완전탐색 가능
public class Subsequence1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int cnt = 0;
        //비트마스크, 모든 조합
        for (int k = 1; k < (1 << n); k++) { //공집합 제외
            int sum = 0;
            for (int i = 0; i < n; i++) //해당 인덱스를 선택했는지 조사
                if ((k & (1 << i)) > 0)
                    sum += arr[i];
            if (sum == s) cnt++;
        }
        System.out.println(cnt);
    }
}
