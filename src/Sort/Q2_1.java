package Sort;

import java.util.Arrays;

/*마지막 예외 생각 못함.. 경계값을 보긴 봤으나 다른 경계만 봤다..
하나를 파기 전에 여러가지 추정 요인을 모두 추려놓자.*/
public class Q2_1 {
    static public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        for(int i=0;i<numbers.length;i++)
            nums[i]=Integer.toString(numbers[i]);
        Arrays.sort(nums, (o1, o2) -> {
            int n1=Integer.parseInt(o1+o2);
            int n2=Integer.parseInt(o2+o1);
            return n2-n1;//내림차순. 붙여서 큰 수가 앞에.
        });
        StringBuilder answer = new StringBuilder(500);
        for(String s: nums)
            answer.append(s);

        return answer.charAt(0)=='0'?"0":answer.toString();
    }
    public static void main(String[] args) {
        //int[] numbers = {3,30,34,5,9};
        int[] numbers = {44,4,40,404,4,14,15};
        System.out.println(solution(numbers));
    }
}
