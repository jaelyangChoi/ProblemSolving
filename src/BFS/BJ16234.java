package BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class BJ16234 {
    final static int[] dx = {0, 0, -1, 1};
    final static int[] dy = {1, -1, 0, 0};

    static boolean bfs(int[][] a, int l, int r) {
        int n = a.length;
        boolean[][] check = new boolean[n][n];
        boolean moved = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!check[i][j]) {
                    check[i][j] = true;
                    Queue<Integer> q = new ArrayDeque<>();
                    q.add(i);
                    q.add(j);
                    ArrayList<Integer> union = new ArrayList<>();
                    int population = 0;
                    while (!q.isEmpty()) {
                        int x = q.remove();
                        int y = q.remove();
                        union.add(x);
                        union.add(y);
                        population += a[x][y];
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                                int diff = Math.abs(a[x][y] - a[nx][ny]);
                                if (!check[nx][ny] && l <= diff && diff <= r) {
                                    check[nx][ny] = true;
                                    q.add(nx);
                                    q.add(ny);
                                }
                            }
                        }
                    }
                    if (union.size() > 2) {
                        int avg = population / (union.size() / 2);
                        for (int k = 0; k < union.size(); k += 2)
                            a[union.get(k)][union.get(k + 1)] = avg;
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = sc.nextInt();

        int ans = 0;
        while (true) {
            if (bfs(a, l, r))
                ans++;
            else
                break;
        }
        System.out.println(ans);
    }
}
