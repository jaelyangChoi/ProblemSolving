package String;

import java.util.regex.Pattern;

public class Regix {
    public static void main(String[] args) {
        String pattern = "^[0-9]*$";//숫자만
        String val = "123456789";
        boolean regex = Pattern.matches(pattern, val);//정규표현식과 일치하면 true
        System.out.println(regex);

        pattern = "^[가-힣]*$";//한글
        val = "최재량";
        regex = Pattern.matches(pattern, val);
        System.out.println(regex);

        pattern = "\\w+@\\w+\\.\\w+(\\.\\w+)?"; //이메일
        val = "cjl2076@naver.com";
        regex = Pattern.matches(pattern, val);
        System.out.println(regex);

        pattern = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$"; //전화번호
        val = "010-2758-8203";
        regex = Pattern.matches(pattern, val);
        System.out.println(regex);
    }
}
