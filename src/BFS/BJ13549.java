package BFS;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

//숨바꼭질3, 가중치가 다른 경우
public class BJ13549 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        final int max = 100000;
        boolean[] check = new boolean[max + 1]; //방문 기록
        int[] dist = new int[max + 1]; //v까지의 거리 = dist[v]
        Queue<Integer> q = new ArrayDeque<>();//비용이 0인 경우
        Queue<Integer> nextQ = new ArrayDeque<>();//비용이 1인 경우

        q.offer(n);
        check[n] = true;
        while (true) {
            int x = q.poll();
            if (x == k) break;
            for (int next : new int[]{2 * x, x + 1, x - 1}) {//x로 배열 만들기!
                if (next >= 0 && next <= max && !check[next]) {
                    check[next]=true;
                    if (next == 2 * x) {
                        dist[next] = dist[x];//가중치 0이므로
                        q.offer(next);
                    } else {
                        dist[next] = dist[x] + 1; //가중치 1
                        nextQ.offer(next);
                    }
                }
            }

            if (q.isEmpty()) {
                Queue<Integer> temp = q;
                q = nextQ;
                nextQ = temp;
            }
        }
        System.out.println(dist[k]);
    }
}
