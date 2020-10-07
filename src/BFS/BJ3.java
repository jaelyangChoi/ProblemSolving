package BFS;
//퍼즐

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
//틀린 이유:상태가 다르면 다른 정점. 정점의 기준을 잘못 잡았다.이걸 풀다가 깨달았다.. 만만하게 보고 달려들지 말자..
//틀린 이유:매번 배열을 복사하는건 시간,공간적으로 굉장히 무리.
public class BJ3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] p = new int[3][3];
        int zeroI = 0, zeroJ = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                p[i][j] = sc.nextInt();
                if (p[i][j] == 0) {
                    zeroI = i;
                    zeroJ = j;
                }
            }
        final int[] di = {0, 0, 1, -1};//왼,오,아,위
        final int[] dj = {-1, 1, 0, 0};
        boolean[][] check = new boolean[3][3];//zero의 위치
        int[][] dist = new int[3][3];
        Queue<Integer> q = new ArrayDeque<>();
        Queue<int[][]> q2 = new ArrayDeque<>();
        q.offer(zeroI);
        q.offer(zeroJ);
        q2.offer(p);
        while (!q.isEmpty()) {
            zeroI = q.poll();
            zeroJ = q.poll();
            p = q2.poll();
            if (zeroI == 2 && zeroJ == 2) break;

            //연산
            for (int k = 0; k < 4; k++) {
                int ni = zeroI + di[k];
                int nj = zeroJ + dj[k];
                if (ni >= 0 && nj >= 0 && ni < 3 && nj < 3) {
                    if (check[ni][nj]) continue;
                    int[][] nextP = new int[3][3];
                    for (int i = 0; i < 3; i++)
                        for (int j = 0; j < 3; j++)
                            nextP[i][j] = p[i][j];
                    int temp = nextP[ni][nj];
                    nextP[ni][nj] = p[zeroI][zeroJ];
                    p[zeroI][zeroJ] = temp;
                    q.offer(ni);
                    q.offer(nj);
                    q2.offer(nextP);
                    dist[ni][nj]=dist[zeroI][zeroJ]+1;
                }
            }
        }
        System.out.println(dist[2][2]);
    }
}
