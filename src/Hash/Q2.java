package Hash;

import java.util.Arrays;
//startWith이라는 메소드가 있구나!
public class Q2 {
    static public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        String pre = "a";
        for (String s : phone_book) {
            if (s.startsWith(pre)) return false;
            pre = s;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"119", "97674223", "1195524421"}));
    }
}
