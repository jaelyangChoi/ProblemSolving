package BruteForce;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

//두 배열의 합
public class TwoArrSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        int m = sc.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++)
            b[i] = sc.nextInt();
        int[] sa = new int[n * (n + 1) / 2];
        int[] sb = new int[m * (m + 1) / 2];

        //각 부 배열의 합을 구함
        int idx = 0;
        for (int s = 0; s < n; s++) //start
            for (int e = s; e < n; e++) {//end
                for (int i = s; i <= e; i++)
                    sa[idx] += a[i];
                idx++;
            }
        idx = 0;
        for (int s = 0; s < m; s++) //start
            for (int e = s; e < m; e++) {//end
                for (int i = s; i <= e; i++)
                    sb[idx] += b[i];
                idx++;
            }

        Arrays.sort(sa);
        Arrays.sort(sb);

        //sa[i]와 sb[j]의 합이 t가 되면 카운드
        //t보다 작으면 증가시켜야 하므로 ai++, 크면 감소시켜야 하므로 bi--;
        long cnt = 0;
        int ai = 0;
        int bi = sb.length - 1;
        while (ai < sa.length && bi >= 0) {
            int s = sa[ai] + sb[bi];
            if (s == t) {
                long same1 = 1, same2 = 1;
                ai++;
                bi--;
                while (ai < sa.length && sa[ai] == sa[ai - 1]) {
                    ai++;
                    same1++;
                }
                while (bi >= 0 && sb[bi] == sb[bi + 1]) {
                    bi--;
                    same2++;
                }
                cnt += same1 * same2;
            } else if (s < t) ai++;
            else bi--;
        }
        System.out.println(cnt);
    }
}
