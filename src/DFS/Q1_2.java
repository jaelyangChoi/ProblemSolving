package DFS;

public class Q1_2 {
    static public int solution(int[] numbers, int target) {
        return go(numbers, target, 0,0);
    }

    private static int go(int[] numbers, int target, int idx, int sum) {
        if(idx==numbers.length) return sum==target?1:0;
        return go(numbers,target,idx+1,sum+numbers[idx])+go(numbers,target,idx+1, sum-numbers[idx]);
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 1, 1, 1}, 3));
    }
}
