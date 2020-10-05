package Greedy;

import java.util.ArrayDeque;
import java.util.Arrays;

/*
# 그리디가 어려운 이유
1. 해답일 것 같은 알고리즘 구현
2. 예외 발생, 이유를 찾는데 오래 걸림
3. 다른 알고리즘으로 다시 짜야됨
=> 검증하고 시작해야한다..
 */
// 피드백: 애초에 코드로 구현하기 쉽도록, 일관성 있도록 알고리즘을 짜자.
// 문제: 기존에 짠 코드가 오답이 나옴. 섣부른 일반화의 오류를 범했음. 테스트케이스 한개론 안된다..
// 팁: 디버깅 전에 중간 결과물을 찍어보면 문제 원인을 좁히는데 도움이 된다.
public class Q3 {
    static public int solution(String name) {
        int answer = 0;
        int n = name.length();
        char[] arr = name.toCharArray();
        Arrays.fill(arr, 'A');
        int i = 0;

        while (!name.equals(new String(arr))) {
            System.out.println(new String(arr));
            char c = name.charAt(i);
            //최소 조작
            answer += Math.min(c - arr[i], 'Z' - c + 1);
            arr[i] = c;

            //알파벳을 최소 이동 (다음 조작할 곳으로)
            int i1 = i; //i를 증가시키는 방향
            int d1 = 0;
            while (name.charAt(i1) == arr[i1] && d1 <= n) {
                ++d1;
                if ((++i1) == n) i1 = 0;
            }
            int i2 = i; //i를 감소시키는 방향
            int d2 = 0;
            while (name.charAt(i2) == arr[i2] && d2 <= n) {
                ++d2;
                if ((--i2) == -1) i2 = n - 1;
            }
            if (d1 + d2 > 2 * n) break;
            if (d1 <= d2) {
                i = i1;
                answer += d1;
            } else {
                i = i2;
                answer += d2;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("ZAZZ"));
    }
}
