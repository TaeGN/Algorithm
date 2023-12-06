package programmers.lv2.ok.숫자_변환하기;

public class Solution2 {
	public static void main(String[] args) {
		int x = 10;
		int y = 40;
		int n = 5;
		System.out.println(Solution(x,y,n));
	}
	// 시간초과
	private static int Solution(int x, int y, int n) {
		N = n;
		X = x;
		change(y, 0);
		return min == Integer.MAX_VALUE ? -1 : min;
	}
	static int N, X;
	static int min = Integer.MAX_VALUE;
	private static void change(int y, int cnt) {
		if(cnt >= min) return;
		if(y == X) {
			min = Math.min(min, cnt);
			return;
		}
		
		if(y % 3 == 0) change(y / 3, cnt + 1);
		if(y % 2 == 0) change(y / 2, cnt + 1);
		if(y - N > X) change(y - N, cnt + 1);
	}
}
