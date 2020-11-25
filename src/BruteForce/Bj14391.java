package BruteForce;
//종이 조각 다시 풀기
import java.util.Scanner;

//발상법: 2개 중 하나에 속한다->0,1로 모델링->비트마스크!
public class Bj14391 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//세로 길이
        int m = sc.nextInt();//가로 길이
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            int temp = sc.nextInt();
            int div = (int) Math.pow(10, m-1);
            for (int j = 0; j < m; j++) { //arr[i][j] = s.charAt(j) - '0';가 더 빠르네
                arr[i][j] = temp / div;
                temp %= div;
                div /= 10;
            }
        }
        int max = 0;
        //0은 가로, 1은 세로로 모델링
        //1<<3 = 8 (1000)
        for (int k = 0; k < 1 << (n * m); k++) {//모든 경우의 수 0000..~1111..(16개)
            int sum = 0;
            //가로(0) 조사
            for (int i = 0; i < n; i++) {
                int cur = 0;
                for (int j = 0; j < m; j++) {
                    if ((k & (1 << (m * i + j))) == 0) {//가로. 쭉 이어질수록 크니 이어진 것으로 본다
                        cur = cur * 10 + arr[i][j];
                    } else {//세로
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }
            //세로(1) 조사
            for (int j = 0; j < m; j++) {
                int cur = 0;
                for (int i = 0; i < n; i++) {
                    if ((k & (1 << (m * i + j))) != 0) {//세로
                        cur = cur * 10 + arr[i][j];
                    } else {//가로
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
