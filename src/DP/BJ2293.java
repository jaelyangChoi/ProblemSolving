package DP;
//동전 1
import java.util.Scanner;

public class BJ2293 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        //구성이 같으면 중복 -> 순서를 부여하면 된다.
        //1만 사용해서 전부 구한다.
        //1,2만 사용해서 전부 구한다.
        int[] d = new int[k + 1]; //d[i]=d[i-1](1)+d[i-2](2)+..
        d[0] = 1; //->d[1]=1이 된다
        for (int x : arr)
            for (int i = 1; i <= k; i++)
                if (i - x >= 0) d[i] += d[i - x];
        System.out.println(d[k]);
    }
}
