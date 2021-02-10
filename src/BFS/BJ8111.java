package BFS;
// 0과 1

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
/* 왜 틀렸을까?
1. String을 bfs로 기록하는 건 에바. 문자열이 길어서 합치는데 메모리 초과, 시간 초과 => from, how 활용!
2. 너무 복잡한 연산은 시간이 너무 오래걸린다. 아니다 싶으면 과감히 접어라
3. from만 생각하고 how를 못 떠올렸다. from으로 다 해결하려 했으니.. 생각이 틀에 갇혀있다. 틀을 깨자!
*/

/* 공략
1. 길이 최대 100 => 2^100.. 너무 길다.
2. 관심있는 것은 나머지이므로, 나머지만 추적한다. => N개로 줄어든다!
   모든 연산을 했는데 나머지가 같다면 더 진행해봐야 제자리걸음!
   *나머지 관련 연산: (A+B)%C = (A%C+B%C)%C 나눠서 0인 부분을 날려도 나머지는 같기 때문!
*/
public class BJ8111 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int START = -1;
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            // 수 x에 1,0 붙이며 탐색해본다. (한 x에 대해 x0,x1 => bfs)
            //2^100 너무 많다. 나머지에만 관심있으니 나머지만 넘기면 됨. 0~n-1 번 검사. 중복되면 더 할 필요x
            Queue<Integer> q = new ArrayDeque<>();
            boolean[] check = new boolean[n];//0~n-1
            int[] from = new int[n]; //0~n-1. 중복되는 나머지는 더 검사하지 않으므로 유일.
            char[] how = new char[n];

            int r = 1 % n;
            q.offer(r);
            from[r] = START;
            how[r] = '1';
            check[r] = true;
            while (!q.isEmpty()) {
                r = q.poll();
                for (int i = 0; i <= 1; i++) {
                    int next = (r * 10 + i) % n; //나머지 연산에 의해, 나머지만 고려해도 된다.
                    if (check[next]) continue;
                    check[next] = true;
                    from[next] = r;
                    how[next] = i == 0 ? '0' : '1';
                    q.offer(next);
                    if (next == 0) break;
                }
            }

            if (check[0]) { //나누어 떨어지는 경우
                StringBuilder ans = new StringBuilder();
                for (int i = 0; i != START; i = from[i])
                    ans.append(how[i]);
                System.out.println(ans.reverse().toString());
            } else
                System.out.println("BRAK");
        }
    }
}
