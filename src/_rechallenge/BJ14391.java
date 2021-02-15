package _rechallenge;
//종이 조각
import java.util.Scanner;

public class BJ14391 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            int j = 0;
            for (char c : sc.next().toCharArray())
                arr[i][j++] = c - '0';
        }

        int ans = 0;
        for (int s = 0; s < 1 << (n * m); s++) {//0,1로 모든 조합을 표현
            int sum = 0;
            //가로:0
            for (int i = 0; i < n; i++) {
                //m개씩 끊어 읽는다.
                int line = 0;
                for (int j = 0; j < m; j++) {
                    if ((s & (1 << (m * i + j))) == 0) { //가로 원소
                        line = line * 10 + arr[i][j];
                    } else {//세로 원소
                        sum += line;
                        line = 0;
                    }
                }
                sum += line;
            }
            //세로:1
            for (int j = 0; j < m; j++) {
                //n개씩 끊어 읽는다.
                int line = 0;
                for (int i = 0; i < n; i++) {
                    if ((s & (1 << (m * i + j))) > 0) { //세로 원소
                        line = line * 10 + arr[i][j];
                    } else {//세로 원소
                        sum += line;
                        line = 0;
                    }
                }
                sum += line;
            }
            if (sum > ans) ans = sum;
        }
        System.out.println(ans);
    }
}
