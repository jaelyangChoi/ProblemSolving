package BruteForce;

import java.util.*;

//두 배열의 합
public class Bj1 {
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
        ArrayList<Integer> sumA = new ArrayList<>();
        ArrayList<Integer> sumB = new ArrayList<>();
        for (int i = 0; i < n; i++) {//start
            int sum = 0;
            for (int j = i; j < n; j++) {//end
                sum += a[j];
                sumA.add(sum);
            }
        }
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += b[j];
                sumB.add(sum);
            }
        }
        //같은 것 개수 세기
        HashMap<Integer, Integer> map = new HashMap<>(); //map에 넣기
        for (int e : sumB)
            map.put(e, map.getOrDefault(e, 0) + 1);

        //sumA의 각 요소와 sumB의 각 요소를 더해 t가 되는 것 카운트
        long cnt = 0;
        for (int e : sumA)
            if (map.containsKey(t - e))
                cnt += map.get(t-e);
        System.out.println(cnt);
    }
}
