package Greedy;

public class Q3 {
    static public String solution(String number, int k) {
        //문제를 부분 문제로 쪼개고, 각 단계에서 최선의 선택. 나보다 작은 앞놈을 다 지운다!
        char[] numbers = number.toCharArray();
        //k개를 지울 때 까지
        int delete = 0;
        int idx = 1;
        while (delete < k && idx < number.length()) {
            int cur = numbers[idx];
            for (int i = idx - 1; i >= 0; --i) {
                if (numbers[i] == '#') continue;
                if (numbers[i] < cur) {
                    numbers[i] = '#';
                    ++delete;
                } else break;
                if (delete == k) break;
            }
            ++idx;
        }
        int cnt=0;
        StringBuilder sb = new StringBuilder();
        for (char c : numbers) {
            if (c == '#') continue;
            sb.append(c);
            ++cnt;
            if(cnt==number.length()-k) break;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution("7652321", 1));
    }
}
