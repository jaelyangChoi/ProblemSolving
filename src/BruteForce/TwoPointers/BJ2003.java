package BruteForce.TwoPointers;
//절대 정답이 되지 않는 경우를 skip하는 알고리즘. 건너 뛰기
//이중 포문을 사용하지 않고 두 개의 포인터를 사용하여 시간 개선

//수들의 합2
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int[] arr = new int[n];
        int i = 0;
        for (String s : br.readLine().split(" "))
            arr[i++] = Integer.parseInt(s);
        int f = 0, e = 0, cnt = 0;
        int sum = arr[e];
        while (true) {
            if (sum < m) {
                if (++e >= n) break;
                sum += arr[e];
            } else if (sum > m) {
                if (++f >= n) break;
                sum -= arr[f-1];
            } else {
                cnt++;
                if (++f >= n || ++e >= n) break;
                sum = sum + arr[e] - arr[f - 1];
            }
        }
        System.out.println(cnt);
    }
}
