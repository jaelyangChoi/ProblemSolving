package BFS;
//리모컨
import java.util.Scanner;
//어디가 틀렸을까?
public class BJ1107 {
    static boolean[] malFunc = new boolean[10];
    static int min;
    static int goal;
    static final int limit =6;

    //모든 조합을 만들어 내 비용 구한다.
    static void go(int num, int cnt, int chosen) {
        if (chosen > limit) return; //다 골랐다
        min = Math.min(min, Math.abs(goal - num) + cnt);//num으로 min 갱신
        for (int i = 0; i < 10; i++) {
            if (malFunc[i]) continue;//고장난 버튼
            go(num*10+i, cnt+1,chosen+1);//선택
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        goal = sc.nextInt();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++)
            malFunc[sc.nextInt()] = true;
        min = Math.abs(goal - 100);

        go(0, 0, 0);
        System.out.println(min);
    }
}
