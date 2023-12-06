package programmers.lv3.no.거스름돈;

import java.util.Arrays;

public class Solution {
    public int solution(int n, int[] money) {
    	int[] dp = new int[n + 1];
    	final int MOD = 1_000_000_007;
    	int len = money.length;
    	for (int i = 0; i < len; i++) {
			for (int j = n; j >= money[i]; j--) {
				dp[j] = (dp[j] + dp[n - money[i]]) % MOD;
			}
			for (int j = money[i]; j <= n; j += money[i]) {
				dp[j]++;
			}
			System.out.println(Arrays.toString(dp));
		}
        return (int)dp[n];
    }
}
