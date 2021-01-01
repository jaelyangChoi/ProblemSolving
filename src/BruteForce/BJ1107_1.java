package BruteForce;
//리모컨 풀이1

import java.util.Scanner;

//어디가 틀렸을까? 예외가 있다는 것.
public class BJ1107_1 {
    static boolean[] malFunc = new boolean[10];
    static int min;
    static int goal;
    static final int limit = 999900;

    //모든 조합을 만들어 내 비용 구한다.
    static void go(int num, int cnt) {
        if (num > limit) return; //num의 상한
        if (cnt != 0)
            min = Math.min(min, Math.abs(goal - num) + cnt);//num으로 min 갱신
        for (int i = 0; i < 10; i++) {
            if (malFunc[i]) continue;//고장난 버튼
            if (num == 0 && cnt > 0) continue;//0 연속 선택 불가
            go(num * 10 + i, cnt + 1);//선택
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        goal = sc.nextInt();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++)
            malFunc[sc.nextInt()] = true;
        min = Math.abs(goal - 100);
        if (!malFunc[0] && goal == 0) min = 1;
        go(0, 0);
        System.out.println(min);
    }
}
