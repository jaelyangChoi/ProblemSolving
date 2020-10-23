package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Q1_1 {
    static public int solution(int n, int[][] edge) {
        ArrayList<Integer>[] g = (ArrayList<Integer>[]) new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            g[i] = new ArrayList<>();
        for (int[] e : edge) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        boolean[] check = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        check[1] = true;
        q.offer(1);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int vt : g[v]) {
                if (check[vt]) continue;
                q.offer(vt);
                check[vt] = true;
                dist[vt] = dist[v] + 1;
            }
        }
        int max = 0;
        int cnt = 0;
        for (int d : dist) {
            if (d > max) {
                max = d;
                cnt = 1;
            } else if (d == max) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }
}
