package Greedy;

//체육복

//그리디 접근: 왼쪽부터 주고 없으면 오른쪽을 준다는 접근이 그리디가 맞았다. 선택에 우선순위를 두는 것. 그것은 단순히 if() else if()여도 된다.
//피드백: 체육복 정보를 한꺼번에 저장하면 계산하기 쉬울 건데 메모리 아낀다고 안 만들었고 더 복잡해졌다. 메모리가 문제가 아니라 효율성이 문제다..
public class Q2 {
    static public int solution(int n, int[] lost, int[] reserve) {
        int[] clothes = new int[n + 1]; //-1: 없음, 0: 있음, 1: 여벌 있음
        for (int i = 0; i < lost.length; ++i)
            clothes[lost[i]] = -1;
        for (int i = 0; i < reserve.length; ++i)
            ++clothes[reserve[i]];

        for (int i = 1; i <= n; ++i) {
            if (clothes[i] == -1) {//체육복이 없을 때 빌릴 수 있는가?
                //앞부터 빌림
                if (clothes[i - 1] == 1) {
                    --clothes[i - 1];
                    ++clothes[i];
                }
                //앞에 없으면 뒤에서 빌림
                else if (i + 1 <= n && clothes[i + 1] == 1){
                    --clothes[i + 1];
                    ++clothes[i];
                }
            }
        }
        int cnt=0;
        for(int c: clothes)
            if(c==-1) cnt++;
        return n-cnt;
    }
    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{2, 4}, new int[]{3}));
    }
}
