package DFS;
//여행 경로

import java.util.ArrayList;
import java.util.Collections;

public class Q3_2 {
    static ArrayList<String> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder("ICN,");
    static boolean[] check;

    static void dfs(String[][] tickets, String depart, int cnt) {
        if(cnt==tickets.length){
            list.add(sb.toString());
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!check[i] && tickets[i][0].equals(depart)) {
                check[i] = true;
                sb.append(tickets[i][1]).append(",");
                dfs(tickets, tickets[i][1], cnt+1);
                sb.delete(sb.length() - 4, sb.length());
                check[i]=false;
            }
        }
    }

    static String[] solution(String[][] tickets) {
        check = new boolean[tickets.length];
        dfs(tickets, "ICN",0);
        Collections.sort(list);
        return list.get(0).split(",");
    }

    public static void main(String[] args) {
        //String[][] tickets = new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets = new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        for (String s : solution(tickets))
            System.out.print(s + " ");
    }
}
