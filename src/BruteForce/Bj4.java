package BruteForce;
//N Queen 다시풀기
import java.util.Scanner;
//배운점: 속도향상-> 중복된 계산 줄이려는 노력(memoization)
//팁: 배열-> 그림 그리면 힌트가 보인다! 그림+인덱스 조작
public class Bj4 {
    //중복된 계산을 줄이기 위해 check 배열을 여러개 만듦
    static boolean[] cCheck;//불가능한 col을 true로 체크
    static boolean[] ulCheck;//왼쪽 대각선에 번호를 매기고, 불가능한 왼쪽 대각선에 true로 체크
    static boolean[] urCheck;//오른쪽 대각선에 번호를 매기고, 불가능한 오른쪽 대각선에 true로 체크
    static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        cCheck = new boolean[n];
        ulCheck = new boolean[2 * n - 1];//r-c+(n-1): 양수화
        urCheck = new boolean[2 * n - 1];//r+c
        cal(n, 0);
        System.out.println(cnt);
    }

    //가능한 row에 위치시키기
    private static void cal(int n, int r) { //r=현재 행
        if (r == n) {
            cnt++;
            return;
        }
        //라인 검사
        for (int c = 0; c < n; c++) {
            if (check(n, r, c)) {//백트래킹. 놓을 수 있는 곳
                cCheck[c] = true;
                ulCheck[r - c + n - 1] = true;
                urCheck[r + c] = true;
                cal(n, r + 1);
                cCheck[c] = false;
                ulCheck[r - c + n - 1] = false;
                urCheck[r + c] = false;
            }
        }
    }

    //위, 대각선 방향 검사
    private static boolean check(int n, int row, int col) {
        if(cCheck[col]) return false;
        if(ulCheck[row-col+n-1]) return false;
        if(urCheck[row+col]) return false;
        return true;
    }
}
