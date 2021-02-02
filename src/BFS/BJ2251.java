package BFS;
//물통

import java.util.*;

/*
 생각하지 못한 이유
1. BFS는 최소비용을 구할때만 쓴다는 고정관념. 얼마든지 원리를 활용할 수 있다. -> 모든 경우 순회
2. 계산이 너무 많아 (6가지 경우) 주저했다 -> 일반화하면 된다.
*/

//팁: 3개의 변수 -> 3차원 배열? No! 2개를 알면 나머지 하나를 구할 수 있다->2차원 배열

/*
BFS의 원리
0. 한 상태(정점)에서 가능한 모든 경우를 다간다
1. 레벨 개념 -> 큐
2. 큐에서 꺼내며 모든 경우 수행(계산) -> for문 일반화 가능
3. 같은 경로를 반복하지 않기 위해 boolean check[]
(4. 비용, 이전 방문 정점을 기록할 int[] d, from 등)
*/
public class BJ2251 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] max = new int[3]; //A,B,C
        final int[] from = {0, 0, 1, 1, 2, 2};
        final int[] to = {1, 2, 0, 2, 0, 1};
        for (int i = 0; i < 3; i++)
            max[i] = sc.nextInt();
        final int total = max[2];
        boolean[][] check = new boolean[max[0] + 1][max[1] + 1];//A,B -> C
        boolean[] ans = new boolean[max[2] + 1];
        Queue<Integer> q = new ArrayDeque<>();
        check[0][0] = true;
        q.offer(0);
        q.offer(0);

        while (!q.isEmpty()) {
            int a = q.poll();
            int b = q.poll();
            int c = total - a - b;

            for (int k = 0; k < 6; k++) {
                int[] next = {a, b, c};
                next[to[k]] += next[from[k]];
                next[from[k]] = 0;
                if (next[to[k]] > max[to[k]]) {
                    next[from[k]] = next[to[k]] - max[to[k]];
                    next[to[k]] = max[to[k]];
                }
                if (check[next[0]][next[1]]) continue;
                check[next[0]][next[1]] = true;
                q.offer(next[0]);
                q.offer(next[1]);
                if (next[0] == 0) ans[next[2]] = true; //센스: 계산 과정에서 답까지 기록!
            }
        }

        for (int i = 0; i <= max[2]; i++)
            if (ans[i]) System.out.print(i + " ");
    }
}
