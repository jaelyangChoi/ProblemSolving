package BFS;
//숨바꼭질4, 경로를 기록해야 하는 경우

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BJ5 {
    //역순 출력
    static void print(int[] from, int start, int node) {
        if (node != start)
            print(from, start, from[node]);
        System.out.print(node + " ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        final int max = 100000;
        boolean[] check = new boolean[max + 1]; //방문 기록
        int[] dist = new int[max + 1]; //v까지의 거리 = dist[v]
        int[] from = new int[max + 1]; //v에 오기전 노드 = from[v]
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);
        check[n] = true;
        dist[n] = 0;
        //각 노드에서 방문할 수 있는 모든 지점을 방문하고 큐에 넣어 같은 레벨(시간,비용) 유지
        while (!q.isEmpty()) {
            int x = q.poll();
            if (x - 1 >= 0 && !check[x - 1]) {
                q.offer(x - 1);
                check[x - 1] = true;
                dist[x - 1] = dist[x] + 1;
                from[x - 1] = x;
            }
            if (x + 1 <= max && !check[x + 1]) {
                q.offer(x + 1);
                check[x + 1] = true;
                dist[x + 1] = dist[x] + 1;
                from[x + 1] = x;
            }
            if (2 * x <= max && !check[2 * x]) {
                q.offer(2 * x);
                check[2 * x] = true;
                dist[2 * x] = dist[x] + 1;
                from[2 * x] = x;
            }
        }
        System.out.println(dist[k]);
        print(from, n, k);
    }
}
