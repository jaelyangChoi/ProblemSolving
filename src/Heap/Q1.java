package Heap;

import java.util.Iterator;
import java.util.PriorityQueue;

//더맵게
//피드백: 처음에 return cnt>0?cnt:-1 이라고 했다. cnt==0인 경우도 답이 될 수 있는데 고려하지 않은 것.
//교훈: 예시로 경계값을 생각해본뒤 코딩하자!
public class Q1 {
    public static int solution(int[] scoville, int K) { //연산은 long으로
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int e : scoville)
            pq.offer(e);

        int cnt = 0;
        //scoville의 원소 중 k 이하인 최소, 두번째 최소 꺼내 연산
        while (pq.size() > 1 && pq.peek() < K) {//반복 조건도 신중히 생각.. size()>1 &&pq.peek()<k 인 동안이잖아
            pq.offer(pq.poll() + 2 * pq.poll());
            cnt++;
        }

        return pq.peek() >= K ? cnt : -1;
    }

    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int k = 7;
        int ret = solution(scoville, k);
        System.out.println(ret);
    }
}
