package Heap;

import java.util.*;

//디스크 컨트롤러
//깨달음: 변수명을 좀 더 구체적으로 적으면 생각할 때 덜 헷갈려 도움이 된다!
public class Q2_3 {
    static int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int sum = 0;
        int endT = 0;//현재시간이라고 하니까 헷갈림
        int idx = 0;
        /* 작업을 기준으로
        while (idx < jobs.length) {
            while(!pq.isEmpty()&&t<jobs[idx][0]){ //이전 작업 처리
                int[] j = pq.poll();
                t += j[1];
                sum += t - j[0];
            }
            if (t < jobs[idx][0]) //시간 갱신
                t = jobs[idx][0];
            pq.offer(jobs[idx++]);
        }
        while (!pq.isEmpty()) { //남은 작업 처리
            int[] j = pq.poll();
            t += j[1];
            sum += t - j[0];
        }*/

        // 대기열을 기준으로
        while (!pq.isEmpty() || idx < jobs.length) {
            //종료시간 이전 작업 대기
            while (idx < jobs.length && endT >= jobs[idx][0])
                pq.offer(jobs[idx++]);
            //최대한 넣었으니 작업 처리
            if (pq.isEmpty()) //종료시간이 더 일찍이라 못 넣을 경우
                endT = jobs[idx][0]; //시간 갱신
            else {
                int[] job = pq.poll();
                endT += job[1];
                sum += endT - job[0];
            }
        }
        return sum / jobs.length;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{0, 3}, {2, 6}, {1, 9}}));
    }
}
