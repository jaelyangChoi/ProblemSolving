package Queue;
//다리를 지나는 트럭
import java.util.ArrayDeque;
import java.util.Queue;

//간과한 점: 시간이 지남에 따라 변화하는 것 고려x
public class Q3 {
    static public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> weightQ = new ArrayDeque<>();//도로를 건너는 중인 트럭
        Queue<Integer> startQ = new ArrayDeque<>();//도로 진입 시간
        int curTime = 1;
        int curWeight = 0;
        int i = 0;
        while (i < truck_weights.length) {
            if (curWeight + truck_weights[i] <= weight) {
                weightQ.offer(truck_weights[i]);
                curWeight += truck_weights[i++];
                startQ.offer(curTime++);//다리를 올라가는데 1초
            } else {//건너갈 때까지 기다린다
                curWeight -= weightQ.poll();
                curTime = startQ.poll() + bridge_length;//빠져나온 시각
            }
            //시간 변화 후 자연스레 빠지는 경우
            while (!startQ.isEmpty() && startQ.peek() + bridge_length <= curTime) {
                curWeight -= weightQ.poll();
                curTime = startQ.poll() + bridge_length;//빠져나온 시각
            }
        }
        while (!startQ.isEmpty())
            curTime = startQ.poll()+ bridge_length;
        return curTime;
    }

    public static void main(String[] args) {
        int bridge_length = 1;
        int weight = 2;
        int[] truck_weights = new int[]{1, 1, 1};
        System.out.println(solution(bridge_length, weight, truck_weights));
    }
}
