package BruteForce.BackTracking;
//스타트와 링크
//리턴값이 있는 재귀 함수 처리 방법
import java.util.ArrayList;
import java.util.Scanner;

//백트래킹: 재귀함수를 이용해 bf 할때, 더 이상 함수 호출이 의미없는 경우는 제외하고 진행
//더 들어가봐야 답 안나오니 뒤로 백한다.
public class BJ14889 {
    static int n;
    static int[][] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = sc.nextInt();


        System.out.println(go(0, new ArrayList<Integer>(), new ArrayList<Integer>()));
    }

    static private int go(int idx, ArrayList<Integer> team1, ArrayList<Integer> team2) {
        if (idx == n) {
            if (team1.size() == n / 2)
                return calc(team1, team2);
            return -1;
        }
        //백트래킹
        if(team1.size()>n/2) return -1;
        if(team2.size()>n/2) return -1;

        int ans = -1;
        //team1에 배정
        team1.add(idx);
        int t1 = go(idx + 1, team1, team2);
        if (ans == -1 || (t1 != -1 && ans > t1)) ans = t1;
        team1.remove(team1.size() - 1);

        //team2에 배정
        team2.add(idx);
        int t2 = go(idx + 1, team1, team2);
        if (ans == -1 || (t2 != -1 && ans > t2)) ans = t2; //t1이 -1이었을 경우 ans=-1
        team2.remove(team2.size() - 1); //이거 빼먹으면 안된다.

        return ans;
    }

    private static int calc(ArrayList<Integer> team1, ArrayList<Integer> team2) {
        int t1 = 0;
        int t2 = 0;
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n/2; j++) {
                if (i == j) continue;
                t1 += arr[team1.get(i)][team1.get(j)];
                t2 += arr[team2.get(i)][team2.get(j)];
            }
        }
        return Math.abs(t1 - t2);
    }
}
