package programmers.lv2.no.조이스틱;

public class Solution1 {
	public static void main(String[] args) {
//		String name = "JEROEN";
//		String name = "JAN";
		String name = "AAB";
		System.out.println(Solution(name));
	}

	private static int Solution(String name) {
		int cnt = 0;
		int len = name.length();
		cnt += len - 1;
		if(len > 1) {	// 시작지점에서 왼쪽이나 오른쪽의 연속된 'A'의 개수의 최대값 빼주기
			int right = 0;
			int left = 0;
			for(int i = 1; i < len; i++) {
				if(name.charAt(i) != 'A') break;
				right++;
			}
			for(int i = len - 1; i > 0; i--) {
				if(name.charAt(i) != 'A') break;
				left++;
			}
			cnt -= Math.max(right, left);
		}
		
		for(char c : name.toCharArray()) {
			
			if(c - 'A' > 13) {
				cnt += (26 - (c - 'A'));
			} else {
				cnt += (c - 'A');
			}
		}
		return cnt;
	}
}
