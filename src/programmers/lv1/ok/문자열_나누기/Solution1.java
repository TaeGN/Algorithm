package programmers.lv1.ok.문자열_나누기;

public class Solution1 {
	public static void main(String[] args) {
		String s = "banana";
		int answer = Solution(s);
		System.out.println(answer);
	}

	private static int Solution(String s) {
		int i = 0;
		int result = 0;
		while(i < s.length()) {
			char x = s.charAt(i);
			int xCount = 1;
			int notXCount = 0;
			result++;
			
			i++;
			while(xCount != notXCount && i < s.length()) {
				char c = s.charAt(i++);
				if(x == c) {
					xCount++;
				} else {
					notXCount++;
				}
			}
		}
		return result;
	}
}
