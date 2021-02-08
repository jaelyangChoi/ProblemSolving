package BruteForce.MeetInTheMiddle;
//부분수열의 합2
//시간 초과 -> two pointers로 선형 탐색. 양쪽 압박 탐색 안했더니 시간 초과
//비트마스크의 정수가 원소의 갯수..곱할 경우 long 써야하는 것 간과

import java.util.*;

/* 중간에서 만나기
    문제를 절반으로 나눠서
    양쪽 절반에서 모든 경우를 다 해보는 방법.
    -> 탐색의 크기가 많이 줄어든다. M=N/2이라고 할 때, 2^N에서 2^M+2^M
*/
public class BJ1208 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int m = n / 2;
        int[] first = new int[1 << m];
        int[] second = new int[1 << (n - m)];

        //O(2^m)+O(2^m)
        for (int k = 0; k < 1 << m; k++) //조합: 비트마스크!
            for (int idx = 0; idx < m; idx++)
                if ((k & (1 << idx)) != 0) first[k] += arr[idx];

        for (int k = 0; k < 1 << (n - m); k++)
            for (int idx = 0; idx < n - m; idx++)
                if ((k & (1 << idx)) != 0) second[k] += arr[idx+m];

        long ans = 0;
        Arrays.sort(first);
        Arrays.sort(second);
        n = 1 << (n - m); //길이
        m = 1 << m; //m이 20이면 2^20개..아직은 int 범위.
        int f = 0, e = n - 1;
        while (f < m && e >= 0) {
            int sum = first[f] + second[e];
            if (sum < s) {
                f++;
            } else if (sum > s) {
                e--;
            } else {
                long c1 = 1, c2 = 1;
                while (++f < m && first[f] == first[f - 1])
                    c1++;
                while (--e >= 0 && second[e] == second[e + 1])
                    c2++;
                ans += c1 * c2; //최대 2^20 * 2^20 이므로  c1를 long으로 해야한다.
            }
        }
        if (s == 0) ans--; //크기가 양수이므로 s=0일때 양쪽 모두 0을 택하는 경우 제외
        System.out.println(ans);
    }
}
