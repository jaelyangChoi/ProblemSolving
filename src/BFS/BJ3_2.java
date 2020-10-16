package BFS;
//퍼즐 2차 풀이
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;
//문자열로 풀었으나, 속도상으로나 메모리 상으로나 String 보다는 Integer를 저장하는게 유리하다.
public class BJ3_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++)
            sb.append(sc.nextInt());
        final int[] di = {0, 0, 1, -1};//왼,오,아래,위
        final int[] dj = {-1, 1, 0, 0};
        Queue<String> q = new ArrayDeque<>();
        HashMap<String, Integer> d = new HashMap<>();
        q.offer(sb.toString());
        d.put(sb.toString(), 0);
        while (!q.isEmpty()) {
            String curV = q.poll();
            if (curV.equals("123456780")) break;
            //빈공간(0)의 위치 찾기
            int idx = curV.indexOf("0");
            //배열 상의 위치로 전환
            int i = idx / 3;
            int j = idx % 3;
            //이동
            for (int k = 0; k < 4; k++) {
                int ni = i + di[k];
                int nj = j + dj[k];
                if (ni < 0 || nj < 0 || ni >= 3 || nj >= 3) continue;
                sb = new StringBuilder(curV);
                sb.setCharAt(idx, sb.charAt(ni * 3 + nj));//replace말고 setCharAt도 있다. 대체한다.
                sb.setCharAt(ni * 3 + nj, '0');
                if (d.containsKey(sb.toString())) continue;//중복 체크
                d.put(sb.toString(), d.get(curV) + 1); //거리 계산
                q.offer(sb.toString());
            }
        }
        System.out.println(d.getOrDefault("123456780", -1));
    }
}
