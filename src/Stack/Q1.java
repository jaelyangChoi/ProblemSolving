package Stack;

//주식 가격

import java.util.ArrayDeque;
//피드백: 단순 저장 목적이라면 class를 만들지 않고 int[]을 스택에 넣어도 된다
public class Q1 {
    static public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        ArrayDeque<int[]> st = new ArrayDeque<>();
        //스택의 꼭대기에는 가장 가격이 높은 것만 놓는다.
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && prices[i] < st.peekFirst()[1]) {////가격이 떨어지면 꺼냄
                int[] stock = st.pollFirst();
                answer[stock[0]] = i - stock[0]; //비 감소 구간 시간 기록
            }
            st.offerFirst(new int[]{i,prices[i]});
        }
        //스택 비우기
        while(!st.isEmpty()){
            int[] stock = st.pollFirst();
            answer[stock[0]] = n-1-stock[0];
        }
        return answer;
    }

    public static void main(String[] args) {
        for (int e : solution(new int[]{1, 2, 3, 2, 3}))
            System.out.print(e + " ");
    }
}

