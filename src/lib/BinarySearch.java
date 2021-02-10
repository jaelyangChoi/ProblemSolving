package lib;

import java.util.Arrays;

//전제: 정렬
//O(logN) 빠르다!
public class BinarySearch {
    //탐색 대상: 크면서 가장 작은 수
    static int upper_bound(int[] a, int target) {
        int left = 0;
        int right = a.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] <= target) left = mid + 1; //같아도 계속 전진
            else right = mid;
        }
        return left;
    }

    //탐색 대상: 크거나 같으면서 가장 작은 수
    static int lower_bound(int[] a, int target) {
        int left = 0;
        int right = a.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 2,2, -3, 3};
        Arrays.sort(arr);
        for (int x : arr)
            System.out.print(x + " ");
        System.out.println();
        int target = 2;
        int lower = lower_bound(arr, target);
        int upper = upper_bound(arr, target);
        System.out.println("lower: " + lower);
        System.out.println("upper: " + upper);
        System.out.println("타겟과 같은 것 갯수: " + (upper - lower));
    }
}
