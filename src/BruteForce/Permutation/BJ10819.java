package BruteForce.Permutation;

import java.util.Arrays;
import java.util.Scanner;

/* 차이를 최대로
배열에 들어있는 정수의 순서를 적절히 바꿔서 다음 식의 최댓값을 구하는 프로그램
|A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|
*/
public class BJ10819 {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int max = -1;
        Arrays.sort(arr); //내림차순 순열 만들기 전 정렬

        //1. 모든 순열을 만든다.
        do {//n!번 호출된다.
            //2. 값을 계산한다.
            int temp = 0;
            for (int i = 0; i < n - 1; i++)
                temp += Math.abs(arr[i] - arr[i + 1]);
            //3. 최대값 기록
            if (max < 0 || temp > max)
                max = temp;
        } while (next_permutation(arr));

        System.out.println(max);
    }
}
