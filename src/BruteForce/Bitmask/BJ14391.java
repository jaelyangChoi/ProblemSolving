package BruteForce.Bitmask;
//종이 조각

import java.util.Scanner;

public class BJ14391 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            String temp = sc.next();
            for (int j = 0; j < m; j++)
                arr[i][j] = temp.charAt(j) - '0';
        }
        //일단 모든 조합을 다 구한다. 비트마스크를 이용하면 조합을 구하는 과정이 간단
        //한 조각은 가로 or 세로에 속한다 => 0,1로 모델링
        int ans = 0;
        for (int k = 0; k < (1 << n * m); k++) {//0000...1111
            int sum = 0;
            //가로 합
            //편의를 위해 앞 뒤에서부터 표시했다고 치자 (비트는 뒤부터 읽으니까)
            for (int i = 0; i < n; i++) {
                int line = 0;
                //boolean successful = false; 연속되는지 아닌지 굳이 판별할 필요가 없었다..
                for (int j = 0; j < m; j++) {
                    int idx = m * i + j; //오류 원인: idx=n*i+j로 함..
                    if ((k & (1 << idx)) == 0) { // idx번째가 0(가로)이라면
                        line = line * 10 + arr[i][j];
                    } else {
                        sum += line;
                        line = 0;
                    }
                }
                sum += line;
            }
            //세로 합
            for (int j = 0; j < m; j++) {
                int line = 0;
                for (int i = 0; i < n; i++) {
                    int idx = m * i + j;
                    if ((k & (1 << idx)) != 0) { // idx번째가 1(세로)이라면
                        line = line * 10 + arr[i][j];
                    } else {
                        sum += line;
                        line = 0;
                    }
                }
                sum += line;
            }
            ans = Math.max(ans, sum);
        }
        System.out.println(ans);
    }
}
