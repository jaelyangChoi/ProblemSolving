package _rechallenge;
//구슬 탈출2 https://www.acmicpc.net/problem/13460 : 푸는데 오래 걸림
//이동 문제 => bfs or 완탐으로 모든 경로 만들기

import java.util.Scanner;

public class BJ13460 {
    static final int[] di = {0, 0, -1, 1};//왼,오,위,아래
    static final int[] dj = {-1, 1, 0, 0};
    static final int LIMIT = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] map = new String[n];
        int ri = -1, rj = -1, bi = -1, bj = -1;
        for (int i = 0; i < n; i++) {
            map[i] = sc.next();
            for (int j = 0; j < m; j++) {
                char c = map[i].charAt(j);
                if (c == 'R') {
                    ri = i;
                    rj = j;
                } else if (c == 'B') {
                    bi = i;
                    bj = j;
                }
            }
        }
        int ans = -1;
        //모든 경로 조합 만들기
        for (int set = 0; set < 1 << (LIMIT * 2); set++) { //4^10 = 2^20
            int[] dir = genDir(set);
            //타당한 경로면 탐색 시작
            if (valid(dir)) {
                char[][] arr = new char[n][m];
                for (int i = 0; i < n; i++)
                    arr[i] = map[i].toCharArray();
                int cnt = simulate(arr, dir, ri, rj, bi, bj);
                if (cnt == -1) continue;
                if (ans == -1 || ans > cnt) ans = cnt;
            }
        }
        System.out.println(ans);
    }

    private static int simulate(char[][] map, int[] dir, int ri, int rj, int bi, int bj) {
        int cnt = 0;
        for (int d : dir) {
            cnt++;
            boolean rHole = false, bHole = false;
            while (true) {
                Result r = move(map, d, ri, rj);
                Result b = move(map, d, bi, bj);
                if (!r.moved && !b.moved) break;
                if (r.hole) rHole = true;
                if (b.hole) bHole = true;
                ri = r.i;
                rj = r.j;
                bi = b.i;
                bj = b.j;
            }
            if (bHole) return -1;
            if (rHole) return cnt;
        }
        return -1;
    }

    private static Result move(char[][] map, int d, int i, int j) {
        if (i == -1) return new Result(i, j, false, false);//홀에 빠진 후
        boolean moved = false;
        while (true) {
            int ni = i + di[d];
            int nj = j + dj[d];
            char ch = map[ni][nj];
            if (ch == '.') {
                map[ni][nj] = map[i][j];
                map[i][j] = '.';
                i = ni;
                j = nj;
                moved = true;
            } else if (ch == 'O') {
                map[i][j] = '.';
                return new Result(-1, -1, true, true);
            } else // # or R or B
                return new Result(i, j, false, moved);
        }
    }

    //왼,오,위,아래
    //0, 1, 2, 3
    private static boolean valid(int[] dir) {
        for (int i = 0; i < LIMIT - 1; i++)
            if (dir[i] == 0 || dir[i] == 1) {
                if (dir[i + 1] == 0 || dir[i + 1] == 1) return false;
            } else {
                if (dir[i + 1] == 2 || dir[i + 1] == 3) return false;
            }
        return true;
    }

    private static int[] genDir(int set) {
        int[] dir = new int[LIMIT];
        for (int i = 0; i < LIMIT; i++) {
            dir[i] = set & 3;
            set = set >> 2;
        }
        return dir;
    }

    private static class Result {
        int i, j;
        boolean hole, moved;

        public Result(int i, int j, boolean hole, boolean moved) {
            this.i = i;
            this.j = j;
            this.hole = hole;
            this.moved = moved;
        }
    }
}
