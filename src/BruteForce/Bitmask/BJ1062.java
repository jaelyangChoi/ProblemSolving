package BruteForce.Bitmask;
//가르침
//비트마스크의 위력: 문자열 비교 vs 비트마스크(정수) 비교 O(n):O(1)

import java.util.Scanner;

public class BJ1062 {
    static final boolean[] essential = new boolean[26];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        for (int i : new int[]{'a' - 'a', 'c' - 'a', 't' - 'a', 'i' - 'a', 'n' - 'a'})
            essential[i] = true;
        int[] words = new int[n];
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            //각 단어의 구성 성분만 중요 -> 비트마스크(정수)로 표현하고 비교! O(1)
            for (char c : s.toCharArray())
                words[i] |= (1 << (c - 'a'));
        }
        System.out.println(go(0, k, 0, words));
    }

    private static int go(int idx, int k, int mask, int[] words) {
        if (k < 0) return 0;
        if (idx == 26) {
            if (k != 0) return 0;
            return count(words, mask);
        }

        int ans = 0;
        //idx번째 알파벳을 배운다
        int ret = go(idx + 1, k - 1, mask | (1 << idx), words);
        ans = Math.max(ans, ret);

        //idx번째 알파벳을 배우지 않는다. 필수 알파벳이 아니라면
        if (!essential[idx])
            ret = go(idx + 1, k, mask, words);
        ans = Math.max(ans, ret);

        return ans;
    }

    private static int count(int[] words, int mask) {
        int cnt = 0;
        for (int word : words)
            if ((word & ((1 << 26) - 1 - mask)) == 0) cnt++; //안 배운 것과의 교집합
        //if ((word & mask) == mask) cnt++; 배운 것과의 교집합
        return cnt;
    }
}