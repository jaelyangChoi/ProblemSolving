package BFS;
//로봇 청소기

//알고리즘이 틀렸다. 틀리고 나서야 반례를 찾게 된다.. 미리 if문 짠다 치고 여러 케이스를 들어봤어야..
//그리디는 검증이 제일 중요..!
//크루스칼 알고리즘은 최소 간선이 같은 영역에 속하지 않아야 병합한다..!

import java.util.*;

/*bfs를 여러번 해서 dist[s][e] 배열을 채운다!*/
//bfs: 두 점 사이의 최단 거리
//but 순서가 중요. 10개 조건 -> 10! 순열!
public class BJ4991 {
    static final int[] di = {0, 0, -1, 1};
    static final int[] dj = {-1, 1, 0, 0};

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int w = sc.nextInt();
            int h = sc.nextInt();
            if (w == 0 && h == 0) break;
            char[][] arr = new char[h][w];
            ArrayList<Point> points = new ArrayList<>();
            points.add(new Point(-1, -1));//나중에 시작점을 바꾸기 위해
            for (int i = 0; i < h; i++) {
                String temp = sc.next();
                for (int j = 0; j < w; j++) {
                    arr[i][j] = temp.charAt(j);
                    if (arr[i][j] == 'o') points.set(0, new Point(i, j));//시작점
                    else if (arr[i][j] == '*') points.add(new Point(i, j));
                }
            }
            boolean ok = true;
            //각 시작점으로부터 다른 지점까지의 최단거리를 구해 dist[s][e]를 채운다
            int len = points.size();
            int[][] dist = new int[len][len];
            for (int s = 0; s < len; s++) {
                int[][] d = bfs(arr, points.get(s));
                for (int e = 0; e < len; e++) {
                    dist[s][e] = d[points.get(e).i][points.get(e).j];
                    if (dist[s][e] == -1) ok = false;
                }
            }
            //모든 이동경로 순서를 만들어 최단 경로를 찾는다. -> 순열
            int ans = -1;
            if (ok) {
                int[] path = new int[len - 1];//시작점 0은 고정이므로 고려x
                for (int i = 0; i < len - 1; i++)
                    path[i] = i + 1;
                do {
                    int cur = dist[0][path[0]];
                    for (int i = 0; i < len - 2; i++)
                        cur += dist[path[i]][path[i + 1]];
                    if (ans == -1 || ans > cur) ans = cur;
                } while (next_permutation(path));
            }
            System.out.println(ans);
        }
    }

    private static int[][] bfs(char[][] arr, Point point) {
        final int h = arr.length, w = arr[0].length;
        Queue<Point> q = new ArrayDeque<>();
        int[][] d = new int[h][w];
        for (int[] di : d) //-1로 d 초기화
            Arrays.fill(di, -1);
        q.offer(point);
        d[point.i][point.j] = 0;
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int k = 0; k < 4; k++) {
                int ni = p.i + di[k];
                int nj = p.j + dj[k];
                if (ni < 0 || ni >= h || nj < 0 || nj >= w) continue;
                if (d[ni][nj] != -1 || arr[ni][nj] == 'x') continue; //X,x, O,o 주의.. 에러 찾기 힘들다
                d[ni][nj] = d[p.i][p.j] + 1;
                q.offer(new Point(ni, nj));
            }
        }
        return d;
    }

    //사전 순으로 다음에 오는 순열로 만든다. (오름차순)
    public static boolean next_permutation(int[] arr) {
        //마지막 부분 순열의 경계 찾기(앞으로 이동하며 내림차순의 끝)
        int i = arr.length - 1; //경계를 가리킴 (맨 끝부터)
        while (i > 0 && arr[i - 1] >= arr[i])
            i--;
        if (i <= 0) return false;//마지막 순열까지 다옴

        //마지막 부분 순열을 첫 부분 순열로 바꾸는 과정
        // 1.경계를 다음 수로 교환한다.
        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1])//현재보다 다음으로 큰 수를 찾기 위해
            j--;

        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        // 2.첫 부분 순열 만들기(경계 이후를 오름차순으로 바꾼다)
        j = arr.length - 1;
        while (i < j) {
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            j--;
            i++;
        }
        return true;
    }
}
