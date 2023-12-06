package programmers.lv2.ok.두_원_사이의_정수_쌍;

public class Solution1 {
	public static void main(String[] args) {
		int r1 = 2;
		int r2 = 3;
		System.out.println(solution(r1,r2));
	}
	
    public static long solution(int r1, int r2) {
        long answer = 0;
        final long r2r2 = (long) r2 * r2;
        final long r1r1 = (long) r1 * r1;
        
        for (int i = r2; i > 0; i--) {
        	answer += (long) Math.sqrt(r2r2 - (long) i * i) + 1;
			if(i <= r1) answer -= (long) Math.ceil(Math.sqrt(r1r1 - (long) i * i));
		}
        return answer;
    }
}
