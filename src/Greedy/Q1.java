package Greedy;

import java.util.Scanner;

//거스름 돈 문제
//그리디 - 무조건 큰 단위 화폐부터 준다
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();
        int coin = 0;//거스름돈 동전 개수

        coin += money / 500; //500원짜리 동전 최대한 줌
        money %= 500; //500원 미만으로
        coin += money / 100;
        money %= 100;
        coin += money / 50;
        money %= 50;
        coin += money / 10;

        System.out.println(coin);
        return;
    }
}
