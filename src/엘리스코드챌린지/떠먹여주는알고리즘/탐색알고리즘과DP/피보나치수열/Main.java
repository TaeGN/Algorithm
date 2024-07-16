package 엘리스코드챌린지.떠먹여주는알고리즘.탐색알고리즘과DP.피보나치수열;

import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = {0, 1, 1};
        for (int i = 3; i <= n; i++) {
            arr[0] = arr[1];
            arr[1] = arr[2];
            arr[2] = arr[0] + arr[1];
        }
        System.out.println(arr[Math.min(n, 2)]);
    }
}
