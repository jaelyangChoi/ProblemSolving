package _rechallenge;
//열쇠

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BJ9328 {
    public static void main(String[] args) throws IOException {
        final int[] dr = {0, 0, -1, 1};
        final int[] dc = {-1, 1, 0, 0};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] temp = br.readLine().split(" ");
            int h = Integer.parseInt(temp[0]);
            int w = Integer.parseInt(temp[1]);
            char[][] map = new char[h + 2][w + 2];
            for (int j = 0; j < w + 2; j++)
                map[0][j] = map[h + 1][j] = '.';
            for (int i = 1; i < h + 1; i++) {
                String str = br.readLine();
                map[i][0] = map[i][w + 1] = '.';
                for (int j = 1; j < w + 1; j++)
                    map[i][j] = str.charAt(j - 1);
            }
            boolean[] keys = new boolean['z' - 'a' + 1];
            String str = br.readLine();
            if (!str.equals("0"))
                for (char c : str.toCharArray())
                    keys[c - 'a'] = true;

            h += 2;
            w += 2;
            Queue<Integer> q = new ArrayDeque<>();
            Queue<Integer>[] waitingQ = new ArrayDeque['Z' - 'A' + 1]; //열쇠 주울 때까지 대기
            for (int i = 0; i < waitingQ.length; i++)
                waitingQ[i] = new ArrayDeque<>();
            boolean[][] check = new boolean[h][w];
            int ans = 0;

            q.offer(0);
            q.offer(0);
            check[0][0] = true;
            while (!q.isEmpty()) {
                int r = q.poll();
                int c = q.poll();
                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
                    if (check[nr][nc]) continue;
                    char ch = map[nr][nc];
                    if (ch == '*') continue;

                    check[nr][nc] = true;
                    if (ch == '$') ans++;
                    else if (ch >= 'A' && ch <= 'Z') { //문 앞
                        if (!keys[ch - 'A']) { //열쇠 없을 경우, 대기
                            waitingQ[ch - 'A'].offer(nr);
                            waitingQ[ch - 'A'].offer(nc);
                            continue;
                        }
                    } else if (ch >= 'a' && ch <= 'z') { // 키 주은 경우, 대기 해체
                        keys[ch - 'a'] = true;
                        while (!waitingQ[ch - 'a'].isEmpty())
                            q.offer(waitingQ[ch - 'a'].poll());
                    }
                    q.offer(nr);
                    q.offer(nc);
                }
            }
            System.out.println(ans);
        }
    }
}
