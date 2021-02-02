package BruteForce.BackTracking;
//알파벳
//백트래킹: check[]가 관건

import java.util.Scanner;

public class BJ1987 {
    static boolean[] check = new boolean['Z' - 'A' + 1];
    static String[] arr;//String을 충분히 배열처럼 쓸 수 있다. <- charAt()
    static final int[] di = {0, 0, -1, 1};
    static final int[] dj = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        arr = new String[r];
        for (int i = 0; i < r; i++)
            arr[i] = sc.next();
        check[arr[0].charAt(0) - 'A'] = true;
        System.out.println(go(0, 0));
    }

    //DFS. 갈 수 있는 데까지 가보기. 끝에서부터 계산해서 내려옴
    private static int go(int i, int j) {
        int ans = 0;//맨끝에서 하나씩 더해져 내려옴

        for (int k = 0; k < 4; k++) {
            int ni = i + di[k];
            int nj = j + dj[k];
            if (ni < 0 || ni >= arr.length || nj < 0 || nj >= arr[0].length()) continue;

            if (check[arr[ni].charAt(nj) - 'A']) continue;//백트래킹
            check[arr[ni].charAt(nj) - 'A'] = true;
            int next = go(ni, nj);
            ans = Math.max(next, ans); //4 방향 중 최대값 유지
            check[arr[ni].charAt(nj) - 'A'] = false;
        }
        return ans + 1;//현재 위치 더해줌
    }
}