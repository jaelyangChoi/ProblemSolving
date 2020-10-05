package BruteForce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//중간에서 만나기
//부분 수열의 합2. n<=40 이므로 2^40. 약 1조. 그냥 완전탐색 불가능.
//but 2^20은 구할 수 있으므로 2^20짜리 2개로 나누고 나중에 합친다!

//틀린 이유: ArrayList<Intger> l1,l2를 각각 get해서 비교할때 오토언박싱 안된다.. 객체이므로 equals 써야한다!!!
//틀린 이유: 큰 수(int)끼리 곱하므로 long 이어야 한다.. long으로 받아도 int*int는 먼저 int가 된다..
public class Subsequence2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        //두 부분으로 나눠 각각 부분 수열의 합을 구한다.
        int m = n / 2;//앞 놈 수
        //앞놈
        ArrayList<Integer> l1 = new ArrayList<>();
        for (int k = 0; k < (1 << m); k++) { //m개 비트마스크
            int sum = 0;
            for (int i = 0; i < m; i++)
                if ((k & (1 << i)) > 0) sum += arr[i]; //i번째 비트가 켜있으면 더함
            l1.add(sum);
        }
        //뒷놈
        ArrayList<Integer> l2 = new ArrayList<>();
        for (int k = 0; k < (1 << n - m); k++) { //n-m개 비트마스크
            int sum = 0;
            for (int i = 0; i < n - m; i++)
                if ((k & (1 << i)) > 0) sum += arr[i + m]; //i+m번째 비트가 켜있으면 더함
            l2.add(sum);
        }

        //정렬 후 앞놈은 작->큰 뒷놈은 큰->작 으로 이동하며 합해본다.
        Collections.sort(l1);
        Collections.sort(l2, Collections.reverseOrder());
        System.out.println(l1);
        System.out.println(l2);
        long cnt = 0;
        int fIdx = 0, rIdx = 0;
        while (fIdx < l1.size() && rIdx < l2.size()) {
            int sum = l1.get(fIdx) + l2.get(rIdx);
            if (sum < s)//값을 키워야 하므로 앞놈 인덱스 증가
                fIdx++;
            else if (sum > s) //값을 줄여야 하므로 뒷놈 인덱스 증가
                rIdx++;
            else {
                long same1 = 1, same2 = 1; //same끼리 곱하므로 long이어야 한다..
                //같은 값을 가진 경우 카운트
                while (++fIdx < l1.size() && l1.get(fIdx).equals(l1.get(fIdx - 1))) same1++;
                while (++rIdx < l2.size() && l2.get(rIdx).equals(l2.get(rIdx - 1))) same2++;
                cnt += same1 * same2;
            }
        }
        if (s == 0) cnt--;
        System.out.println(cnt);
    }
}
