package Hash;

import java.util.Arrays;

//문자열 정렬은 시간이 많이 든다.
//노가다 문자열 비교 작업 -> 해시! 숫자로 비교!
//해시 방식을 통한 비교: 문자열을 하나의 해시코드 숫자로 만든 뒤 숫자를 비교
public class Q2 {
    static public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        int preHash = phone_book[0].hashCode();
        int prelen = phone_book[0].length();
        for (int i = 1; i < phone_book.length; ++i) {
            String b =phone_book[i];
            if (prelen < b.length() && b.substring(0, prelen).hashCode() == preHash)
                return false;
            prelen = b.length();
            preHash = b.hashCode();
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"119", "97674223", "1195524421"}));
    }
}
