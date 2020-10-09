package Heap;

import java.util.PriorityQueue;
//왜 예전에 풀었던 문젠데 못풀까?
// -> 틀에 얽매여서 사고가 갇혔다. 코딩은 정답이 하나가 아니다..
// 그때 논리적 사고가 아니라 직감으로 푼 것.

//시뮬레이션 문제가 아니라면 무조건 문제에 나온 그대로 옮기는건 아님
//생각할 것이 많다면 기준을 잡자.
public class Q2_2 {
    static public int solution(int[][] jobs) {
        int t = 0;//시각
        int sum = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); //오름차순. 같을 경우 자리가 안바뀌니 먼저 넣은 순서가 유지되겠지.
        int idx = 0;
        //while문 하나로 뽕뽑기
        while (idx < jobs.length || !pq.isEmpty()) {
            //대기에 넣는 경우, 꺼내는 경우 분리
            while (idx < jobs.length && t >= jobs[idx][0])
                pq.offer(jobs[idx++]);
            //시각 t 이후 job 요청
            if (pq.isEmpty()) //시간 갱신
                t = jobs[idx][0];
            else {//하나씩 빼보면 간단.. 당연히 while문을 써야한다고 생각함..
                int[] j = pq.poll();
                t += j[1];
                sum += t - j[0];
            }
        }
        return sum / jobs.length;
    }

    //        for (int[] job : jobs) {
//            if (pq.isEmpty() && job[0] >= t) {
//                t = job[0] + job[1];
//                sum += t - job[0];
//                continue;
//            }
//
//            if (job[0] < t) pq.offer(job);
//            if (job[0] >= t) {
//                while (!pq.isEmpty() && t < job[0]) {
//                    int[] j = pq.peek();
//                    t += j[1];
//                    sum += t - j[0];
//                }
//                pq.offer(job);
//            }
//        }
//        while(!pq.isEmpty()){
//            int[] j = pq.peek();
//            if(j[0]<t)
//        }
//
//        return sum / jobs.length;
//    }
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{0, 3}, {1, 9}, {2, 6}}));
    }
}
