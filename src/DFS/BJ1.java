package DFS;
//연결 요소

import java.util.ArrayList;
import java.util.Scanner;

public class BJ1 {
    static void dfs(ArrayList<Integer>[] g, int v, boolean[] check) {
        check[v] = true;
        for (int vt : g[v])//연결된 점들
            if (!check[vt])
                dfs(g, vt, check);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//정점 갯수
        int m = sc.nextInt();//간선 갯수

        //그래프 할당
        ArrayList<Integer>[] g = (ArrayList<Integer>[]) new ArrayList[n+1];
        for (int i = 1; i <= n; i++)
            g[i] = new ArrayList<>();
        for (int i = 0; i < m; i++)
            g[sc.nextInt()].add(sc.nextInt());

        int cnt = 0;
        boolean[] check = new boolean[n+1];
        //연결요소 파악 by dfs(or bfs)
        for (int v = 1; v <= n; v++)
            if (!check[v]) {
                dfs(g, v, check);
                cnt++;
            }
        System.out.println(cnt);
    }
}
