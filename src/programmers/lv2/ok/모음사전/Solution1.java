package programmers.lv2.ok.모음사전;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
	public static void main(String[] args) {
		String word = "AAAAE";
		System.out.println(Solution(word));
	}

	private static int Solution(String word) {
		makeDict("");
		return dict.indexOf(word);
	}
	
	static List<String> dict = new ArrayList<>();
	static char[] alp = new char[] {'A','E','I','O','U'};
	private static void makeDict(String word) {
		dict.add(word);
		if(word.length() == 5) {
			return;
		}
		
		for(int i = 0; i < 5; i++) {
			makeDict(word + alp[i]);
		}
	}
}
