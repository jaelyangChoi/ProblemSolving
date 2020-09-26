package BruteForce;

import java.util.HashSet;
import java.util.Iterator;

public class Q2 {
    static boolean[] check = new boolean[10];
    static HashSet<Integer> set = new HashSet<>();

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;
        return true;
    }

    //순서x, 모두 선택x
    static void combinate(int[] arr, int num, int idx, int m) {
        if (idx == m) {
            set.add(num);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (check[i]) continue;
            check[i] = true; //선택
            combinate(arr, num * 10 + arr[i], idx + 1, m);
            check[i] = false; //선택x. 다음으로 넘어감
        }
    }

    static public int solution(String numbers) {
        int n = numbers.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = numbers.charAt(i) - '0';
        for (int m = 1; m <= n; m++)
            combinate(arr, 0, 0, m);

        int cnt=0;
        Iterator<Integer> iter = set.iterator();
        while(iter.hasNext()){
            if(isPrime(iter.next())) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(solution("011"));
    }
}
