package programmers.lv2.no.전화번호_목록;

import java.util.Arrays;

public class Solution2 {
	public static void main(String[] args) {
//		String s = "11199";
//		System.out.println(s.indexOf("119"));
		String[] phone_book = {"119", "97674223", "1195524421"};
		System.out.println(Solution(phone_book));
	}

	private static boolean Solution(String[] phone_book) {
		Arrays.sort(phone_book);
		int size = phone_book.length;
		for(int i = 0; i < size - 1; i++) {
//			if(phone_book[i + 1].indexOf(phone_book[i]) == 0) return false;
			if(phone_book[i + 1].startsWith(phone_book[i])) return false;
		}
		return true;
	}
	
	private static boolean Solution2(String[] phone_book) {
		Arrays.sort(phone_book, (o1,o2) -> o1.length() - o2.length());
		int size = phone_book.length;
		String s;
		for(int i = 0; i < size - 1; i++) {
			s = phone_book[i];
			for(int j = i + 1; j < size; j++) {
				if(phone_book[j].indexOf(s) == 0) return false;
			}
		}
		return true;
	}
}
