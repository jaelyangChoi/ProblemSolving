package BruteForce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        //합이 s가 되는 부분 수열. n<=40 -> 2^40(1조).. 반으로 나눠 2^20씩 연산하고 확인
        System.out.println(go(arr, s));

    }

    private static long go(int[] arr, int s) {
        //수열을 반으로 나누고 부분 수열을 만든다.
        int n = arr.length;
        int fn = n / 2; //front n
        int rn = n - fn;//rear n
        ArrayList<Integer> fs = new ArrayList<>(); //front 부분 수열의 sum
        ArrayList<Integer> rs = new ArrayList<>(); //rear 부분 수열의 sum
        //앞 수열의 부분 수열. 비트 마스크로 표현
        for (int k = 0; k < 1 << fn; k++) {
            int sum = 0;
            for (int i = 0; i < fn; i++)
                if ((k & (1 << i)) > 0) sum += arr[i];

            fs.add(sum);
        }
        //뒷 수열의 부분 수열. 비트 마스크로 표현
        for (int k = 0; k < 1 << rn; k++) {
            int sum = 0;
            for (int i = 0; i < rn; i++)
                if ((k & (1 << i)) > 0) sum += arr[fn + i];

            rs.add(sum);
        }

        //두 수열의 합을 더해 s가 되는지 확인
        //불필요한 경우 건너 뛰기 위해 정렬
        Collections.sort(fs);
        Collections.sort(rs);
        long cnt = 0;
        //건너 뛰려면 인덱스의 움직임이 달라야 함. 같은 방향으로 움직이면 구분 불가
        int fi = 0, ri = rs.size() - 1; //fi 증가, ri 감소
        while (fi < fs.size() && ri >= 0) {
            int sum = fs.get(fi) + rs.get(ri);
            if (sum > s) {//수를 줄여야함.
                --ri;
            } else if (sum < s) { //수를 늘려야 함. i++
                ++fi;
            } else {
                int fi2 = fi + 1, ri2 = ri - 1;
                long fsame = 1, rsame = 1;
                while (fi2 < fs.size() && fs.get(fi).equals(fs.get(fi2))) {
                    ++fsame;
                    ++fi2;
                }//아래 코드보다 간결해서 보기 좋다
                while (ri2 >= 0) {
                    if (rs.get(ri).equals(rs.get(ri2))) {//==로 하니 틀림..
                        ++rsame;
                        --ri2;
                    } else break;
                }
                cnt += fsame * rsame;
                fi = fi2;
                ri = ri2;
            }
        }

        //s가 0인 경우 하나 뺌
        if (s == 0) --cnt;
        return cnt;
    }
}
