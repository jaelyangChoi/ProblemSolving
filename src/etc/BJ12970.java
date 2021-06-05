package etc;

import java.util.Scanner;

public class BJ12970 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        for (int b = 0; b <= n; b++) {
            int a = n - b;
            if (a * b < k) continue;

            int[] position = new int[b + 1];
            int left = k;
            for (int i = 0; i < a; i++) {//a번 선택
                int add = Math.min(left, b);
                left -= add;
                position[add] += 1;
            }
            for (int i = b; i >= 0; i--) {
                for (int j = 0; j < position[i]; j++)
                    System.out.print("A");
                if (i != 0)
                    System.out.print("B");
            }
            System.exit(0);
        }
        System.out.println(-1);
    }
}
