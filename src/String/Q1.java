package String;

public class Q1 {
    static int[] solution(String[] s) {
        int n = s.length;//문자열 개수
        int m = s[0].length();//문자열의 길이
        char[][] chars = new char[n][m];
        for (int i = 0; i < n; i++)
            chars[i] = s[i].toCharArray();

        //완전탐색
        for (int i = 0; i < m; i++) //idx
            for (int k = 0; k < n - 1; k++) //앞놈
                for (int l = k + 1; l < n; l++) //뒷놈
                    if (chars[k][i] == chars[l][i])
                        return new int[]{k, l, i};

        return new int[0];
    }

    public static void main(String[] args) {
        String[] s = new String[]{"abc", "bca", "dbe"};
        //String[] s = new String[]{"gr", "sd", "rg"};
        //String[] s = new String[]{"bdafg", "ceagi"};
        for (int e : solution(s))
            System.out.print(e + " ");
    }
}
