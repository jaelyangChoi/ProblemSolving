package BruteForce.MeetInTheMiddle;
//합이 0인 네 정수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//틀린 이유: 시간 초과
//map, two pointers algorithm 보다 binary search가 빠르다. O(logN)
public class BJ7453 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[4][n];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < 4; j++)
                arr[j][i] = Integer.parseInt(temp[j]);
        }

        //4000^4 너무 많다 => 2000^2 두 개로 나눈다.
        int[] ab = new int[n * n];
        int[] cd = new int[n * n];
        int idx = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                ab[idx] = arr[0][i] + arr[1][j];
                cd[idx++] = arr[2][i] + arr[3][j];
            }

        //(C+D)=-(A+B)인 갯수를 센다.
        long ans = 0;
        //binary search가 빠르다. 중복 처리하면 더 빠르다!
        Arrays.sort(cd);
        for (int sum : ab)
            ans += upper_bound(cd, -sum) - lower_bound(cd, -sum);
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