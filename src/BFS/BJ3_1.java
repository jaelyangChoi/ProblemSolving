package BFS;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;

public class BJ3_1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int[] di = {0, 0, 1, -1};//오,웬,아,위
        final int[] dj = {1, -1, 0, 0};
        int initState = 0;
        for (int i = 0; i < 9; i++) {
            int x = sc.nextInt();
            initState = initState * 10 + (x == 0 ? 9 : x);//x가 0이면 012345678은 12345678이 됨
        }

        HashMap<Integer, Integer> dist = new HashMap<>();//String보다는 Integer 넣는게 메모리, 속도 상 유리
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(initState);
        dist.put(initState, 0);

        while (!q.isEmpty()) {
            String vertex = q.poll().toString();//Integer 반환하므로
            if (vertex.equals("123456789")) break;
            int blankIdx = vertex.indexOf("9");
            int row = blankIdx / 3;
            int col = blankIdx % 3;
            for (int k = 0; k < 4; k++) {
                int nextR = row + di[k];
                int nextC = col + dj[k];
                if (nextR < 0 || nextR >= 3 || nextC < 0 || nextC >= 3) continue;
                StringBuilder next = new StringBuilder(vertex);
                swap(next, blankIdx, 3 * nextR + nextC);
                int nextNum = Integer.parseInt(next.toString());
                if (dist.containsKey(nextNum)) continue;//방문한 적 있는 경우
                dist.put(nextNum, dist.get(Integer.parseInt(vertex)) + 1);
                q.offer(nextNum);
            }
        }
        System.out.println(dist.getOrDefault(123456780, -1));
    }

    private static void swap(StringBuilder next, int blankIdx, int nextIdx) {
        next.setCharAt(blankIdx, next.charAt(nextIdx));
        next.setCharAt(nextIdx, '9');
    }
}
