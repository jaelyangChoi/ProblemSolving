import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class BJ16928 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt() + sc.nextInt();
        int[] jump = new int[101];
        for (int i = 1; i <= 100; i++)
            jump[i] = i;
        for (int i = 0; i < m; i++)
            jump[sc.nextInt()] = sc.nextInt();

        int[] dist = new int[101];
        Arrays.fill(dist, -1);
        dist[1] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int move = 1; move <= 6; move++) {
                int next = cur + move;
                if (next <= 100) {
                    next = jump[next];
                    if (dist[next] == -1) {
                        dist[next] = dist[cur] + 1;
                        q.add(next);
                    }
                }
            }
        }
        System.out.println(dist[100]);
    }
}
