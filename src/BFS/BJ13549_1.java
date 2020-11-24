package BFS;

import java.util.ArrayDeque;
import java.util.Scanner;

//숨바꼭질3. 가중치가 다른 경우
public class BJ13549_1 {
    static final int max = 100000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        boolean[] check = new boolean[max + 1]; //방문 기록
        int[] dist = new int[max + 1]; //v까지의 거리 = dist[v]
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        deq.offerFirst(n);
        dist[n] = 0;
        check[n] = true;
        while (!deq.isEmpty()) {
            int now = deq.pollFirst();
            for (int next : new int[]{now - 1, now + 1, now * 2}) {
                if (next >= 0 && next <= max && !check[next]) {
                    check[next] = true;
                    if (next == now * 2) {//가중치가 0이면 앞에 넣어서 바로 꺼내본다
                        deq.offerFirst(next);
                        dist[next] = dist[now];
                    } else {//가중치가 다르면 뒤에 넣고 0짜리 다보고 나중에 꺼내본다
                        deq.offerLast(next);
                        dist[next] = dist[now] + 1;
                    }
                }
            }
        }
        System.out.println(dist[k]);
    }
}
