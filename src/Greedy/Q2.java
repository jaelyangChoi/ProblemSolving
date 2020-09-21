package Greedy;

//체육복

//그리디 접근: 왼쪽부터 주고 없으면 오른쪽을 준다는 접근이 그리디가 맞았다. 선택에 우선순위를 두는 것. 그것은 단순히 if() else if()여도 된다.
//그러나 구현에서 틀렸다. 대체 뭐가 잘못된걸까?
public class Q2 {
    static public int solution(int n, int[] lost, int[] reserve) {
        int lostN = lost.length;
        //lost 중 체육복을 빌릴 수 있는 놈들을 제거
        int idx = 0;
        int[] check = new int[lostN];
        //1. 자기 자신부터
        for (int i = 0; i < lostN; ++i) {
            int l = lost[i];
            while (idx < reserve.length && reserve[idx] < l) ++idx;
            if (idx >= reserve.length) break;
            if (reserve[idx] == l) {
                lost[i] = -1;
                reserve[idx] = -1;
                ++idx;
                --lostN;
            }
        }
        idx = 0;
        for (int i=0;i<lost.length;++i) {
            int l=lost[i];
            if (l == -1) continue;
            while (idx < reserve.length && reserve[idx] < l - 1) ++idx;
            if (idx >= reserve.length) break;
            //2. 앞 놈부터 소진, 없으면 뒷놈
            if (reserve[idx] -1 == l || reserve[idx]+1 == l) {
                lost[i]=-1;
                --lostN;
                ++idx;
            }
        }

        return n - lostN;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{2, 4}, new int[]{3}));
    }
}
