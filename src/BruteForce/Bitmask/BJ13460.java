package BruteForce.Bitmask;
//구슬 탈출2
//이동한다고 다 bfs는 아니다. 완전 탐색으로 모든 경로를 만들어 두고 이동하는 case
//bfs의 용도와는 조금 다르다. bfs: 시작점 기준 비용

import java.util.Scanner;
//결과 클래스를 만들면 유용
class Result {
    int i, j;
    boolean moved, hole;

    public Result(int i, int j, boolean moved, boolean hole) {
        this.i = i;
        this.j = j;
        this.moved = moved;
        this.hole = hole;
    }
}

public class BJ13460 {
    static int LIMIT = 10;
    static final int[] di = {0, 0, -1, 1};//0, 1, 2, 3
    static final int[] dj = {-1, 1, 0, 0};//왼,오,위,아래
    static int red_i, red_j, blue_i, blue_j;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] map = new String[n];
        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++)
            map[i] = sc.next();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (map[i].charAt(j) == 'B') {
                    blue_i = i;
                    blue_j = j;
                } else if (map[i].charAt(j) == 'R') {
                    red_i = i;
                    red_j = j;
                }
        int ans = -1;
        //4방향, 10회 이동 -> 모든 조합 생성(4^10==2^20)
        for (int d = 0; d < (1 << LIMIT * 2); d++) { //핵심1. 비트마스크 생성
            int[] dir = genDir(d); //핵심2. 비트마스크에서 추출
            if (!valid(dir)) continue;
            for (int i = 0; i < n; i++)
                board[i] = map[i].toCharArray();
            int ret = check(board, dir);
            if (ret != -1 && (ans == -1 || ans > ret)) ans = ret;
        }
        System.out.println(ans);
    }

    private static int[] genDir(int d) {
        int[] dir = new int[LIMIT];
        for (int i = 0; i < LIMIT; i++, d = d >> 2)
            dir[i] = d & 3;//(11)
        return dir;
    }

    private static boolean valid(int[] dir) {
        for (int i = 0; i < LIMIT - 1; i++) {
            if (dir[i] == dir[i + 1]) return false; //같은 방향 연속 의미x
            if (dir[i] == 0 && dir[i + 1] == 1) return false; //반대 방향 연속 의미x
            if (dir[i] == 1 && dir[i + 1] == 0) return false;
            if (dir[i] == 2 && dir[i + 1] == 3) return false;
            if (dir[i] == 3 && dir[i + 1] == 2) return false;
        }
        return true;
    }

    private static int check(char[][] board, int[] dir) {
        int ri = red_i, rj = red_j;
        int bi = blue_i, bj = blue_j;
        int cnt = 0;

        //dir에 적힌 방향대로 이동
        for (int k : dir) {
            cnt++;
            boolean blue_hole = false, red_hole = false;
            while (true) {//R와 B가 움직이지 않을 때까지 이동
                Result red = simulate(board, k, ri, rj);
                Result blue = simulate(board, k, bi, bj);
                ri = red.i;
                rj = red.j;
                bi = blue.i;
                bj = blue.j;
                if (red.hole) red_hole = true;
                if (blue.hole) blue_hole = true;
                if (!red.moved && !blue.moved) break;
            }
            if (blue_hole) return -1;
            if (red_hole) return cnt;
        }
        return -1;
    }

    private static Result simulate(char[][] board, int k, int i, int j) {
        if (board[i][j] == '.') return new Result(i, j, false, false);//이미 홀에 빠진 상태
        boolean moved = false;
        while (true) {
            int ni = i + di[k];
            int nj = j + dj[k];
            char c = board[ni][nj];
            if (c == '.') {
                board[ni][nj] = board[i][j];
                board[i][j] = c;
                i = ni;
                j = nj;
                moved = true;
            } else if (c == 'O') {
                board[i][j] = '.';
                return new Result(i, j, true, true);
            } else return new Result(i, j, moved, false);
        }
    }
}
