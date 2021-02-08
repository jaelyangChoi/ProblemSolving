package DP;
//파일 합치기
//bottom-up 점화식 순서가 애매하면 top-down(재귀)로!
//사례=> 일반화
//바로 직전 단계만 고려한다. 재귀는 너무 깊게 생각할 필요 없다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11066 {
    static int[] arr;
    static int[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
            arr = new int[k];
            String[] temp = br.readLine().split(" ");
            for (int i = 0; i < k; i++)
                arr[i] = Integer.parseInt(temp[i]);

            d = new int[k][k]; //d[s][e]: s~e까지 합치는 최소 비용
            //d[s][e] = min(d[s][m-1]+d[m][e]+sum)
            //for문을 어떻게 돌려야할지 애매 => 재귀!
            System.out.println(f(0, k - 1));
        }
    }

    //O(N^3)
    static int f(int s, int e) {
        if (s == e) return 0;
        //if (e - s == 1) return arr[s] + arr[e]; 아래 라인에 포함됨
        if (d[s][e] == 0) {
            int sum = 0, min = Integer.MAX_VALUE;
            for (int i = s; i <= e; i++)
                sum += arr[i];
            for (int m = s; m <= e - 1; m++) {
                int cur = f(s, m) + f(m + 1, e) + sum;
                if (min > cur) min = cur;
            }
            d[s][e] = min;
        }
        return d[s][e];
    }
}
