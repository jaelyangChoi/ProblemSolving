package _rechallenge;
//로봇 청소기
//크루스칼 알고리즘은 시작점 기준 각 정점까지의 최단 경로..!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class BJ4991 {
    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static final int[] di = {0, 0, -1, 1};
    static final int[] dj = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String[] temp = br.readLine().split(" ");
            int w = Integer.parseInt(temp[0]);
            int h = Integer.parseInt(temp[1]);
            if (w == 0 && h == 0) break;
            String[] map = new String[h];
            ArrayList<Point> points = new ArrayList<>();
            points.add(new Point(-1, -1)); //나중에 시작점 바꾸기 위해 일단 자리 잡음
            for (int i = 0; i < h; i++) {
                map[i] = br.readLine();
                for (int j = 0; j < w; j++)
                    if (map[i].charAt(j) == 'o')
                        points.set(0, new Point(i, j)); //시작점 바꿈
                    else if (map[i].charAt(j) == '*')
                        points.add(new Point(i, j));
            }
            //지점들 간의 거리를 구한다.
            int len = points.size();
            int[][] dist = new int[len][len];//dist[s][e]:s~e간 거리
            boolean ok = true;
            for (int s = 0; s < len; s++) {
                int[][] d = bfs(map, points.get(s));
                for (int e = 0; e < len; e++) {
                    Point p = points.get(e);
                    dist[s][e] = d[p.i][p.j];
                    if (dist[s][e] == -1) ok = false;
                }
            }
            //모든 경로를 구해 각각 경로마다의 거리합을 구한다. ->순서 => 순열
            if (!ok) System.out.println(-1);
            else {
                int ans = Integer.MAX_VALUE;
                int[] path = new int[len - 1];
                for (int i = 0; i < len - 1; i++)
                    path[i] = i + 1;

                do {
                    int sum = dist[0][path[0]];
                    for (int i = 0; i < len - 2; i++)
                        sum += dist[path[i]][path[i + 1]];
                    ans = Math.min(ans, sum);
                } while (next_permutation(path));

                System.out.println(ans);
            }
        }
    }

    private static int[][] bfs(String[] map, Point start) {
        final int h = map.length, w = map[0].length();
        int[][] d = new int[h][w];
        for (int i = 0; i < h; i++)
            Arrays.fill(d[i], -1);
        Queue<Point> q = new ArrayDeque<>();
        q.offer(start);
        d[start.i][start.j] = 0;
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int k = 0; k < 4; k++) {
                int ni = now.i + di[k];
                int nj = now.j + dj[k];
                if (ni < 0 || ni >= h || nj < 0 || nj >= w) continue;
                if (d[ni][nj] != -1) continue;
                if (map[ni].charAt(nj) == 'x') continue;
                d[ni][nj] = d[now.i][now.j] + 1;
                q.offer(new Point(ni, nj));
            }
        }
        return d;
    }

    //사전 순으로 다음에 오는 순열로 만든다. (내림차순) 1,2 -> 2,1
    public static boolean next_permutation(int[] arr) {
        //마지막 부분 순열의 경계 찾기(앞으로 이동하며 내림차순의 끝)
        int i = arr.length - 1; //경계를 가리킴 (맨 끝부터)
        while (i > 0 && arr[i - 1] >= arr[i])
            i--;
        if (i <= 0) return false;//마지막 순열까지 다옴

        //마지막 부분 순열을 첫 부분 순열로 바꾸는 과정
        // 1.경계를 다음 수로 교환한다.
        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1])//현재보다 다음으로 큰 수를 찾기 위해
            j--;

        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        // 2.첫 부분 순열 만들기(경계 이후를 오름차순으로 바꾼다)
        j = arr.length - 1;
        while (i < j) {
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            j--;
            i++;
        }
        return true;
    }
}
