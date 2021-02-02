package BFS;
//숨바꼭질2

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

//완탐은 느리고, DP는 빠르다.(메모하므로 중복 계산x)
//고칠점: 방문할 때만 연산하려는 고정관념에서 벗어나라! 최소한 고민은 해보자!
public class BJ12851 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int limit = 100001;
        int n = sc.nextInt();
        int k = sc.nextInt();
        Queue<Integer> q = new ArrayDeque<>();
        int[] dist = new int[limit]; //check 겸
        int[] how = new int[limit]; //how[x]: x까지 오는 방법 수
        dist[n] = 0;
        how[n] = 1;
        q.offer(n);

        while (!q.isEmpty()) {
            int x = q.poll();
            if (x == k) break;
            for (int next : new int[]{x - 1, x + 1, 2 * x}) {
                if (next < 0 || next >= limit) continue;
                if (dist[next] == 0) { //방문한 적x
                    dist[next] = dist[x] + 1;
                    how[next] = how[x];
                    q.offer(next);
                } else if (dist[next] == dist[x] + 1) //이미 방문. but 거리가 같다면
                    how[next] += how[x];
            }
        }

        System.out.println(dist[k]);
        System.out.println(how[k]);
    }
}
