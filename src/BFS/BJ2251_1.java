package BFS;

import java.util.*;

/*풀지 못한 이유
 1. 일반화를 못하겠다.
 2. 3차원 배열 -> 너무 커서 hashmap쓰려고 했다.(지난 배움 적용 못함)
 */
/*배운점을 적용 못한 이유
 한번 풀고 넘어가면 100% 습득이 안된다는 방증. 어렴풋이 알게됨..
 ->까먹을 때쯤 다시 풀어서 스스로 발상하도록 해야한다. 공부는 정직하다. 복습. 인내 싸움.
 */
/* 배울점
1. 일반화 스킬 (모든 경우의 수를 따져서 숫자로 표현)
2. 직접적으로 답을 기록하는 방식
3. 수학적 사고 능력 키울 것: 효율적으로 풀려고 하자! x,y 등으로 일반화
 */
public class BJ2251_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] cap = new int[3];
        for (int i = 0; i < 3; i++)
            cap[i] = sc.nextInt();
        boolean[][] check = new boolean[cap[0] + 1][cap[1] + 1];//a,b로 표현 가능
        boolean[] answer = new boolean[cap[2] + 1];//a가 0일때 c의 값
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        q.offer(0);
        check[0][0] = true;
        answer[cap[2]] = true;
        final int[] from = {0, 0, 1, 1, 2, 2};
        final int[] to = {1, 2, 0, 2, 0, 1};

        while (!q.isEmpty()) {
            int a = q.poll();
            int b = q.poll();
            int c = cap[2] - a - b;
            for (int i = 0; i < 6; i++) {//한 정점에서 모든 경우를 다 간다
                int[] btl = {a, b, c};//편리한 초기화 방법
                //일단 전부 붓는다
                btl[to[i]] += btl[from[i]];
                btl[from[i]] = 0;
                if (btl[to[i]] > cap[to[i]]) {//초과하면 조정
                    btl[from[i]] = btl[to[i]] - cap[to[i]];
                    btl[to[i]] = cap[to[i]];
                }
                if (!check[btl[0]][btl[1]]) {
                    q.offer(btl[0]);//다음 레벨
                    q.offer(btl[1]);
                    check[btl[0]][btl[1]] = true;
                    if (btl[0] == 0) answer[btl[2]] = true;
                }
            }
        }

        for (int i = 0; i <= cap[2]; i++)
            if (answer[i]) System.out.print(i + " ");
    }
}
