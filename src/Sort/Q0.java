package Sort;

import java.util.ArrayList;
import java.util.Arrays;


public class Q0 {
    static public int solution(int[] A) {
        //정렬
        Arrays.sort(A);
        ArrayList<Integer> list = new ArrayList<>();
        //중복, 음수 제거
        for (int i = 0; i < A.length; i++) {
            if (i == 0 || A[i] != A[i - 1])
                if (A[i] > 0)
                    list.add(A[i]);
        }

        int ans = 1;
        for (int e : list) {
            if (e != ans) return ans;
            ans++;
        }

        return ans == 1 ? 1 : ans;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{-1,1, 3, 6, 4, 1, 2}));
        //System.out.println(solution(new int[]{1, 2, 3}));
        //System.out.println(solution(new int[]{-1,-2,0}));
    }
}
