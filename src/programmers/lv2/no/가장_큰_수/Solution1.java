package programmers.lv2.no.가장_큰_수;

import java.util.Arrays;

public class Solution1 {
	public static void main(String[] args) {
		int[] numbers = {12, 121};
//		int[] numbers = {3, 33, 303, 34, 53, 39};
		System.out.println(Solution(numbers));
	}

	private static String Solution(int[] numbers) {
		String[] numArr = new String[numbers.length];
		int i = 0;
		for(int num : numbers) {
			numArr[i++] = String.valueOf(num);
		}
		Arrays.sort(numArr, (o1, o2) -> {
			char c;
			int len1 = o1.length();
			int len2 = o2.length();
			if(len1 > len2) {
				c = o2.charAt(0);
				for(int j = 0; j < len1 - len2; j++) {
					o2 += c;
				}
			} else if(len1 < len2) {
				c = o1.charAt(0);
				for(int j = 0; j < len2 - len1; j++) {
					o1 += c;
				}
			}
			System.out.println(o1);
			System.out.println(o2);
			for(int j = 0, size = o1.length(); j < size; j++) {
				if(o1.charAt(j) != o2.charAt(j)) return o2.charAt(j) - o1.charAt(j);
			}
			return -1;
		});
		StringBuilder sb = new StringBuilder();
		for(String s : numArr) {
			sb.append(s);
		}
		
		return sb.toString();
	}
}
