package programmers.lv2.no.괄호_변환;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution1 {
	public static void main(String[] args) {
		System.out.println(solution("(()())()"));
	}
	static int N;
	static StringBuilder result = new StringBuilder();
	static StringBuilder u = new StringBuilder();
	static StringBuilder v = new StringBuilder();
	static Deque<Character> stack = new ArrayDeque<>();
	static Map<Character, Integer> map = new HashMap<>();
	static char[] input;
	static public String solution(String p) {
		N = p.length();
		input = p.toCharArray();
		map.put('(', 1);
		map.put(')', -1);
		getCorrectString(0);	
		return u.toString();

	}
	static private void getCorrectString(int idx) {
		if(idx >= N) return;
		
		int start = idx;
		int cnt = map.get(input[idx]);
		while(++idx < N && cnt != 0) cnt += map.get(input[idx]);
		int end = idx;
		
		stack.push(input[start]);
		for(int i = start + 1; i < end; i++) {
			if(stack.isEmpty() || stack.peek() == input[i]) stack.push(input[i]);
			else stack.pop();
		}
		
		u.append(String.copyValueOf(input, start, end - start));
		if(stack.isEmpty()) {
			u.append(String.copyValueOf(input, start, end - start));
			getCorrectString(end + 1);
		}
		else {
			stack.clear();
			getCorrectString(end + 1);
			u.append('(');
			for(char c : String.copyValueOf(input, start + 1, end - start - 2).toCharArray()) {
				if(c == '(') u.append(')');
				else u.append('(');
			}
			u.append(')');
		}
	}
}
