import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test {
    static final int mod = (int) (Math.pow(10, 9) + 7);
    static final char[] vowel = {'a', 'e', 'i', 'o', 'u'};
    static StringBuilder sb = new StringBuilder();

    public static int countPerms(int n) {
        return perm(n, 0);//모든 조합 만들고 체크
    }

    private static int perm(int n, int len) {
        if (len == n) return check() == true ? 1 : 0;
        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            sb.append(vowel[i]);
            cnt += perm(n, len + 1);
            sb.deleteCharAt(len);
        }
        return cnt % mod;
    }

    private static boolean check() {
        for (int i = 0; i < sb.length() - 1; i++)
            switch (sb.charAt(i)) {
                case 'a':
                    if (sb.charAt(i + 1) != 'e') return false;
                    break;
                case 'e':
                    if (sb.charAt(i + 1) != 'a' && sb.charAt(i + 1) != 'i') return false;
                    break;
                case 'i':
                    if (sb.charAt(i + 1) == 'i') return false;
                    break;
                case 'o':
                    if (sb.charAt(i + 1) != 'i' && sb.charAt(i + 1) != 'u') return false;
                    break;
                case 'u':
                    if (sb.charAt(i + 1) != 'a') return false;
                    break;
            }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(countPerms(3));
    }
}
