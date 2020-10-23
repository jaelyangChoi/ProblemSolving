package Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

//틀린이유: if문이 복잡하면 새어나가는 걸 파악하기 힘들다..
//복잡할 땐 기준!(넣을 때, 뺄 때)
public class Q2_4 {
    static public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);//요청 시간 오름차순
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);//소요시간 오름차순
        final int len = jobs.length;
        int t = 0;
        int sum = 0;
        int idx = 0;

        while (idx < len || !pq.isEmpty()) {
            //넣는다
            while (idx < len && t >= jobs[idx][0])
                pq.offer(jobs[idx++]);
            //빼거나 시간 조정
            if (pq.isEmpty())
                t = jobs[idx][0];
            else {
                int[] job = pq.poll();
                t += job[1];
                sum += t - job[0];
            }
        }
        return sum / len;
    }

    public static void main(String[] args) {
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        //int[][] jobs = {{1, 4}, {6, 4}, {9, 6}, {9, 5}};
        //int[][] jobs = {{0, 4}, {1, 5}, {2, 4}, {2, 3}, {3, 1}};
        //int[][] jobs = {{0, 4}, {1, 5}, {2, 4}, {2, 3}, {3, 1}};
        System.out.println(solution(jobs));
    }
}
