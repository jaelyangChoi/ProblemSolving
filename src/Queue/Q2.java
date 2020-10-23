package Queue;
//기능개발
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
//배운점: 큐에 한꺼번에 다 넣거나 꺼내는 아니라, 필요한 경우에, 조건에 따라 큐에 넣거나 꺼낸다
public class Q2 {
    static public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 0; i < progresses.length; i++) {
            int d = (int) Math.ceil((100-progresses[i]) /(double) speeds[i]);//남은 일수 구하기

            if (!q.isEmpty()&&q.peek()<d){
                answer.add(q.size());
                q.clear();
            }
            q.offer(d);
        }
        answer.add(q.size());

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        for (int e : solution(new int[]{93, 30, 55}, new int[]{1, 30, 5}))
            System.out.print(e + " ");
    }
}
