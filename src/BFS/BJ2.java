package BFS;
//DSLR

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
//최소 비용이 아닌 최소 '경로'를 구하는 문제로 BFS로 풀 수 있다. 큐를 이용해서 최소 경로로 갔기 때문에.

public class BJ2 {
    static final int max = 10000;

    static int operate(int i, int x) {
        if (i == 0) return (2 * x) % max;//D
        else if (i == 1) return x == 0 ? 9999 : x - 1;//S
        else if (i == 2) return (x % 1000) * 10 + x / 1000;//L: 1(234)->(234)1
        else return (x % 10) * 1000 + x / 10;//R:(123)4->4(123)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        final char[] operator = {'D', 'S', 'L', 'R'};
        while (t-- > 0) {
            int a = sc.nextInt();//start
            int b = sc.nextInt();//end
            boolean[] check = new boolean[max];//0000~9999
            int[] from = new int[max];//숫자가 노드
            char[] how = new char[max]; // how[v]=v가 되기 위해 쓴 방법
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(a);
            check[a] = true;
            while (!q.isEmpty()) {
                int x = q.poll();
                if (x == b) break;
                //연산
                for (int i = 0; i < 4; i++) {
                    int next = operate(i, x);
                    if (!check[next]) {
                        q.offer(next);
                        check[next] = true;
                        from[next] = x;
                        how[next] = operator[i];
                    }
                }
            }

            StringBuilder ans = new StringBuilder();
            while (b != a) {
                ans.append(how[b]);
                b = from[b];
            }
            System.out.println(ans.reverse());//뒤집어 출력!
        }
    }
}
