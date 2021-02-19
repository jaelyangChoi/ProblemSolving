package Simulation;
//주사위 굴리기
//틀린 이유: 주사위는 회전한다. 상단이 1이더라도 오른쪽은 2,3,4,5가 될 수 있다. 고정된게 아니다..

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ14499 {
    public static void main(String[] args) throws IOException {
        final int[] dx = new int[]{0, 0, 0, -1, 1}; //동,서,북,남
        final int[] dy = new int[]{0, 1, -1, 0, 0};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int x = Integer.parseInt(temp[2]);
        int y = Integer.parseInt(temp[3]);
        int k = Integer.parseInt(temp[4]);

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < m; j++)
                map[i][j] = Integer.parseInt(temp[j]);
        }
        int[] commands = new int[k]; // 동(1), 서(2),북(3),남(4)
        temp = br.readLine().split(" ");
        for (int i = 0; i < k; i++)
            commands[i] = Integer.parseInt(temp[i]);
        int[] dice = new int[7]; //주사위 면에 쓰여 있는 수. d[1]=top, d[6]=bottom
        //이동
        for (int command : commands) {
            int nx = x + dx[command];
            int ny = y + dy[command];
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            x = nx;
            y = ny;
            //주사위 면 변화
            switch (command) {
                case 1: //동
                    int a = dice[1];
                    dice[1] = dice[4];
                    dice[4] = dice[6];
                    dice[6] = dice[3];
                    dice[3] = a;
                    break;
                case 2: //서
                    a = dice[1];
                    dice[1] = dice[3];
                    dice[3] = dice[6];
                    dice[6] = dice[4];
                    dice[4] = a;
                    break;
                case 3: //북
                    a = dice[1];
                    dice[1] = dice[5];
                    dice[5] = dice[6];
                    dice[6] = dice[2];
                    dice[2] = a;
                    break;
                case 4: //남
                    a = dice[1];
                    dice[1] = dice[2];
                    dice[2] = dice[6];
                    dice[6] = dice[5];
                    dice[5] = a;
                    break;

            }
            //칸이 0이면 바닥면 복사
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
