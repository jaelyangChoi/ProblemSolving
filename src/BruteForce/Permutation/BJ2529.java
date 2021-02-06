package BruteForce.Permutation;
//부등호
import java.util.Scanner;

//가능한 경우만 고려하면 된다.
//사고의 틀 깨기 -> 굳이 최대, 최소를 같이 구했어야 했는가?
//비교 이슈 -> 오름차순, 내림차순으로 구하는데 굳이 또 비교했어야 하는가?
//출력 이슈 -> 굳이 숫자로 바꿔야 했는가? 배열을 출력하면 되는데?
public class BJ2529 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        char[] op = new char[k]; //equals보다 ==이 빠르므로.
        for (int i = 0; i < k; i++)
            op[i] = sc.next().charAt(0);//next()는 하나의 단어로 입력을 받음. 문자열중에 공백이나 탭등이 있으면 구분 됨.

        //최대값, 최소값은 구성 원소가 다르므로 따로 구한다.
        int[] max = new int[k + 1]; //9,8,7,,
        int[] min = new int[k + 1]; //0,1,2,,
        for (int i = 0; i < k + 1; i++) {
            min[i] = i;
            max[i] = 9 - i;
        }

        //모든 순열을 만들어보며 조건에 부합하는지 확인한다.
        do {
            if (check(max, op)) break; //가장 먼저 조건에 부합하는 것이 정답이 되도록 구성했다.
        } while (prev_permutation(max));

        do {
            if (check(min, op)) break;
        } while (next_permutation(min));

        for (int i = 0; i < k + 1; i++)
            System.out.print(max[i]);
        System.out.println();
        for (int i = 0; i < k + 1; i++)
            System.out.print(min[i]);
    }


    private static boolean check(int[] permutation, char[] op) {
        for (int i = 0; i < permutation.length - 1; i++) {
            if (op[i] == '<' && permutation[i] > permutation[i + 1]) return false;
            if (op[i] == '>' && permutation[i] < permutation[i + 1]) return false;
        }
        return true;
    }

    //사전 순으로 다음에 오는 순열로 만든다. (오름차순)
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