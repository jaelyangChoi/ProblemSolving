package BFS;
//퍼즐

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;

//틀린 이유:상태가 다르면 다른 정점. 정점의 기준을 잘못 잡았다.이걸 풀다가 깨달았다.. 만만하게 보고 달려들지 말자..
//틀린 이유:매번 배열을 복사하는건 시간,공간적으로 굉장히 무리.
/* 배운 아이디어
1. 수 조합이 다르면 다른 정점 -> 9!의 정점(약 36만개, ok)
2. 배열 36만개를 만들 순 없음 -> 숫자나 문자열로 배열을 표현!
3. check,dist 배열의 인덱스로 9자리 수를 쓸 수 없음 -> hashmap!
4. 문자열 인덱스 <-> 배열 인덱스
 */
public class BJ3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int[] di = {0, 0, -1, 1};//왼,오,위,아래
        final int[] dj = {-1, 1, 0, 0};
        int start = 0;
        for (int i = 0; i < 9; i++) {
            int x = sc.nextInt();
            if (x == 0) x = 9;//0이면 소실될 수 있음
            start = start * 10 + x;//배열을 숫자로 표현
        }
        //방문 여부, 거리를 나타내기 위한 해시맵
        HashMap<Integer, Integer> dist = new HashMap<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        dist.put(start, 0);
        while (!q.isEmpty()) {
            int now_num = q.poll();
            String now = Integer.toString(now_num);
            int zeroIdx = now.indexOf('9');
            //문자열 위치->배열 위치
            int zi = zeroIdx / 3;
            int zj = zeroIdx % 3;
            //연산
            for (int k = 0; k < 4; k++) {
                int ni = zi + di[k];//이동
                int nj = zj + dj[k];
                if (ni >= 0 && nj >= 0 && ni < 3 && nj < 3) {
                    StringBuilder next = new StringBuilder(now);//교환을 위해
                    next.setCharAt(zeroIdx, next.charAt(ni*3+nj));
                    next.setCharAt(ni*3+nj, '9');
                    int next_num = Integer.parseInt(next.toString());
                    if (dist.containsKey(next_num)) continue;//방문한 적 있음
                    dist.put(next_num, dist.get(now_num) + 1);//방문기록+거리
                    q.offer(next_num);
                }
            }
        }
        System.out.println(dist.getOrDefault(123456789, -1));
    }
}