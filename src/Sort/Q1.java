package Sort;

import java.util.Arrays;

//k번째 수
public class Q1 {
    static public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i=0;i<commands.length;++i){
            int[] arr = Arrays.copyOfRange(array,commands[i][0]-1,commands[i][1]);
            Arrays.sort(arr);
            answer[i]=arr[commands[i][2]-1];
        }
        return answer;
    }
    public static void main(String[] args) {
        for(int e: solution(new int[]{1, 5, 2, 6, 3, 7, 4},new int[][]{{2,5,3},{4,4,1},{1,7,3}}))
            System.out.print(e+" ");
    }
}
