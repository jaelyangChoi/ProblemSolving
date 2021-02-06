package BruteForce.Permutation;
//스타트와 링크

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ14889 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < n; j++)
                arr[i][j] = Integer.parseInt(s[j]);
        }
        int min = Integer.MAX_VALUE;

        //팀 배분 -> 0,1 같은 것을 포함한 순열
        int[] member = new int[n];
        for (int i = n - 1; i >= n / 2; i--)
            member[i] = 1;
        int[] team1 = new int[n / 2];
        int[] team2 = new int[n / 2];

        do {
            int i1 = 0, i2 = 0;
            for (int i = 0; i < n; i++) {
                if (member[i] == 0) team1[i1++] = i;
                else team2[i2++] = i;
            }
            //능력치 계산
            int t1 = calc(team1, arr);
            int t2 = calc(team2, arr);
            min = Math.min(min, Math.abs(t1 - t2));
        } while (next_permutation(member));

        System.out.println(min);
    }

    private static int calc(int[] team, int[][] arr) {
        int m = team.length;
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i == j) continue;
                sum += arr[team[i]][team[j]];
            }
        }
        return sum;
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
