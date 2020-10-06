package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//합이 0인 네 정수
//이진 탐색, 정렬 등을 배우면서 습득한 원리, 아이디어를 코테에 활용하는 거구나..

public class BJ2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[4][n];
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < 4; j++)
                arr[j][i] = Integer.parseInt(s[j]);
        }

    }
}
