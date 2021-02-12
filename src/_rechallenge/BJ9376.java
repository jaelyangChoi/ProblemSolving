package _rechallenge;
//탈옥 https://www.acmicpc.net/problem/9376

//가중치 생각을 못했다..
//덱일 경우 i,j 넣는 순서 주의!!!
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class BJ9376 {
    static int[] di = new int[]{0, 0, -1, 1};
    static int[] dj = new int[]{-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] temp = br.readLine().split(" ");
            int h = Integer.parseInt(temp[0]);
            int w = Integer.parseInt(temp[1]);
            String[] map = new String[h + 2];
            //패딩
            int si1 = -1, sj1 = -1, si2 = -1, sj2 = -1;
            for (int i = 1; i <= h; i++) {
                map[i] = '.' + br.readLine() + '.';
                for (int j = 1; j <= w; j++)
                    if (map[i].charAt(j) == '$') {
                        if (si1 == -1) {
                            si1 = i;
                            sj1 = j;
                        } else {
                            si2 = i;
                            sj2 = j;
                        }
                    }
            }
            map[0] = map[h + 1] = "";
            for (int i = 0; i < w + 2; i++) {
                map[0] += '.';
                map[h + 1] += '.';
            }

            //각각 출발점으로부터 모든 지점의 비용을 구한다
            int[][] start1 = bfs(map, 0, 0); //임의의 시작점을 (0,0)으로 설정
            int[][] start2 = bfs(map, si1, sj1);
            int[][] start3 = bfs(map, si2, sj2);
//            for (int i = 0; i < h + 2; i++) {
//                for (int j = 0; j < w + 2; j++) {
//                    System.out.print(start1[i][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//            for (int i = 0; i < h + 2; i++) {
//                for (int j = 0; j < w + 2; j++) {
//                    System.out.print(start2[i][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//            for (int i = 0; i < h + 2; i++) {
//                for (int j = 0; j < w + 2; j++) {
//                    System.out.print(start3[i][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
            // 비용 합이 최소인 곳이 중간 지점이 된다.
            int ans = w * h; //모든 점이 문이고 다 따는 경우
            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    if (start1[i][j] == -1 || start2[i][j] == -1 || start3[i][j] == -1) continue;
                    int sum = start1[i][j] + start2[i][j] + start3[i][j];
                    if (map[i].charAt(j) == '#') sum -= 2; //문일 경우 중복 비용 2 제거
                    if (ans > sum) ans = sum;
                }
            }
            System.out.println(ans);
        }
    }

    private static int[][] bfs(String[] map, int startI, int startJ) {
        int h = map.length, w = map[0].length();
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        int[][] dist = new int[h][w];
        for (int i = 0; i < h; i++)
            Arrays.fill(dist[i], -1);

        dist[startI][startJ] = 0;
        deq.offerLast(startI);
        deq.offerLast(startJ);
        while (!deq.isEmpty()) {
            int i = deq.pollFirst();
            int j = deq.pollFirst();
            for (int k = 0; k < 4; k++) {
                int ni = i + di[k];
                int nj = j + dj[k];
                if (ni < 0 || ni >= h || nj < 0 || nj >= w) continue;
                if (dist[ni][nj] != -1) continue;
                char ch = map[ni].charAt(nj);
                if (ch == '*') continue;
                dist[ni][nj] = dist[i][j];
                if (ch == '#') { //가중치 1인 경우
                    dist[ni][nj]++;
                    deq.offerLast(ni);
                    deq.offerLast(nj);
                } else { //가중치 0인 경우
                    deq.offerFirst(nj);
                    deq.offerFirst(ni);//덱일 경우 순서주의!!
                }
            }
        }
        return dist;
    }
}
