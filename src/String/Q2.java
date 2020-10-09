package String;

import java.util.Arrays;

public class Q2 {
    static int solution(int[] A) {
        int n = A.length;
        boolean[] check = new boolean[n + 1]; //1~N
        check[0] = true;
        int cIdx = 1;
        int cnt = 0;
        Arrays.sort(A);
        for (int i = 0; i < n; i++) {
            if (!check[A[i]]) { //처음 등장한 수면 체크하고 넘어감
                check[A[i]] = true;
                continue;
            }
            //이미 있는 값이면
            for (int j = cIdx; j <= n; j++)
                if (!check[j]) {//빈 곳
                    check[j] = true;
                    cnt += Math.abs(j - A[i]);
                    cIdx = j + 1;//다음 시작점
                    break;
                }
        }
        return cnt > 1000000000 ? -1 : cnt;
    }

    public static void main(String[] args) {
        //int[] A = new int[]{1, 2, 1};
        //int[] A = new int[]{2,1,4,4};
        int[] A = new int[]{6,2,3,5,6,3};
        System.out.println(solution(A));
    }
}
