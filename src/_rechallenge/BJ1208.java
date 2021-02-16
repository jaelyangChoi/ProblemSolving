package _rechallenge;
//부분수열의 합2
//배열 2개를 two pointers 알고리즘으로 탐색하는 건 O(n+m)
//O(n)으로 탐색하기 위해 map 활용!

import java.util.HashMap;
import java.util.Scanner;

public class BJ1208 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        //2^40개 너무 큼 => 2^20개씩 반으로 갈라 만나기
        int m = n / 2;
        n -= m;
        int[] d1 = new int[1 << m]; //2^m개
        int[] d2 = new int[1 << n];

        //모든 조합(비트마스크)의 합을 구함
        for (int k = 0; k < 1 << m; k++)
            for (int idx = 0; idx < m; idx++)
                if ((k & (1 << idx)) != 0) d1[k] += arr[idx];
        for (int k = 0; k < 1 << n; k++)
            for (int idx = 0; idx < n; idx++)
                if ((k & (1 << idx)) != 0) d2[k] += arr[idx + m];

        //d2를 map에 넣어 갯수 mapping
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int sum : d2)
            map.put(sum, map.getOrDefault(sum, 0) + 1);

        //답 구함
        long ans = 0;
        for (int sum : d1)
            ans += map.getOrDefault(s - sum, 0);
        if (s == 0) ans--; //크기가 양수일 때 이므로
        System.out.println(ans);
    }
}
