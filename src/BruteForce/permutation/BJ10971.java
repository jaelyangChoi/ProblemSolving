package BruteForce.permutation;
//외판원 순회2 (TSP)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//(a-b-c-a) == (b-c-a-b) 같으므로, 시작점을 a로 고정해도 된다.
public class BJ10971 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] expense = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < n; j++)
                expense[i][j] = Integer.parseInt(s[j]);
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = i;
        int min = Integer.MAX_VALUE;
        //모든 순열을 만든다.
        do {
            if(arr[0]!=0) break; //시작점을 0으로 고정했으므로
            //비용 계산
            int sum = expense[arr[n - 1]][arr[0]];
            if (sum == 0) continue;
            for (int i = 0; i < n-1; i++) {
                int exp = expense[arr[i]][arr[i + 1]];
                if (exp == 0) {
                    sum = -1;
                    break;
                }
                sum += exp;
            }
            if (sum > 0 && sum < min) min = sum;
        } while (next_permutation(arr));
        System.out.println(min);
    }

    //사전 순으로 다음에 오는 순열로 만든다. (오름차순)
    public static boolean next_permutation(int[] arr) {
        //마지막 부분 순열의 경계 찾기(앞으로 이동하며 내림차순의 끝)
        int i = arr.length - 1; //경계를 가리킴 (맨 끝부터)
        while (i > 0 && arr[i - 1] >= arr[i])
            i--;
        if (i <= 0) return false;//마지막 순열까지 다옴

        //마지막 부분 순열을 첫 부분 순열로 바꾸는 과정
        // 1.경계를 다음 수로 교환한다.
        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1])//현재보다 다음으로 큰 수를 찾기 위해
            j--;

        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        // 2.첫 부분 순열 만들기(경계 이후를 오름차순으로 바꾼다)
        j = arr.length - 1;
        while (i < j) {
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            j--;
            i++;
        }
        return true;
    }
}
