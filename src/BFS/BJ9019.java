package BFS;
//DSLR
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

//최단 경로=> 크루스칼, bfs ,, 가중치가 같은 경우 bfs!
public class BJ9019 {
    /*
    static int howerate(int i, int x) {
        if (i == 0) return (2 * x) % max;//D
        else if (i == 1) return x == 0 ? 9999 : x - 1;//S
        else if (i == 2) return (x % 1000) * 10 + x / 1000;//L: 1(234)->(234)1
        else return (x % 10) * 1000 + x / 10;//R:(123)4->4(123)
    }
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int LIMIT = 10000;
        final char[] DSLR = {'D','S','L','R'};
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String s[] = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            boolean[] check = new boolean[LIMIT];
            int[] from =new int[LIMIT];
            char[] how = new char[LIMIT];
            Queue<Integer> q = new ArrayDeque<>();
            check[a] = true;
            q.offer(a);

            while (!q.isEmpty()) {
                int x = q.poll();
                if (x == b) break;
                //연산부
                int i=0;
                for(int next: new int[]{2 * x % LIMIT, x == 0 ? 9999 : x - 1, (x % 1000) * 10 + (x / 1000), (x % 10) * 1000 + (x / 10)}){
                    //int next = operate(i, x);
                    if (!check[next]) {
                        check[next] = true;
                        from[next] =x;
                        how[next]=DSLR[i];
                        q.offer(next);
                    }
                    i++;
                }
            }

            StringBuilder answer = new StringBuilder();
            while(b!=a){
               answer.append(how[b]);
               b=from[b];
            }
            System.out.println(answer.reverse());
        }
    }
}
