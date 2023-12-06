package programmers.lv1.ok.크기가_작은_부분문자열;

public class Solution1 {
	public static void main(String[] args) {
		String t1 = "3141592";
		String t2 = "500220839878";
		String t3 = "10203";
		String p1 = "271";
		String p2 = "7";
		String p3 = "15";
		
		int answer1 = Solution(t1,p1);
		System.out.println(answer1);
		int answer2 = Solution(t2,p2);
		System.out.println(answer2);
		int answer3 = Solution(t3,p3);
		System.out.println(answer3);
		
	}

	private static int Solution(String t, String p) {
		int tLen = t.length();
		int pLen = p.length();
		int cnt = 0;
		
		for(int i = 0; i <= tLen - pLen; i++) {
			long tSub = Long.parseLong(t.substring(i, i + pLen));
			long pInt = Long.parseLong(p);
			if(tSub <= pInt) {
				cnt++;
			}
			System.out.println("t : " + tSub + " p : " + pInt);
		}
		
		return cnt;
	}
}
