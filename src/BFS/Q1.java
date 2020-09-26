package BFS;

import java.util.ArrayDeque;
import java.util.Queue;
//단어 변환
public class Q1 {
    static int diff(String cur, String next) {
        int diff = 0;
        for (int i = 0; i < cur.length(); i++)
            if (cur.charAt(i) != next.charAt(i)) diff++;
        return diff;
    }

    static public int solution(String begin, String target, String[] words) {
        int n = words.length;
        int targetIdx = 0;
        for (int i = 0; i < n; i++)
            if (target.equals(words[i])) {
                targetIdx = i;
                break;
            }

        int[][] d = new int[n + 1][n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        d[n][n] = 1;//-1 해줄 것
        q.offer(n);//이전 상태도 유지하고 싶다면 d[][], offer()*2, poll()*2
        q.offer(n);

        while (!q.isEmpty()) {
            int prevIdx = q.poll();
            int curIdx = q.poll();
            String s = (curIdx == n ? begin : words[curIdx]);

            for (int nextIdx = 0; nextIdx < n; nextIdx++)
                if (d[curIdx][nextIdx] == 0) //방문한적x
                    if (diff(s, words[nextIdx]) == 1) {//차이 하나
                        q.offer(curIdx);//prev
                        q.offer(nextIdx);//cur
                        d[curIdx][nextIdx] = d[prevIdx][curIdx] + 1;
                        if (nextIdx == targetIdx) return d[curIdx][nextIdx] - 1;
                    }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
    }
}
