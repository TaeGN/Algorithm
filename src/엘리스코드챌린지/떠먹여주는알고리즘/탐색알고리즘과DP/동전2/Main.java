package 엘리스코드챌린지.떠먹여주는알고리즘.탐색알고리즘과DP.동전2;

import java.io.*;
import java.util.*;

class Main {
    private static final int IMPOSSIBLE = Integer.MAX_VALUE >> 2;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int[] priceArr = {3, 5};
        int[] dp = new int[N + 1];
        Arrays.fill(dp, IMPOSSIBLE);
        dp[0] = 0;
        for(int i = 1; i <= N; i++) {
            for(int price: priceArr) {
                if(i >= price) dp[i] = Math.min(dp[i], 1 + dp[i - price]);
            }
        }

        System.out.println(dp[N] == IMPOSSIBLE ? -1 : dp[N]);
    }
}
