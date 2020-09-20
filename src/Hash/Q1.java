package Hash;
//완주하지 못한 선수
//알게된 정보: String 클래스의 hashCode()는 문자열의 내용으로 해시코드 만듦 (다른 인스턴스여도 내용이 같으면 같은 해시코드)
import java.util.HashMap;

public class Q1 {
    static public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        //c의 이름을 배열에 해시로 맵핑
        for (String c : completion)
            map.put(c, map.getOrDefault(c,0)+1); //key가 없으면 기본 값, 있으면 value 반환
        //p를 인덱스로 한 return 값이 0이면 없는 것.
        for (String p : participant) {
            if (map.get(p) == null || map.get(p) == 0) return p;
            else map.computeIfPresent(p,(String key, Integer value)->--value);//key가 존재하면 value 계산
        }
        return "";
    }

    public static void main(String[] args) {
        String[] p = {"mislav", "stanko", "mislav", "ana"};
        String[] c = {"stanko", "ana", "mislav"};
        System.out.println(solution(p, c));
    }
}
