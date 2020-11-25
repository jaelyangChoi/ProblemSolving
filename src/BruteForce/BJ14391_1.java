package BruteForce;
//종이 조각
//발상이 아니라 구현에서 헤맸다. 다시 풀어볼 것..
import java.util.Scanner;

public class BJ14391_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            int j = 0;
            for (String s : sc.nextLine().split(""))
                arr[i][j++] = Integer.parseInt(s);
        }

        int max = 0;
        //가로 0, 세로 1로 모델링 -> 비트마스크
        for (int k = 0; k < (1 << (n * m)); k++) {
            int sum = 0;
            //가로(0) 합
            for (int i = 0; i < n; i++) {
                int cur = 0;
                for (int j = 0; j < m; j++) {
                    if ((k & (1 << (m * i + j))) == 0)//가로
                        cur = cur * 10 + arr[i][j];
                    else {//세로
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }
            //세로(1) 합
            for (int j = 0; j < m; j++) {
                int cur = 0;
                for (int i = 0; i < n; i++) {
                    if ((k & (1 << (m * i + j))) != 0)//세로
                        cur = cur * 10 + arr[i][j];
                    else {//가로
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
}
