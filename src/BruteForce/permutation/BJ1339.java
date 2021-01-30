package BruteForce.permutation;
//단어 수학
/* 교훈 */
//자료구조를 처음부터 잘 생각하면 전체 뒤엎을 일도 없다..
//한번 정한 자료구조를 이용하는게 조금 번거럽더라도 크게 시간 잡아먹지 않는다.. 갈아업지말고 밀어붙이는 연습.

import java.util.*;

/* 개선할 사항 */
//중복을 제거하는 가장 짧은 코드를 쓸 것. -> set
//hashmap도 좋지만, array를 사용해 mapping 하는 방법도 익혀두자.
public class BJ1339 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashSet<Character> set = new HashSet<>();
        ArrayList<Character>[] listArr = new ArrayList[n]; //우선 ArrayList 배열 선언
        for (int i = 0; i < n; i++) {
            listArr[i] = new ArrayList<>();
            for (char c : sc.next().toCharArray()) {
                listArr[i].add(c);
                set.add(c);
            }
        }
        //문자 중복 제거
        Character[] letters = set.toArray(new Character[set.size()]);

        //문자 - 숫자 맵핑
        int[] num = new int[letters.length];
        for (int i = 0; i < letters.length; i++)
            num[i] = 9 - i;
        Arrays.sort(num);

        int[] map = new int['Z' - 'A' + 1];
        int max = 0;

        //모든 조합 만들기
        do {
            for (int i = 0; i < letters.length; i++)
                map[letters[i] - 'A'] = num[i];
            //계산
            int temp = calc(map, listArr);
            max = Math.max(max, temp);
        } while (next_permutation(num));

        System.out.println(max);
    }

    private static int calc(int[] map, ArrayList<Character>[] listArr) {
        int sum = 0;
        for (ArrayList<Character> list : listArr) {
            int temp = 0;
            for (char c : list)
                temp = temp * 10 + map[c - 'A'];
            sum += temp;
        }
        return sum;
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
}