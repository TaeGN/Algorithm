package programmers.lv1.ok.기사단원의_무기;

public class Solution1 {
	public static void main(String[] args) {
		int number = 5;
		int limit = 3;
		int power = 2;
		int answer = Solution(number, limit, power);
		System.out.println(answer);
	}

	private static int Solution(int number, int limit, int power) {
		int result = 0;
		
		// 약수 개수 구하기
		for(int i = 1; i <= number; i++) {
			int cnt = CountDivisors(i);
			System.out.println(cnt);
			if(cnt > limit) {
				result += power;
			} else {
				result += cnt;
			}
		}
		return result;
	}

	private static int CountDivisors(int num) {
		int cnt = 0;
		for(int i = 1; i <= (int) Math.sqrt(num); i++) {
			if(num % i == 0) {
				if(num != i * i) {
					cnt += 2;
				} else {
					cnt += 1;
				}
			}
		}
		return cnt;
	}
}
