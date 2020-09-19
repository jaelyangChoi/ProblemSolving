package Simulation;

import java.io.IOException;
import java.util.Scanner;

//주사위 굴리기
//해결 못한 이유: 이동 규칙이 있을 줄 알았다. 그걸 알아내지 못했다.
//해결 못한 이유: 기준을 잡고 생각하지 않으니 너무 복잡했다.
//깨달음: 별다른 방법이 떠오르지 않는다면 무식하게 푸는 게 답이다!
//깨달음: 복잡할 수록 기준을 잡고 생각해야 한다!
public class Q1 {
    static int[] dr = {0, 0, -1, 1};//동서북남
    static int[] dc = {1, -1, 0, 0};//동서북남

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int k = sc.nextInt();
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                map[i][j] = sc.nextInt();
        }
        int[] dice = new int[7];//인덱스별 위치를 기준으로 삼고 생각한다!

        while (k-- > 0) {
            int command = sc.nextInt() - 1;
            int nx = x + dr[command], ny = y + dc[command];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

            if (command == 0) {//동
                int temp = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[3];
                dice[3] = temp;
            } else if (command == 1) {//서
                int temp = dice[1];
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = dice[4];
                dice[4] = temp;
            } else if (command == 2) {//북
                int temp = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[2];
                dice[2] = temp;
            } else {//남
                int temp = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[5];
                dice[5] = temp;
            }
            x = nx;
            y = ny;

            if (map[x][y] == 0) {
                map[x][y] = dice[6];
            } else {
                dice[6] = map[x][y];
                map[x][y] = 0;
            }
            System.out.println(dice[1]);
        }
    }
}
