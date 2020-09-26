package BFS;
import java.io.IOException;
import java.util.*;
//숨바꼭질
public class Q0_1 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        final int max = 100000;
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] d = new int[max + 1]; //거리 비용
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);
        d[n] = 1; //1초 빼줄것
        while (!q.isEmpty()) {
            int x = q.poll();
            if(x==k) break;
            
            //이동 경우 3가지
            if (x + 1 <= max && d[x + 1] == 0) {
                d[x+1]=d[x]+1;
                q.offer(x+1);
            }
            if (x - 1 >= 0 && d[x - 1] == 0) {
                d[x-1]=d[x]+1;
                q.offer(x-1);
            }
            if (2 * x <= max && d[2 * x] == 0) {
                d[2*x]=d[x]+1;
                q.offer(2*x);
            }
        }

        System.out.println(d[k]-1);
    }
}
