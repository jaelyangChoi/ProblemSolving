package BruteForce;
//합이 0인 네 정수
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//팁: n^4는 너무 커.. 반으로 나누기!(n^2은 할만하다)
//깨달음: 이진 탐색, 정렬 등을 배우면서 습득한 원리, 아이디어를 코테에 활용하는 거구나..
//ArrayList로 풀었는데 시간초과 나왔다. 이유: add시 allocation을 하기 때문. new ArrayList<>(n*n)을 하면 빨라짐.
public class BJ2 {
    /*여러가지 구현 방식이 있지만 [left,right) 방식은 예외처리가 더 적어서 이렇게 구현함*/
    //val보다 큰 것 중 제일 작은 것
    static int upperBound(int[] arr, int val) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= val) //그럼 val는 mid보다 오른쪽에 있다.
                left = mid + 1;
            else //e는 mid보다 왼쪽에 있다.
                right = mid; //mid-1로 하니까 헷갈렸는데 명쾌하네.. 정확하게 val을 찾는게 아니라 mid-1로 제할 필요x
        }
        return left;
    }

    //val보다 크거나 같은 것 중 제일 작은 것
    static int lowerBound(int[] arr, int val) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < val) //그럼 val는 mid보다 오른쪽에 있다.
                left = mid + 1;
            else //e는 mid보다 왼쪽에 있다.
                right = mid;
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[4][n];
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < 4; j++)
                arr[j][i] = Integer.parseInt(s[j]);
        }
        //arr[0][a]+arr[1][b] = -(arr[2][c]+arr[3][d])
        int[] ab = new int[n * n];
        int[] cd = new int[n * n];
        int idx = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                ab[idx] = arr[0][i] + arr[1][j];
                cd[idx++] = arr[2][i] + arr[3][j];
            }

        //-ab=cd 찾기. 중복 가능성있으므로, 몇개 있는지 찾아야 함. upperbound-lowerbound
        //Collections.sort(ab); ab는 정렬할 필요x.. 이진 탐색할 것 아니니까
        Arrays.sort(cd);
        long cnt = 0; //int로 하면 데이터 손실...!
        for (int e : ab)
            cnt += upperBound(cd, -e) - lowerBound(cd, -e);
        System.out.println(cnt);
    }
}
