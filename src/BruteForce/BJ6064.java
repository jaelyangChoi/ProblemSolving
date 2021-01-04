package BruteForce;
//카잉달력

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//특정 수를 넘어가면 1 -> %의 아이디어!
public class BJ6064 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] s = br.readLine().split(" ");
            int m = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);
            int x = Integer.parseInt(s[2]);
            int y = Integer.parseInt(s[3]);

            int b = x % n == 0 ? n : x % n;
            int ans = -1;
            for (int i = x; i <= m * n; i += m) {//m*n으로 대충..
                if (b == y) {
                    ans = i;
                    break;
                }
                b = (b + m) % n == 0 ? n : (b + m) % n; //나머지로 표현하면 문제 발생x
                //b = b+m <= n? b+m : b+m-n; 으로 할 경우 m>n일때 문제 발생(한바퀴 이상 돌 수 있다)
            }
            System.out.println(ans);
        }
    }
}
