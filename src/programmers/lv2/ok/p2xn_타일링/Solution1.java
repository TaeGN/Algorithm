package programmers.lv2.ok.p2xn_타일링;

public class Solution1 {
	public static void main(String[] args) {
		int n = 60000;
		System.out.println(Solution(n));
	}

	private static int Solution(int n) {
		int[] cntArr = new int[n + 1];
		cntArr[1] = 1;
		cntArr[2] = 2;
		for(int i = 3; i <= n; i++) {
			cntArr[i] = (cntArr[i - 1] + cntArr[i - 2]) % 1000000007;
		}
		return cntArr[n];
	}
}
