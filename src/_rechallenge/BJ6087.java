package _rechallenge;
//레이저 통신  https://www.acmicpc.net/problem/6087
//비용이 무엇인가?
//예외: 그림 그려 진행하가면서 찾음. 습관적으로 짜는 코드가 위험하다.. 의심해라!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BJ6087 {
    public static void main(String[] args) throws IOException {
        final int[] di = {0, 0, -1, 1};
        final int[] dj = {-1, 1, 0, 0};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int w = Integer.parseInt(temp[0]);
        int h = Integer.parseInt(temp[1]);
        String[] map = new String[h];
        int i1 = -1, j1 = -1, i2 = -1, j2 = -1;
        for (int i = 0; i < h; i++) {
            map[i] = br.readLine();
            for (int j = 0; j < w; j++)
                if (map[i].charAt(j) == 'C')
                    if (i1 == -1) {
                        i1 = i;
                        j1 = j;
                    } else {
                        i2 = i;
                        j2 = j;
                    }
        }
        //C1에서 bfs로 이동한다
        Queue<Integer> q = new ArrayDeque<>();
        int[][] d = new int[h][w];
        for (int[] x : d)
            Arrays.fill(x, -1);
        d[i1][j1] = 0;
        q.offer(i1);
        q.offer(j1);
        while (!q.isEmpty()) {
            int i = q.poll();
            int j = q.poll();
            if (i == i2 & j == j2) break;
            for (int k = 0; k < 4; k++) {
                //라인 단위로 이동, 같은 라인은 비용이 같다.
                int ni = i + di[k];
                int nj = j + dj[k];
                while (ni >= 0 && ni < h && nj >= 0 && nj < w && map[ni].charAt(nj) != '*') {
                    if (d[ni][nj] == -1) { //예외: break로 끝내면 안된다!!
                        d[ni][nj] = d[i][j] + 1;
                        q.offer(ni);
                        q.offer(nj);
                    }//keep going!!
                    ni += di[k];
                    nj += dj[k];
                }
            }
        }
        //직선의 갯수 = d-1
        System.out.println(d[i2][j2] - 1);
    }
}
