package programmers.lv3.no.아방가르드_타일링;

public class Solution1 {
    public int solution(int n) {
    	final long MOD = 1_000_000_007;
    	long[] prev = {1, 1, 3, 10, 23, 62, 0};
    	int[] p = {4, 2, 2, 5, 2, 1};
    	for (int i = 6; i <= n; i++) {
    		prev[6] = 0;
    		for (int j = 0; j < 6; j++) {
    			prev[6] = (prev[6] + prev[j] * p[j]) % MOD;
			}
    		for (int j = 0; j < 6; j++) {
    			prev[j] = prev[j + 1];
			}
		}
        return n < 6 ? (int) prev[n] : (int) prev[6];
    }
}
