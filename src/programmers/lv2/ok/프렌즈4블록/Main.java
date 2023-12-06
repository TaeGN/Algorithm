package programmers.lv2.ok.프렌즈4블록;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static Solution solution = new Solution();
	
	public static void main(String[] args) {
		int m = 6;
		int n = 6;
		String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
//		int m = 8;
//		int n = 5;
//		String[] board = {"HGNHU", "CRSHV", "UKHVL", "MJHQB", "GSHOT", "MQMJJ", "AGJKK", "QULKK"};
//		int m = 5;
//		int n = 6;
//		String[] board = {"AAAAAA", "BBAATB", "BBAATB", "JJJTAA", "JJJTAA"};
//		int m = 4;
//		int n = 5;
//		String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		
		
		System.out.println(solution.solution(m, n, board));
	}
}
