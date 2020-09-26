package Graph;
import java.util.*;
//연결 요소
public class Q2 {
    static ArrayList<Integer>[] g;
    static boolean[] check;
    static int n;

    static void dfs(Integer v) {
        check[v] = true;
        for (int vt : g[v]) {
            if (!check[vt])
                dfs(vt);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int m = sc.nextInt();

        g = (ArrayList<Integer>[]) new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            g[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            g[from].add(to);
            g[to].add(from);
        }
        check = new boolean[n + 1];

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if(!check[i]){
                dfs(i);
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
