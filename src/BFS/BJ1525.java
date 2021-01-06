package BFS;
//퍼즐
/* 배운 아이디어
1. 수 조합이 다르면 다른 정점 -> 9!의 정점(약 36만개, ok)
2. 배열 36만개를 만들 순 없음 -> 숫자나 문자열로 배열을 표현!
3. check,dist 배열의 인덱스로 9자리 수를 쓸 수 없음 -> hashmap!
4. 문자열 인덱스 <-> 배열 인덱스
 */

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;

// 상태->정점. 상태간 이동 비용-> 간선의 가중치
public class BJ1525 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int[] di = {0, 0, 1, -1}; //오,왼,아래,위
        final int[] dj = {1, -1, 0, 0};
        //상태(정점)를 배열로 표현하면 [][][][][]..[]10개.. 너무 낭비 -> 문자열로 표현
        //문자열보다는 숫자가 처리가 빠르고 공간상 유리!!!
        int start = 0;
        for (int i = 0; i < 9; i++) {
            int x = sc.nextInt();
            if (x == 0) x = 9;
            start = start * 10 + x;
        }
        //boolean[] check = new boolean[1000000000];//문자열 길이만큼. 너무 낭비.
        HashMap<Integer, Integer> dist = new HashMap<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        dist.put(start, 0);
        while (!q.isEmpty()) {
            int curNum = q.poll();
            String cur = Integer.toString(curNum);
            int zeroIdx = cur.indexOf("9");
            int i = zeroIdx / 3; //문자열 내 위치를 배열 상 위치로
            int j = zeroIdx % 3;
            //이동
            for (int k = 0; k < 4; k++) {
                int ni = i + di[k];
                int nj = j + dj[k];
                if (ni < 0 || ni >= 3 || nj < 0 || nj >= 3) continue;
                //swap
                int idx = ni * 3 + nj;
                StringBuilder next = new StringBuilder(cur);
                //next.replace(zeroIdx, zeroIdx + 1, String.valueOf(curStr.charAt(idx)));
                next.setCharAt(zeroIdx, next.charAt(idx));
                next.setCharAt(idx, '9');
                int nextNum = Integer.parseInt(next.toString());
                if (dist.containsKey(nextNum)) continue; //방문한 적 있음
                dist.put(nextNum, dist.get(curNum) + 1);
                q.offer(nextNum);
            }
        }
        System.out.println(dist.getOrDefault(123456789, -1));
    }
}
