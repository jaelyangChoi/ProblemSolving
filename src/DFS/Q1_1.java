package DFS;

public class Q1_1 {
    static public int solution(int[] numbers, int target) {
        return go(numbers, target, 0, 0);
    }

    //dfs==재귀==완전탐색
    static int go(int[] numbers, int target, int i, int sum) {
        if (i == numbers.length)
            return sum == target ? 1 : 0;
        return go(numbers, target, i + 1, sum + numbers[i]) + go(numbers, target, i + 1, sum - numbers[i]);
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 1, 1, 1}, 3));
    }
}
