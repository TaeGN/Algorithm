package programmers.lv2.no.마법의_엘리베이터;

public class Solution1 {
	public static void main(String[] args) {
		int storey = 1;
		int answer = Solution(storey);
		System.out.println(answer);
	}

	private static int Solution(int storey) {
		int cnt = 0;
		
		for(int i = 0; i < 9; i++) {
			int share = storey / 10;
			int remainder = storey % 10;
			if(remainder == 0) {
				storey = share;
			} else if(remainder > 5) {
				cnt += (10 - remainder);
				storey = share + 1;
			} else {
				cnt += remainder;
				storey = share;
			}
			System.out.println(share + " : " + remainder);
			System.out.println(storey + " : " + cnt);
			if(share == 0) {
				break;
			}
		}

		return cnt;
	}
}
