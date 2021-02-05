package BFS;
//열쇠
//진행큐와 대기큐 활용
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BJ9328 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int[] di = {0, 0, -1, 1};
        final int[] dj = {1, -1, 0, 0};
        int t = sc.nextInt();
        while (t-- > 0) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            char[][] map = new char[h + 4][w + 4];
            boolean[][] check = new boolean[h + 4][w + 4];
            boolean[] key = new boolean['z' - 'a' + 1];
            for (int i = 2; i < h + 2; i++) {
                String temp = sc.next();
                for (int j = 0; j < w; j++)
                    map[i][j + 2] = temp.charAt(j);
            }
            //padding. 범위 검사하지 않기 위해
            h += 4;
            w += 4;
            for (int i = 0; i < h; i++) {
                map[i][0] = '*';
                map[i][1] = '.';
                map[i][w - 2] = '.';
                map[i][w - 1] = '*';
            }
            for (int j = 1; j < w - 1; j++) {
                map[0][j] = '*';
                map[1][j] = '.';
                map[h - 2][j] = '.';
                map[h - 1][j] = '*';
            }
            
            String keys = sc.next();
            if (!keys.equals("0"))
                for (char c : keys.toCharArray())
                    key[c - 'a'] = true;

            int ans = 0;
            Queue<Integer> q = new ArrayDeque<>();//진행 큐
            Queue<Integer>[] waitingQ = new ArrayDeque[26]; //A~Z 문 앞에서 대기
            for (int i = 0; i < 26; i++)
                waitingQ[i] = new ArrayDeque<>();
            q.offer(1);//시작점
            q.offer(1);
            check[1][1] = true;

            while (!q.isEmpty()) {
                int i = q.poll();
                int j = q.poll();
                for (int k = 0; k < 4; k++) {
                    int ni = i + di[k];
                    int nj = j + dj[k];
                    if (check[ni][nj]) continue;
                    char ch = map[ni][nj];
                    if (ch == '*') continue;

                    check[ni][nj] = true;
                    if (ch == '.') {
                        q.offer(ni);
                        q.offer(nj);
                    } else if (ch == '$') {
                        ans++;
                        q.offer(ni);
                        q.offer(nj);
                    } else if (ch >= 'A' && ch <= 'Z') {//문 앞
                        if (key[ch - 'A']) {//키가 있으면 진행
                            q.offer(ni);
                            q.offer(nj);
                        } else {//없으면 대기
                            waitingQ[ch - 'A'].offer(ni);
                            waitingQ[ch - 'A'].offer(nj);
                        }
                    } else if (ch >= 'a' && ch <= 'z') {//키 줍
                        q.offer(ni);
                        q.offer(nj);
                        if (key[ch - 'a']) continue;
                        key[ch - 'a'] = true;
                        while (!waitingQ[ch - 'a'].isEmpty())//대기 중이던 경로 계속 진행
                            q.offer(waitingQ[ch - 'a'].poll());
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
