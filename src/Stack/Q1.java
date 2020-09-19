package Stack;

//주식 가격

import java.util.ArrayDeque;

public class Q1 {
    static class Stock {
        int time;
        int price;

        public Stock(int time, int price) {
            this.time = time;
            this.price = price;
        }
    }

    static public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        ArrayDeque<Stock> st = new ArrayDeque<>();
        //스택의 꼭대기에는 가장 가격이 높은 것만 놓는다.
        for (int i = 0; i < n; i++) {
            Stock stock = new Stock(i, prices[i]);
            while (!st.isEmpty() && stock.price < st.peekFirst().price) {////가격이 떨어지면 꺼냄
                Stock s = st.pollFirst();
                answer[s.time] = i - s.time; //비 감소 구간 시간 기록
            }
            st.offerFirst(stock);
        }
        //스택 비우기
        while(!st.isEmpty()){
            Stock s = st.pollFirst();
            answer[s.time] = n-1-s.time;
        }
        return answer;
    }

    public static void main(String[] args) {
        for (int e : solution(new int[]{1, 2, 3, 2, 3}))
            System.out.print(e + " ");
    }
}

