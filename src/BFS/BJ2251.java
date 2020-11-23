package BFS;
//물통 다시 풀어볼 것

import java.util.*;

/*생각하지 못한 이유
1. BFS는 최소비용을 구할때만 쓴다는 고정관념. 얼마든지 원리를 활용할 수 있다.
2. 계산이 너무 많아 (6가지 경우) 주저했다 -> 일반화하면 된다.*/
//팁: 3개의 변수 -> 3차원 배열? No! 2개를 알면 나머지 하나를 구할 수 있다->2차원 배열
/*BFS의 원리
0. 레벨 개념
1. 한 상태(정점)에서 가능한 모든 경우를 다간다 -> 큐
2. 같은 경로를 반복하지 않기 위해 boolean check[]
3. 큐에서 꺼내며 모든 경우 수행(계산) -> for문 일반화 가능
(4. 비용, 이전 방문 정점을 기록할 int[] d, from 등)*/
public class BJ2251 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] cap = new int[3];
        for (int i = 0; i < 3; i++)
            cap[i] = sc.nextInt();
        int total = cap[2];
        boolean[][] check = new boolean[cap[0] + 1][cap[1] + 1]; //c=total-a-b
        boolean[] answer = new boolean[cap[2] + 1]; //a가 0일때 답이므로, a가 0일때만 기록.
        Queue<Integer> q = new ArrayDeque<>();
        final int[] from = {0, 0, 1, 1, 2, 2};
        final int[] to = {1, 2, 0, 1, 0, 1};
        q.offer(0);
        q.offer(0);
        check[0][0] = true;
        answer[cap[2]] = true;
        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            int z = total - x - y;
            for (int i = 0; i < 6; i++) {
                int[] bottle = new int[]{x,y,z};//편리한 초기화 방법
                //전부 붓는다
                bottle[to[i]] += bottle[from[i]];
                bottle[from[i]] = 0;
                //다 부으면 넘칠 경우 일부만 붓는다
                if (bottle[to[i]] > cap[to[i]]) {
                    bottle[from[i]] = bottle[to[i]] - cap[to[i]];
                    bottle[to[i]] = cap[to[i]];
                }
                if (!check[bottle[0]][bottle[1]]) {
                    check[bottle[0]][bottle[1]] = true;
                    q.offer(bottle[0]);
                    q.offer(bottle[1]);
                    if (bottle[0] == 0) answer[bottle[2]] = true;
                }
            }
        }
        for (int i = 0; i <= cap[2]; i++)
            if (answer[i])
                System.out.print(i + " ");
    }
}
