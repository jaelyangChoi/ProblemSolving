package BruteForce.Bitmask;
//2048(Easy)
//n번 이동 경로 -> 비트마스크로 모든 경로를 만들어본다.
import java.util.Scanner;

public class BJ12100 {
    static final int[] di = new int[]{0, 0, -1, 1};//0, 1, 2, 3
    static final int[] dj = new int[]{-1, 1, 0, 0};//왼,오,위,아래
    static final int LIMIT = 5;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = sc.nextInt();
        int ans = 0;
        //5번 이동 경로. 4^5 == 2^10
        for (int d = 0; d < 1 << (LIMIT * 2); d++) {
            int[] dir = genDir(d);
            int[][] map = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    map[i][j] = arr[i][j];

            for (int k : dir)
                simulate(map, k);

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (ans < map[i][j]) ans = map[i][j];
        }
        System.out.println(ans);
    }

    private static void simulate(int[][] map, int k) {
        int n = map.length;
        boolean[][] merged = new boolean[n][n];
        if (k == 0 || k == 2) {//<, ^
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 0) continue;
                    //빈칸인동안 계속 이동
                    int ni = i, nj = j;
                    while (ni + di[k] >= 0 && ni + di[k] < n && nj + dj[k] >= 0 && nj + dj[k] < n) {
                        ni = ni + di[k];
                        nj = nj + dj[k];
                        if (map[ni][nj] != 0) break;
                    }
                    //이동 X
                    if (ni == i && nj == j) continue;
                    //빈칸
                    if (map[ni][nj] == 0) {
                        map[ni][nj] = map[i][j];
                        map[i][j] = 0;
                    } else {
                        //합칠 수 있는 경우
                        if ((map[ni][nj] == map[i][j]) && !merged[ni][nj]) {
                            merged[ni][nj] = true;
                            map[ni][nj] *= 2;
                            map[i][j] = 0;
                        }//합칠 수 없는 경우
                        else {
                            if (ni - di[k] == i && nj - dj[k] == j) continue;
                            map[ni - di[k]][nj - dj[k]] = map[i][j];
                            map[i][j] = 0;
                        }
                    }
                }
            }
        } else {//오른쪽, 아래
            for (int i = n - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (map[i][j] == 0) continue;
                    //빈칸인동안 계속 이동
                    int ni = i, nj = j;
                    while (ni + di[k] >= 0 && ni + di[k] < n && nj + dj[k] >= 0 && nj + dj[k] < n) {
                        ni = ni + di[k];
                        nj = nj + dj[k];
                        if (map[ni][nj] != 0) break;
                    }
                    //이동 X
                    if (ni == i && nj == j) continue;
                    //빈칸
                    if (map[ni][nj] == 0) {
                        map[ni][nj] = map[i][j];
                        map[i][j] = 0;
                    } else {
                        //합칠 수 있는 경우
                        if ((map[ni][nj] == map[i][j]) && !merged[ni][nj]) {
                            merged[ni][nj] = true;
                            map[ni][nj] *= 2;
                            map[i][j] = 0;
                        }//합칠 수 없는 경우
                        else {
                            if (ni - di[k] == i && nj - dj[k] == j) continue;
                            map[ni - di[k]][nj - dj[k]] = map[i][j];
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

    private static int[] genDir(int d) {
        int[] dir = new int[LIMIT];
        for (int i = 0; i < LIMIT; i++, d = d >> 2)
            dir[i] = d & 3; //(11)
        return dir;
    }
}
