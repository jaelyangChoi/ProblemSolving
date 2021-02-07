package BFS;
//레이저 통신

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/* 의미 찾기
    거울 설치->직선의 방향이 바뀜을 의미
    답 = 필요한 직선의 갯수 -1
*/
// 한 칸씩 진행이 아니라 한 라인을 통째로!
/* 발상법
    정답 - 직선의 갯수가 필요. 최소 직선 갯수.
    직선 이동이 같은 비용. ---- 가중치 1 => 가중치 최소 => BFS!
    직선 단위로 계산.
*/

public class BJ6087 {
    public static void main(String[] args) throws IOException {
        final int[] di = {0, 0, -1, 1};
        final int[] dj = {-1, 1, 0, 0};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int w = Integer.parseInt(temp[0]);
        int h = Integer.parseInt(temp[1]);
        String[] map = new String[h];
        int si = -1, sj = -1, ei = -1, ej = -1;
        for (int i = 0; i < h; i++) {
            map[i] = br.readLine();
            for (int j = 0; j < w; j++)
                if (map[i].charAt(j) == 'C') {
                    if (si == -1) {
                        si = i;
                        sj = j;
                    } else {
                        ei = i;
                        ej = j;
                    }
                }
        }
        Queue<Integer> q = new ArrayDeque<>();
        int[][] d = new int[h][w];
        for (int i = 0; i < h; i++)
            Arrays.fill(d[i], -1);
        d[si][sj] = 0;
        q.offer(si);
        q.offer(sj);
        while (!q.isEmpty()) {
            int i = q.poll();
            int j = q.poll();
            for (int k = 0; k < 4; k++) {
                int ni = i + di[k];
                int nj = j + dj[k];
                while (ni >= 0 && ni < h && nj >= 0 && nj < w && map[ni].charAt(nj) != '*') {
                    if (d[ni][nj] == -1) {
                        d[ni][nj] = d[i][j] + 1;
                        q.offer(ni);
                        q.offer(nj);
                    }//keep going
                    ni += di[k];
                    nj += dj[k];
                }
            }
        }
        System.out.println(d[ei][ej] - 1);
    }
}

/*
static class Node {
        int i, j, pre;

        public Node(int i, int j, int pre) {
            this.i = i;
            this.j = j;
            this.pre = pre;
        }
    }

    public static void main(String[] args) throws IOException {
        final int[] di = {0, 0, -1, 1};
        final int[] dj = {-1, 1, 0, 0};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int w = Integer.parseInt(temp[0]);
        int h = Integer.parseInt(temp[1]);
        char[][] map = new char[h][w];
        Node start = null, end = null;
        for (int i = 0; i < h; i++) {
            temp[0] = br.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = temp[0].charAt(j);
                if (map[i][j] == 'C') {
                    if (start == null) start = new Node(i, j, -1);
                    else end = new Node(i, j, -1);
                }
            }
        }
        Queue<Node> q = new ArrayDeque<>();
        int[][] d = new int[h][w];
        for (int i = 0; i < h; i++)
            Arrays.fill(d[i], -1);
        d[start.i][start.j] = 0;
        q.offer(start);
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int k = 0; k < 4; k++) {
                int ni = now.i + di[k];
                int nj = now.j + dj[k];
                if (ni < 0 || ni >= h || nj < 0 || nj >= w) continue;
                if (map[ni][nj] == '*') continue;
                int dist = d[now.i][now.j];
                if (now.pre != -1 && now.pre != k) dist++;
                if (d[ni][nj] == -1 || dist <= d[ni][nj]) {
                    d[ni][nj] = dist;
                    q.offer(new Node(ni, nj, k));
                }
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++)
                System.out.print(d[i][j] + " ");
            System.out.println();
        }
        System.out.println(d[end.i][end.j]);
    }
*/