package BruteForce.TwoPointers;
//부분 합

//절대 정답이 되지 않는 경우를 skip하는 알고리즘. 건너 뛰기
//이중 포문을 사용하지 않고 두 개의 포인터를 사용하여 시간 개선. O(n^2) => O(n)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int s = Integer.parseInt(temp[1]);
        int[] arr = new int[n + 1]; //skill 1: 범위 검사를 줄이기 위해
        int i = 0;
        for (String x : br.readLine().split(" "))
            arr[i++] = Integer.parseInt(x);
        int ans = n + 1; //skill 2: 절대 될 수 없는 답. 꼭 -1로 할 필요없다.
        int f = 0, e = 0;
        int sum = arr[0];

        while (f <= e && e < n) {
            if (sum < s) {
                sum += arr[++e];
            }else if (sum > s) {
                ans = Math.min(ans, e - f + 1); //if (ans == 0 || ans > (e - f + 1)) ans = e - f + 1;
                sum -= arr[f++];
            } else {
                ans = Math.min(ans, e - f + 1);
                sum += arr[++e];
            }
        }
        if (ans == n + 1) ans = 0;
        System.out.println(ans);
    }
}
