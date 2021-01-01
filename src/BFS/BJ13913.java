package BFS;
//숨바꼭질4

import java.util.*;

public class BJ13913 {
    //역순 출력
    static void print(int[] from, int start, int node) {
        if (node != start)
            print(from, start, from[node]);
        System.out.print(node + " ");
    }

    public static void main(String[] args) {
        final int limit = 100001;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        boolean[] check = new boolean[limit];
        int[] dist = new int[limit];//시작 위치 n부터의 비용
        int[] from = new int[limit];//이전 위치
        Queue<Integer> q = new ArrayDeque<>();
        check[n] = true;
        q.offer(n);

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int next : new int[]{x - 1, x + 1, 2 * x}) {
                if (next < 0 || next >= limit) continue;
                if (check[next]) continue;//방문한 곳을 재방문하지 않으면 최소 비용만 기록됨
                check[next] = true;
                dist[next] = dist[x] + 1;
                from[next] = x;
                q.offer(next);
            }
        }

        System.out.println(dist[k]);
//        ArrayDeque<Integer> stack = new ArrayDeque<>();
//        while(k!=n){
//            stack.offerFirst(k);
//            k=from[k];
//        }
//        stack.offerFirst(k);
//        for(int e: stack)
//            System.out.print(e+" ");
        print(from, n, k);
    }
}
