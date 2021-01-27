package BruteForce.permutation;
//로또
//다시 풀기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//k개 중 6개 선택. 순열이 아니라 조합 문제?
//k개 중 6개 선택, k-6개 선택x -> 0,1로 모델링 -> 같은 것을 포함한 순열!
//배열에 1,1,2,2,2를 넣고 next_permutation을 수행하면 어떻게 될까? => 같은 것을 포함한 순열(중복 제외 10개)
public class BJ6603 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String str[] = br.readLine().split(" ");
            if (str[0].equals("0")) break;
            int k = Integer.parseInt(str[0]);
            String[] s = new String[k];
            for (int i = 0; i < k; i++)
                s[i] = str[i + 1];
            //k개 중 6개 선택(1), k-6개 선택x(0)
            int[] idxes = new int[k];
            for (int i = 0; i < 6; i++)
                idxes[i] = 1;
            StringBuffer ans = new StringBuffer();
            do {
                for (int i = 0; i < k; i++)
                    if (idxes[i] == 1) ans.append(s[i]).append(" ");
                System.out.println(ans);
                ans.delete(0,ans.length());
            } while (prev_permutation(idxes));
            System.out.println();
        }
    }

    //사전 순으로 이전에 오는 순열로 만든다. (내림차순)
    public static boolean prev_permutation(int[] a) {
        //오름차순의 경계찾기
        int i = a.length - 1;
        while (i > 0 && a[i - 1] <= a[i])
            i -= 1;
        if (i <= 0) return false;

        //경계 swap
        int j = a.length - 1;
        while (a[j] >= a[i - 1])
            j -= 1;
        int temp = a[i - 1];
        a[i - 1] = a[j];
        a[j] = temp;

        //경계 이후 내림차순으로 만들기
        j = a.length - 1;
        while (i < j) {
            temp = a[j];
            a[j] = a[i];
            a[i] = temp;
            j--;
            i++;
        }
        return true;
    }
}
