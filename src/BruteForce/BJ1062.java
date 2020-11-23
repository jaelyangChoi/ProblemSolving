package BruteForce;
//가르침 다시 풀기

import java.util.Scanner;

//비트마스크의 위력: 문자열 비교 vs 비트마스크(정수) 비교 O(n):O(1)
public class BJ1062 {
    static int[] word;//문자열을 다 넣는게 아니라, 문자열이 포함하는 단어를 비트로 표현. 비교 시 O(len)이 아니라 O(1)!!!
    static int max = 0;
    static final int[] base = {'a'-'a', 'c'-'a', 'i'-'a', 'n'-'a', 't'-'a'};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        if(k<5) {
            System.out.println(0);
            System.exit(0);
        }
        word = new int[n];
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (char c : s.toCharArray())
                word[i] |= (1 << (c - 'a'));
        }

        int learn = 0;
        for (int x : base)//필수 알파벳 미리 배우기
            learn |= (1 << x);
        choose(k - 5, 0, learn);
        System.out.println(max);
    }

    private static void choose(int remain, int i, int learn) {
        if (remain == 0) {//더 배울 것 x
            count(learn);
            return;
        }
        if (i >= 26) return;//끝까지 도달
        if (i != base[0] && i != base[1] && i != base[2] && i != base[3] && i != base[4])//이미 배운 것 아니면
            choose(remain - 1, i + 1, learn | (1 << i));//배우고 다음으로
        choose(remain, i + 1, learn);//안 배우고 다음으로
    }

    private static void count(int learn) {
        int cnt = 0;
        for (int w : word)
            if ((w & ((1 << 26) - 1 - learn)) == 0) cnt++;//1 << 26) - 1 - learn: int는 32bit이므로 앞부분까지 바꾸기 위해
        max = Math.max(max, cnt);
    }
}
