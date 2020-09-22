package Greedy;

import java.util.ArrayDeque;

//큰 수 만들기
//피드백: 이 문제에 효율적인 자료구조가 무엇인지 생각해보자
public class Q3 {
    static public String solution(String number, int k) {
        //문제를 부분 문제로 쪼개고, 각 단계에서 최선의 선택. 나보다 작은 앞놈을 다 지운다!
        char[] result = new char[number.length() - k];
        //앞놈과 비교해 큰 수를 유지 -> stack!
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < number.length(); ++i) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peekFirst() < c && k-- > 0) //나보다 작은 앞 놈 뺌. 뺄 때마다 k--;
                stack.pollFirst();
            stack.offerFirst(c);
        }

        for (int i = 0;i<result.length;++i)
            result[i] = stack.pollLast();
        return new String(result);
    }

    public static void main(String[] args) {
        System.out.println(solution("1000", 2));
    }
}
