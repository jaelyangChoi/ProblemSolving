package _lib;

import java.util.ArrayList;
import java.util.Scanner;

//소수: 약수가 1과 자기 자신 밖에 없는 수
//2,3,5,7,11,13,,
public class Prime {
    /* 소수인지 판별 */
    //2보다 크고 n-1보다 같거나 작은 자연수로 나누어 떨어지면 안된다.
    //O(√N)
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;
        return true;
    }

    /* N까지의 모든 소수 구하기 */
    //O(N√N)
    static ArrayList<Integer> getPrimes(int n) {
        ArrayList<Integer> prime = new ArrayList<>();
        for (int num = 2; num <= n; num++) {
            boolean ok = true;
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) prime.add(num);
        }
        return prime;
    }

    //에라토스테네스의 체
    // O(N*loglogN)
    static ArrayList<Integer> getPrimeEra(int n){
        boolean[] check = new boolean[n + 1]; //에라토스테네스의 체
        ArrayList<Integer> prime = new ArrayList<>();
        for (int num = 2; num <= n; num++) {
            if (!check[num]) {//소수의 배수는 소수가 아니다
                prime.add(num);
                //for (int i = num * 2; i <= n; i += num) //num의 크기에 따라 2로 바꾼다. n이 백만일 경우 int 범위를 넘어감
                for (int i = num * num; i <= n; i += num) //num*(num-1)까지는 이전 2~(num-1)의 배수로 지워졌다.
                    check[i] = true;

            }
        }
        return prime; //check를 반환하면 어떤 수 x가 소수인지 아닌지 O(1) 시간만에 알 수 있다.
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //System.out.println(isPrime(n));
        //System.out.println(getPrimes(n));

    }
}
