package _rechallenge;
// 두 배열의 합: https://www.acmicpc.net/problem/2143
//1. hashmap 이용 400ms
//2. 정렬-> 이진탐색 이용 1000ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class BJ2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(temp[i]);
        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];
        temp = br.readLine().split(" ");
        for (int i = 0; i < m; i++)
            b[i] = Integer.parseInt(temp[i]);
        int[] sumA = new int[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += a[j];
                sumA[idx++] = sum;
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += b[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        long ans = 0;
        for (int sum : sumA)
            ans += map.getOrDefault(t - sum, 0);
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
