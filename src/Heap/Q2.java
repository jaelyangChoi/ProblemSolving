package Heap;

import java.util.*;

//디스크 컨트롤러
//시행착오: 우선순위 큐의 비교 기준에 외부 요인을 넣으면 안됨. 외부 요인이 변해도 갱신x
//시행착오: int는 안되도 int[]도 큐에 들어가는 구나.. 자바에서 배열은 객체니까..!!
//시행착오: 2가지 기준으로 우선순위를 매긴다면 우선순위큐 하나만 쓸 필요는 없구나.. list도 같이 써도 되는구나..

//깨달음: pq에 한꺼번에 넣지 않고 일정 조건을 만족할 때마다 넣어도 되겠구나..
//깨달음: 코드가 짧아야 오류찾기가 쉽구나..
public class Q2 {
    static public int solution(int[][] jobs) {
        int idx = 0;
        int len = jobs.length;
        int time = 0;
        int sum = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        Arrays.sort(jobs, ((o1, o2) -> o1[0] - o2[0])); //시간 순 정렬

        while (idx < len || !pq.isEmpty()) {
            //작업할 수 있는 것이 있다면 소요 시간 순
            while (idx < len && jobs[idx][0] <= time)  //리스트를 한번만 훑는 전략은 위대하다
                pq.offer(jobs[idx++]);

            if (pq.isEmpty()) //작업할 수 있는 것이 없다면
                time = jobs[idx][0];//첫 요청까지 기다림
            else {
                int[] job = pq.poll();
                time += job[1];
                sum += time - job[0];
            }
        }

        return sum / len;
    }

    public static void main(String[] args) {
        //int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        //int[][] jobs = {{1, 4}, {6, 4}, {9, 6}, {9, 5}};
        //int[][] jobs = {{0, 4}, {1, 5}, {2, 4}, {2, 3}, {3, 1}};
        System.out.println(solution(new int[][]{{0, 4}, {1, 5}, {2, 4}, {2, 3}, {3, 1}}));
    }
}

/*class Solution {
    class Job implements Comparable<Job> {
        int request;
        int take;

        public Job(int request, int take) {
            this.request = request;
            this.take = take;
        }

        //요청 시간 순으로 넣되, 종료 시간> 제일 빠른 요청 시간인 경우 여러 선택지 고려
        @Override
        public int compareTo(Job o) { //pq의 우선순위 기준
            return this.request - o.request; //요청 시간 오름차순
        }
    }
    public int solution(int[][] jobs) {
       PriorityQueue<Job> pq = new PriorityQueue<>();
        for (int i = 0; i < jobs.length; ++i)
            pq.offer(new Job(jobs[i][0], jobs[i][1]));
        int endTime = 0;
        int sum = 0;

        ArrayList<Job> l = new ArrayList<>();
        while (!pq.isEmpty()) {
            Job job = pq.poll();//요청이 가장 빠른 작업
            if (job.request > endTime) {//바로 할 작업x 작업 들어오길 기다렸다 시작
                sum += job.take;
                endTime += (job.request - endTime) + job.take;
            } else {//시작할 수 있는 작업 여러개. 다 꺼내서 여러 선택지 고름
                Job min = job;
                l.add(job);
                Iterator<Job> iter = pq.iterator();
                while(iter.hasNext()){
                    Job j = pq.poll();
                    l.add(j);
                    if (j.request > endTime) continue;
                    if (min.take > j.take) min = j;//소요시간 가장 짧은 작업
                }
                endTime += min.take;//바로 시작
                sum += endTime - min.request;//대기 시간 추가

                for (Job j : l) {
                    if (j.equals(min)) continue;
                    pq.offer(j);
                }
                l.clear();
            }
        }
        return sum / jobs.length;
    }
}*/