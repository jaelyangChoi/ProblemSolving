package Greedy;

import java.util.Arrays;
//구명 보트
//어려웠던 이유: 문제의 조건을 제대로 읽지 않았다.. 한 보트에 최대 2명밖에 못탄다

//앞에서 차례대로 더하며 limit와 비교하는 방식. but 틀림..
//예외: 20 20 25 30 60 65 70 (100) -> 20+20+60의 경우..
//어떻게 20 20 60을 꺼낼 수 있지? 다른 방법인가?
public class Q4 {
    static public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int cnt = 0;
        int bIdx = people.length - 1;
        for (int fIdx = 0; fIdx <= bIdx; fIdx++, cnt++)//둘이 탄다
            while (fIdx < bIdx && people[fIdx] + people[bIdx--] > limit)//bIdx는 매회 준다.
                cnt++;//무거운 놈 혼자탄다
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{20, 60, 40, 20, 50, 80}, 100));
    }

}
