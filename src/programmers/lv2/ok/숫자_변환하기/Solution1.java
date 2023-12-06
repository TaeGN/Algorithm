package programmers.lv2.ok.숫자_변환하기;

public class Solution1 {
	public static void main(String[] args) {
		int x = 10;
		int y = 40;
		int n = 5;
		System.out.println(Solution(x,y,n));
	}

	private static int Solution(int x, int y, int n) {
		int a = y;
		int cnt3 = 0;
		while(a % 3 == 0) {
			cnt3++;
			a /= 3;
		}
		a = y;
		int cnt2 = 0;
		while(a % 2 == 0) {
			cnt2++;
			a /= 2;
		}
		
		int min = Integer.MAX_VALUE;
		int b;
		for(int i = 0; i <= cnt3; i++) {	// 곱하기3 연산
			a = y / (int) Math.pow(3, i);
			for(int j = 0; j <= cnt2; j++) {	// 곱하기2 연산
				b = a / (int) Math.pow(2, j);
				if((b - x) % n != 0) continue;
				min = Math.min(min, i + j + (b - x) / n);	// +n 연산
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}
}
