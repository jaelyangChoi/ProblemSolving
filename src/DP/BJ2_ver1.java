package DP;
//점프 O(N^3) 풀이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 못 푼 이유
for문 돌리려다가 너무 비효율적인 것 같아서 안했는데,, 그게 맞았다. O(N^3)으로 풀 수 있는 문제.
너무 유형에 맞추려다보니 생각이 틀에 갇혔다.
*/
//배운 점: 손으로 풀어서 규칙 발견하기..간과하지말자!
public class BJ2_ver1 {
    static long[][] d;//경로가 너무 클 수 있으므로 long..
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++)
                board[i][j] = Integer.parseInt(temp[j]);
        }
        //d[i][j]: (0,0)->(i,j)로 가는 경로 갯수
        d = new long[n][n];
        //오른쪽에서 올 때: d[i][j]+=d[k][j]. 단, k+board[k][j] == i
        //왼쪽에서 올 때: d[i][j]+=d[i][k]. 단, board[i][k]=j-k.
        d[0][0] = 1;//뿌리!
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                for (int k = 0; k < i; k++) //아래쪽에서 온 경우
                    if (k + board[k][j] == i)//이 조건이 맞아야 된다.
                        d[i][j] += d[k][j];//0일 수 있다.
                for (int k = 0; k < j; k++) //오른쪽에서 온 경우
                    if (k + board[i][k] == j)//k + j-k -> j
                        d[i][j] += d[i][k];
            }
        System.out.println(d[n - 1][n - 1]);
    }
}
