package Sort;

import java.util.ArrayList;
import java.util.Collections;

//가장큰수
//시행착오: 숫자가 너무 크면 parseInt할 수 없다. int 범위 넘어감..parseLong도 에러.
//시행착오: 기껏 완전탐색으로 풀었는데 시간 초과.. 완탐의 시간복잡도부터 고려해보자. 무작정 비교하지 말고 효과적인 방법을 생각해보자.. 고민조차 안하고 덤벼드네.
//시행착오: 케이스 생각하는게 일.. 경계값해보라고.. 0000일때 ->0
public class Q2 {
    static public String solution(int[] numbers) {
        ArrayList<String> list = new ArrayList<>();
        for (int e : numbers)
            list.add(Integer.toString(e));
        System.out.println(list);
        Collections.sort(list, (o1, o2) -> {
            int i1 = Integer.parseInt(o1 + o2);
            int i2 = Integer.parseInt(o2 + o1);
            return i2 - i1; //내림차순
        });
        System.out.println(list);
        StringBuilder sb = new StringBuilder();
        for (String s : list)
            sb.append(s);

        return sb.toString().charAt(0) == '0' ? "0" : sb.toString();
    }


    public static void main(String[] args) {
        //System.out.println(solution(new int[]{3, 30, 303,31,34}));
        //System.out.println(solution(new int[]{0,10,11,111,100,1000}));
        //System.out.println(solution(new int[]{1,10001,10002, 1003}));
        System.out.println(solution(new int[]{40, 404, 44}));
        System.out.println(solution(new int[]{404, 40, 4}));
    }
}
