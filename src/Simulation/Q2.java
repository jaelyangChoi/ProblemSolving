package Simulation;

import java.util.Scanner;

//시행착오: 여러 테스트케이스 고려하지 않고 바로 달려들었다.. 특정 테스트케이스에 적합하지 않은 설계
//깨달음: 복잡할수록 함수화하면 생각이 정돈되고 오류를 찾기 쉽고 중복이 줄어든다
public class Q2 {
    static boolean checkLine(int[] arr, int l) {
        int n = arr.length;
        int[] c = new int[n];
        for (int i = 1; i < n; ++i) {
            int diff = arr[i - 1] - arr[i];
            //높이가 같은 경우
            if (diff == 0) continue;
            //높이가 다른 경우
            //경사로를 놓을 수 없는 경우
            if (Math.abs(diff) != 1) return false;
            //경사로 - 내리막
            if (diff == 1) {
                //i~i+l-1까지 l개의 칸의 높이가 같아야 함
                int h = arr[i];
                c[i] = 1;
                for (int k = 1; k < l; k++) {//k-1개 검사
                    if (++i >= n) return false;
                    if (arr[i] != h) return false;
                    if (c[i] == 1) return false;
                    c[i] = 1;
                }
            }
            //경사로 - 오르막
            else if (diff == -1) {
                //i-1~i-l까지 l개의 칸의 높이가 같아야 함
                int h = arr[i - 1];
                for (int k = 1; k <= l; k++) {//k개
                    if (i - k < 0) return false;
                    if (arr[i - k] != h) return false;
                    if (c[i - k] == 1) return false;
                    c[i - k] = 1;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int[][] map = new int[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                map[i][j] = sc.nextInt();

        int cnt = 0;

        int[] arr = new int[n];
        //행 검사
        for (int i = 0; i < n; ++i) {
            for (int k = 0; k < n; ++k)//복사
                arr[k] = map[i][k];
            if (checkLine(arr, l)) ++cnt;
        }
        //열 검사
        for (int j = 0; j < n; ++j) {
            for (int k = 0; k < n; ++k)//복사
                arr[k] = map[k][j];
            if (checkLine(arr, l)) ++cnt;
        }
        System.out.println(cnt);
    }
}
