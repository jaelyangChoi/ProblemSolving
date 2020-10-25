package Sort;
//H-Index

import java.util.Arrays;

//예외 잡아내지 못한 것: h=n-2부터 시작하고 있었다.. 역시 경계값이었다
//교훈: 예외->경계값 살피기/모든 경우에 대한 테스트 케이스 만들기(전부 기준 보다 작은값/큰값)
public class Q3 {
    static public int solution(int[] citations) {
        Arrays.sort(citations);
        int l = 0, r = citations.length - 1;
        while (l < r) {//reverse
            int temp = citations[l];
            citations[l++] = citations[r];
            citations[r--] = temp;
        }
        int h = citations.length;
        int i = 0;
        do {
            while (i < citations.length && citations[i] >= h)
                i++;
            if (i < h) //실패
                continue;
            break;
        } while (h-- > 1);
        return h;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{31, 66}));
    }
}
