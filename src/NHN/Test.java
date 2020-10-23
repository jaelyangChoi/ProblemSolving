package NHN;

import java.util.*;

public class Test {
    private static void solution(int sizeOfMatrix, int[][] matrix) {
        int num = 0;
        ArrayList<Integer> l = new ArrayList<>();
        boolean[][] check = new boolean[sizeOfMatrix][sizeOfMatrix];
        Queue<Integer> q = new ArrayDeque<>();
        final int[] di = {0, 0, 1, -1};//오,왼,아래,위
        final int[] dj = {1, -1, 0, 0};

        for (int i = 0; i < sizeOfMatrix; i++) {
            for (int j = 0; j < sizeOfMatrix; j++) {
                if (matrix[i][j] == 0) continue;
                if (check[i][j]) continue;
                check[i][j] = true;
                int cnt = 1;
                q.offer(i);
                q.offer(j);
                while (!q.isEmpty()) {
                    int r = q.poll();
                    int c = q.poll();
                    for (int k = 0; k < 4; k++) {
                        int ni = r + di[k];
                        int nj = c + dj[k];
                        if (ni < 0 || nj < 0 || ni >= sizeOfMatrix || nj >= sizeOfMatrix || check[ni][nj]) continue;
                        if(matrix[ni][nj]==1) {
                            check[ni][nj] = true;
                            cnt++;
                            q.offer(ni);
                            q.offer(nj);
                        }
                    }
                }
                num++;
                l.add(cnt);
            }
        }
        System.out.println(num);
        Collections.sort(l);
        for (int e : l)
            System.out.print(e + " ");
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = sc.nextInt();
        solution(n, arr);
    }
}
