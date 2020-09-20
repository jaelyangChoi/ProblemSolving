package Hash;
//완주하지 못한 선수
//알게된 정보: String 클래스의 hashCode()는 문자열의 내용으로 해시코드 만듦 (다른 인스턴스여도 내용이 같으면 같은 해시코드)

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;

public class Q1 {
    static public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        //c의 이름을 배열에 해시로 맵핑
        for (String c : completion) {
            int i = 1;
            //이미 넣은 적이 있다면 인원 추가
            if (map.get(c) != null) i = map.get(c) + 1;
            map.put(c, i);
        }
        //p를 인덱스로 한 return 값이 0이면 없는 것.
        for (String p : participant) {
            if (map.get(p) == null || map.get(p) == 0) return p;
            else map.put(p, map.get(p) - 1);
        }
        return "";
    }

    public static void main(String[] args) {
        String[] p = {"mislav", "stanko", "mislav", "ana"};
        String[] c = {"stanko", "ana", "mislav"};
        System.out.println(solution(p, c));
    }
}
