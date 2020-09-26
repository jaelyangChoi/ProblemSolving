package BruteForce;

public class Prac {
    static int[] arr;
    static int[] temp;
    static boolean check[] =new boolean[5];
    //순서x, m개 선택
    static void go1(int idx, int m){
        if(idx==m){
            for(int i: temp)
                System.out.print(i+" ");
            System.out.println();
            return;
        }
        for(int i=0;i<4;i++){
            if(check[i]) continue;
            check[i]=true;
            temp[idx]=i;//선택
            go1(idx+1,m);
            check[i]=false;
        }
    }
    //순서o, m개 선택
    static void go2(int idx, int m, int start){
        if(idx==m){
            for(int i: temp)
                System.out.print(i+" ");
            System.out.println();
            return;
        }
        for(int i=start;i<4;i++){
            if(check[i]) continue;
            check[i]=true;
            temp[idx]=i;//선택
            go2(idx+1,m, i+1);
            check[i]=false;
        }
    }
    //모든 집합의 부분집합이라면 비트마스크.. 근데 이건 m개..
    //순서 x, m개 미만 선택
    static void go3(){//0,1,2,3,01,02,03,,,,0123
        for(int m=1;m<arr.length;m++){
            go1(0,m);
        }
    }
    public static void main(String[] args) {
        arr= new int[]{0,1,2,3};
        int m=3;
        temp=new int[m];
        //go1(0,m);
        //go2(0,m,0);
        go3();
    }
}
