package BruteForce;

import java.util.ArrayList;
import java.util.List;

public class Q1 {
    static public int[] solution(int[] answers) {
        final int[][] type = new int[][]{{1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        final int[] len = new int[]{5, 8, 10};
        int[] cnt = new int[3];
        int max = 0;

        for (int k = 0; k < 3; ++k) {
            for (int i = 0; i < answers.length; ++i)
                if (answers[i] == type[k][i%len[k]]) ++cnt[k];
            max = Math.max(max, cnt[k]);
        }

        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 3; ++i)
            if (max == cnt[i]) l.add(i + 1);

        return l.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        for (int i : solution(new int[]{1,3,2,4,2}))
            System.out.print(i + " ");
    }

}
