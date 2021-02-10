package BruteForce.MeetInTheMiddle;
// 두 배열의 합
/* 연속합:n(n+1)/2 | 연속합x:2^n */
//발상 못한 이유: n,m이 너무 크다. 2^1000... => X. 연속합이었다.. 그럼 1000^2..

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        //모든 부배열의 합을 구한다.
        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();
        for (int s = 0; s < n; s++) {
            int sum = 0;
            for (int e = s; e < n; e++) {
                sum += a[e];
                first.add(sum);
            }
        }
        for (int s = 0; s < m; s++) {
            int sum = 0;
            for (int e = s; e < m; e++) {
                sum += b[e];
                second.add(sum);
            }
        }
        //부배열 간의 합이 t가 되는지 본다
        HashMap<Integer, Integer> map = new HashMap<>(); //second 원소 별 갯수 파악
        for (int sum : second)
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        long ans = 0;
        for (int sum : first)
            ans += map.getOrDefault(t - sum, 0);
        System.out.println(ans);
        /*요 과정을 map을 이용해 미리 계산해 둔다.. 각 a의 부분합에 대해 b가 몇개 있는지만. 그럼 정렬할 필요x
//        Collections.sort(first);
//        Collections.sort(second, Collections.reverseOrder());
//
//        long ans = 0;
//        int i1 = 0, i2 = 0;
//        while (i1 < first.size() && i2 < second.size()) {
//            int sum = first.get(i1) + second.get(i2);
//            if (sum == t) {
//                int c1 = 1, c2 = 1;
//                while (i1 + c1 < first.size() && first.get(i1).equals(first.get(i1 + c1)))
//                    c1++;
//                while (i2 + c2 < second.size() && second.get(i2).equals(second.get(i2 + c2)))
//                    c2++;
//                ans += (long) c1 * c2;
//
//                i1 += c1;
//                i2 += c2;
//            } else if (sum < t) {
//                while (++i1 < first.size() && first.get(i1 - 1).equals(first.get(i1))) ;
//            } else
//                while (++i2 < second.size() && second.get(i2 - 1).equals(second.get(i2))) ;
//        }*/
    }
}
