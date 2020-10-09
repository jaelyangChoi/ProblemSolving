package Sort;

import java.util.ArrayList;
import java.util.Collections;

//틀림
//모든 조합을 만들고 정렬했더니 메모리 초과, 시간 초과
public class Q2_2 {
    static String solution(int[] numbers) {
        ArrayList<String> nums = new ArrayList<>();
        for (int e : numbers)
            nums.add(Integer.toString(e));
        Collections.sort(nums, (o1, o2) -> Integer.parseInt(o2 + o1) - Integer.parseInt(o1 + o2));
        StringBuilder sb = new StringBuilder();
        for (String s : nums)
            sb.append(s);
        return sb.toString().charAt(0) == '0' ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        //System.out.println(solution(new int[]{3, 30, 34, 5, 9}));
        //System.out.println(solution(new int[]{100,1001,1000,11,10,1,0}));
        System.out.println(solution(new int[]{0, 0, 0}));
        System.out.println(solution(new int[]{40, 404, 44}));
        System.out.println(solution(new int[]{404, 40, 4}));
    }
}
