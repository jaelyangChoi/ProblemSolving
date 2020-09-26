package DFS;

import java.util.ArrayList;
//네트워크
public class Q2 {
    static boolean check[];
    static ArrayList<Integer>[] g;//인접 리스트(리스트 배열)

    static public int solution(int n, int[][] computers) {
        check = new boolean[n];
        g = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++)
            g[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                if (i!=j&&computers[i][j] == 1)
                    g[i].add(j);
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (!check[i]) {
                answer++;
                dfs(g[i]);
            }
        }
        return answer;
    }

    private static void dfs(ArrayList<Integer> network) {
        for (int c : network) {
            if(check[c]) continue;
            check[c] = true;
            dfs(g[c]);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        //System.out.println(solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
    }
}
