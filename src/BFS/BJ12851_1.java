package BFS;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BJ12851_1 {
    static final int max = 100000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] d = new int[max + 1];
        int[] cnt = new int[3 * max];//cnt[i]: i로 최단 경로로 오는 방법의 수
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);
        d[n] = 1;//-1 해줄것
        cnt[n] = 1;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : new int[]{now - 1, now + 1, 2 * now}) {
                if (next >= 0 && next <= max) {
                    if (d[next] == 0) {//방문한 적 now
                        d[next] = d[now] + 1;
                        q.offer(next);
                        cnt[next] = cnt[now];
                    } else if (d[next] == d[now] + 1)//방문o, 최단 경로
                        cnt[next] += cnt[now];
                }
            }
        }
        System.out.println(d[k] - 1);
        System.out.println(cnt[k]);
    }
}
