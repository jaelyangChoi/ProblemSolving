package DFS;

//DFS는 완전 탐색의 일종인가보다
//타겟 넘버
public class Q1 {
    static int dfs(int[] numbers, int idx, int sum, int target) {
        if (idx == numbers.length) return sum == target ? 1 : 0;
        return dfs(numbers, idx + 1, sum + numbers[idx], target) + dfs(numbers, idx + 1, sum - numbers[idx], target);
    }

    static public int solution(int[] numbers, int target) {
        return dfs(numbers, 0, 0, target);
    }
    /* 풀이2(백트래킹)
    static int cnt = 0;

    static void dfs(int[] numbers, int idx, int sum, int target) {
        if (sum < target) return;//백트래킹

        for (int i = idx; i < numbers.length; i++)
            dfs(numbers, i + 1, sum - 2 * numbers[i], target);

        if (sum == target) cnt++;
    }

    static public int solution(int[] numbers, int target) {
        int sum = 0;
        for (int e : numbers)
            sum += e;
        dfs(numbers, 0, sum, target);
        return cnt;
    }
     */

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 1, 1, 1}, 3));
    }
}
