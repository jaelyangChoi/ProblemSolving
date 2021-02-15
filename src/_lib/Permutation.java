package _lib;
//순열 라이브러리
//배열을 전달하면 순서를 바꿔서 반환
import java.util.Scanner;
/*다음 순열이 존재하는 가? => 지금 수보다 바로 큰 수 찾기
12345->12354->12435->12453 (규칙: 경계기준 내림차순->오름차순으로 바꿈)
첫 순열: 오름차순으로 이뤄짐, 마지막 순열: 내림차순으로 이뤄짐

배열에 1,1,2,2,2를 넣고 next_permutation을 수행하면 어떻게 될까? => 같은 것을 포함한 순열(중복 제외 10개)
등호 => 같은 수는 넘어간다. 즉, 같은 것을 포함한 순열이다.
*/
public class Permutation {
    //사전 순으로 다음에 오는 순열로 만든다. (내림차순) 1,2 -> 2,1
    public static boolean next_permutation(int[] arr) {
        //마지막 부분 순열의 경계 찾기(앞으로 이동하며 내림차순의 끝)
        int i = arr.length - 1; //경계를 가리킴 (맨 끝부터)
        while (i > 0 && arr[i - 1] >= arr[i])
            i--;
        if (i <= 0) return false;//마지막 순열까지 다옴

        //마지막 부분 순열을 첫 부분 순열로 바꾸는 과정
        // 1.경계를 다음 수로 교환한다.
        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1])//현재보다 다음으로 큰 수를 찾기 위해
            j--;

        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        // 2.첫 부분 순열 만들기(경계 이후를 오름차순으로 바꾼다)
        j = arr.length - 1;
        while (i < j) {
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            j--;
            i++;
        }
        return true;
    }

    //사전 순으로 이전에 오는 순열로 만든다.
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

    //사용법
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = i + 1; //자동으로 정렬된 상태

        //int n=5;
        //int[] arr = {1,1,2,2,2}; //같은 것을 포함한 순열로 계산해 10개 나온다.

        do { //n!번 호출된다.
            for (int i = 0; i < n; i++)
                System.out.print(arr[i] + " ");
            System.out.println();
        } while (next_permutation(arr));
    }
}
