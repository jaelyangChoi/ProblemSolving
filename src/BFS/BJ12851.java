package BFS;
//숨바꼭질2 다시풀기

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

//완탐은 느리고, DP는 빠르다.(메모하므로 중복 계산x)
//DP 쓸때 배열의 정의를 확실히
//고칠점: 방문할 때만 연산하려는 고정관념에서 벗어나라! 최소한 고민은 해보자!
//팁: 어떻게 처리해야할지 모르겠을 땐 상황을 그림으로 그려봐라!
public class BJ12851 {
    static final int max = 100000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        boolean[] check = new boolean[max + 1];
        int[] dist = new int[max + 1];
        int[] cnt = new int[max + 1]; //DP cnt[i] = i까지 가는 방법의 갯수 cnt[i-1]+cnt[i+1]+cnt[i/2]
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);
        check[n] = true;
        cnt[n] = 1;
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int nx : new int[]{x - 1, x + 1, 2 * x}) {
                if (nx < 0 || nx > max) continue;
                if (!check[nx]) {
                    q.offer(nx);
                    check[nx] = true;
                    dist[nx] = dist[x] + 1;
                    cnt[nx] = cnt[x];//첫 방문
                }//첫 방문 아닐때, 방문은 하지 않지만 DP로 기록은 해야지
                else if (dist[nx] == dist[x] + 1)//상황을 그려봐야 이해된다
                    cnt[nx] += cnt[x];
            }
        }
        System.out.println(dist[k]);
        System.out.println(cnt[k]);
    }
}
