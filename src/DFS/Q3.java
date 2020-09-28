package DFS;

import java.util.ArrayList;
import java.util.Collections;
//여행 경로
/*배운 점
*일부 조건을 만족하는 여러 해를 구하고 마지막 조건을 만족하는 최적해를 구하는 편이 쉽다!
dfs를 완전탐색으로 여러 해를 구할 수 있다.
*vertex를 꼭 도시로 잡을 필요 없다. 체크하기 편한 걸로.
그래프 문제 꼭 인접리스트 만들 필요없다.. 무조건 만들고 보는 습관 버리자.
*dfs=>재귀=>완전탐색. 완전탐색할 때랑 똑같이 방문, 방문x로 표시해주면 된다.
dfs의 탈출조건도 재귀의 탈출 조건처럼 유연하게 설정하자.
String으로 답들을 저장하면 정렬하기에도, String[]으로 만들기에도 쉽다!
너무 복잡하게 풀고 있다면.. 잘못 설계했을 가능성이 있다. 단순하게 풀자.
*/
public class Q3 {
    static ArrayList<String> list = new ArrayList<>();
    static boolean[] check;

    static public String[] solution(String[][] tickets) {
        check = new boolean[tickets.length];//도시를 vertex로 잡으면 중복이 있어 너무 복잡, 티켓으로 잡으면 중복x 단순.
        for (int i = 0; i < tickets.length; i++) {
            String depart = tickets[i][0];
            String dest = tickets[i][1];
            if (depart.equals("ICN")) {//시작점
                check[i] = true;
                dfs(tickets, dest, 1, new StringBuilder(depart).append(","));
                check[i] = false;//다른 ICN 시작점에서 사용할 수도 있으니 티켓 재오픈
            }
        }
        Collections.sort(list);//ICN으로 시작하고 모든 경로를 순회한 여러 해 중 알파벳 순으로 앞선 것이 정답
        return list.get(0).split(",");//String으로 답들을 저장하면 정렬하기에도, String[]으로 만들기에도 쉽다!
    }

    static void dfs(String[][] tickets, String dest, int cnt, StringBuilder sb) {
        sb.append(dest).append(",");
        //정답 조건(탈출 조건)
        if (cnt == tickets.length) {
            list.add(sb.toString());
            return;
        }
        //다음 도착지 설정, 모든 경로를 순회했는지 확인하기 위해, 모든 경우 dfs하고 cnt로 확인
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(dest) && !check[i]) {
                //방문
                check[i] = true;
                dfs(tickets, tickets[i][1], cnt + 1, sb);
                //방문 무효화. 다른 경로가 방해받지 않도록. 끝에서부터 돌아오면서 쭉 해제됨.
                check[i] = false;
                sb.delete(sb.length() - 4, sb.length()); //글자수(3) + ","(1)
            }
        }
    }

    public static void main(String[] args) {
        String[][] tickets = new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        //String[][] tickets = new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        for (String s : solution(tickets))
            System.out.print(s + " ");
    }
}
