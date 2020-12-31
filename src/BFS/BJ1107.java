package BFS;
//리모컨
import java.util.Scanner;
//완전 탐색의 범위가 정해져있다면 for문으로 가능
public class BJ1107 {
    //불가능하면 0 반환
    private static int possible(int ch, boolean[] broken) {
        if (ch == 0)
            return broken[0] ? 0 : 1;
        int len = 0;
        while (ch > 0) {
            if (broken[ch % 10]) return 0;
            ch /= 10;
            len++;
        }
        return len;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[] broken = new boolean[10];
        final int max = 999900;
        int goal = sc.nextInt();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++)
            broken[sc.nextInt()] = true;

        int min = Math.abs(goal - 100);
        //이동 가능한 채널 범위
        for (int ch = 0; ch < max; ch++) {
            int btn = possible(ch, broken);//이동 가능한 채널인지 확인하고 이동 횟수 반환
            if (btn == 0) continue;
            min = Math.min(min, Math.abs(goal - ch) + btn);
        }
        System.out.println(min);
    }
}
