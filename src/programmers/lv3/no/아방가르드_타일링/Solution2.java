package programmers.lv3.no.아방가르드_타일링;

public class Solution2 {
    public int solution(int n) {
    	final long MOD = 1_000_000_007;
    	long[] prev = new long[Math.max(n + 1, 6)];
    	prev[0] = 1;
    	prev[1] = 1;
    	prev[2] = 3;
    	prev[3] = 10;
    	prev[4] = 23;
    	prev[5] = 62;
    	int[] p = {1, 2, 5, 2, 2, 4};
    	for (int i = 6; i <= n; i++) {
    		for (int j = 0; j < 6; j++) {
    			prev[i] = (prev[i] + prev[i - j - 1] * p[j]) % MOD;
			}
		}
        return (int) prev[n];
    }
}
