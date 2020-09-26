package DFS;

//타겟 넘버
public class Q1 {
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

    /*
    int dfs(int[] numbers, int idx, int sum, int target) {
            int cnt = 0;
            if (idx == numbers.length) {
                if (sum == target) cnt++;
                return cnt;
            }
            cnt += dfs(numbers, idx + 1, sum + numbers[idx], target);
            cnt += dfs(numbers, idx + 1, sum - numbers[idx], target);
            return cnt;
        }

        public int solution(int[] numbers, int target) {
            return dfs(numbers, 0, 0,target);
        }*/
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 1, 1, 1}, 3));
    }
}
