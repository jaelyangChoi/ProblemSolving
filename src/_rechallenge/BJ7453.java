package _rechallenge;
// 합이 0인 네 정수 https://www.acmicpc.net/problem/7453
// 해쉬맵은 시간초과.. 수가 많으면 해쉬 충돌을 저격한 데이터 셋이 있을 가능성도 있기 때문.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ7453 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            a[i] = Integer.parseInt(temp[0]);
            b[i] = Integer.parseInt(temp[1]);
            c[i] = Integer.parseInt(temp[2]);
            d[i] = Integer.parseInt(temp[3]);
        }
        int[] sum1 = new int[n * n];
        int[] sum2 = new int[n * n];
        for (int i = 0, idx = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                sum1[idx] = a[i] + b[j];
                sum2[idx++] = c[i] + d[j];
            }
        Arrays.sort(sum2);
        long ans = 0;
        for (int sum : sum1)
            ans += upper_bound(sum2, -sum) - lower_bound(sum2, -sum);
        System.out.println(ans);
    }

    //탐색 대상: 크면서 가장 작은 수
    static int upper_bound(int[] a, int target) {
        int left = 0;
        int right = a.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] <= target) left = mid + 1; //같아도 계속 전진
            else right = mid;
        }
        return left;
    }

    //탐색 대상: 크거나 같으면서 가장 작은 수
    static int lower_bound(int[] a, int target) {
        int left = 0;
        int right = a.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}
