package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

//가장 먼 노드
public class Q1 {
    static public int solution(int n, int[][] edge) {
        //인접 리스트(리스트 배열)
        ArrayList<Integer>[] g = (ArrayList<Integer>[])new ArrayList[n+1];
        //인접 리스트 초기화
        for(int i=0;i<n+1;++i)
            g[i] = new ArrayList<>();

        //양방향 간선
        for(int[] e: edge){
            g[e[0]].add(e[1]); //from->to
            g[e[1]].add(e[0]);//to->from
        }
        //최단 거리 -> bfs
        int[] d = new int[n+1]; //방문 체크 및 거리 저장
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        d[1]=1;//초기값 1. 나중에 값에서 1빼줌
        while(!q.isEmpty()){
            int startV = q.poll();
            for(int v: g[startV]){
                if(d[v]!=0) continue; //이미 방문함
                d[v]=d[startV]+1;
                q.offer(v);
            }
        }

        int max = d[0];
        int answer=0;
        for(int e:d){
            if(max==e)
                ++answer;
            else if(max<e){
                max=e;
                answer=1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(6, new int[][]{{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}}));
    }

}
