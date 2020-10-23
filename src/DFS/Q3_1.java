package DFS;

import java.util.ArrayList;
import java.util.Collections;

//시행착오: sb를 매번 새로 생성 -> dfs는 함수가 끝나도 재귀에서 돌아갈 때 sb가 그대로 남아있어 메모리 초과!
public class Q3_1 {
    static ArrayList<String> answer = new ArrayList<>();
    static boolean[] check;
    static StringBuilder sb = new StringBuilder("ICN");

    static public String[] solution(String[][] tickets) {
        check = new boolean[tickets.length];
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals("ICN")) {
                check[i] = true;
                dfs(tickets, tickets[i][1], 1);
                sb.delete(sb.length() - 4, sb.length());
                check[i] = false;
            }
        }
        System.out.println(answer);
        Collections.sort(answer);
        return answer.get(0).split(",");
    }

    private static void dfs(String[][] tickets, String dest, int n) {
        sb.append(",").append(dest);
        if (n == tickets.length) {
            answer.add(sb.toString());
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (check[i]) continue;
            if (tickets[i][0].equals(dest)) {
                check[i] = true;
                dfs(tickets, tickets[i][1], n + 1);
                sb.delete(sb.length() - 4, sb.length());
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        //String[][] tickets = new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets = new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        for (String s : solution(tickets))
            System.out.print(s + " ");
    }
}
