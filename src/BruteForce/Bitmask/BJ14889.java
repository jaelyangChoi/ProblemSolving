package BruteForce.Bitmask;
//스타트와 링크

import java.util.Scanner;

public class BJ14889 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = sc.nextInt();
        int ans = Integer.MAX_VALUE;
        //모든 조합을 만든다.
        //스타트팀 0, 링크팀 1로 modeling
        //0000~1111까지 모든 조합을 만들고 팀원이 n/2이 아니면 건너뛴다.
        int[] start = new int[n / 2];
        int[] link = new int[n / 2];
        for (int set = 0; set < (1 << n); set++) {
            if (Integer.bitCount(set) != n/2) continue; //이 api를 모르면 for문으로 직접 cnt
            int s = 0, l = 0;
            for (int idx = 0; idx < n; idx++) {
                if ((set & (1 << idx)) == 0) start[s++] = idx;
                else link[l++] = idx;
            }
            int s1 = 0, s2 = 0;
            for (int i = 0; i < n / 2; i++)
                for (int j = 0; j < n / 2; j++) {
                    if (i == j) continue;
                    s1 += arr[start[i]][start[j]];
                    s2 += arr[link[i]][link[j]];
                }
            ans = Math.min(ans, Math.abs(s1 - s2));
        }
        System.out.println(ans);
    }
}
