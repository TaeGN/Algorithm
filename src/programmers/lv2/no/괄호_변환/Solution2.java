package programmers.lv2.no.괄호_변환;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution2 {
	public static void main(String[] args) {
		System.out.println(solution("(()())()"));
	}
	int N;
	StringBuilder result = new StringBuilder();
	StringBuilder u = new StringBuilder();
	StringBuilder v = new StringBuilder();
	Deque<Character> stack = new ArrayDeque<>();
	Map<Character, Integer> map = new HashMap<>();
	char[] input;
	public String solution(String p) {
		N = p.length();
		input = p.toCharArray();
		map.put('(', 1);
		map.put(')', -1);
				
		return getCorrectString(0);

	}
	private String getCorrectString(int idx) {
		if(idx >= N) return "";
		
		int start = idx;
		int cnt = map.get(input[idx]);
		while(++idx < N && cnt != 0) cnt += map.get(input[idx]);
		int end = idx;
		
//		String u = String.copyValueOf(input, start, end - start + 1);
		
		stack.push(input[start]);
		for(int i = start + 1; i < end; i++) {
			if(stack.isEmpty() || stack.peek() == input[i]) stack.push(input[i]);
			else stack.pop();
		}
		
		if(stack.isEmpty()) u.append(getCorrectString(end + 1));
		else {
			stack.clear();
			v.append('(').append(getCorrectString(end + 1)).append(')');
			u.deleteCharAt(0);
			u.deleteCharAt(u.length() - 1);
			for(char c : v.toString().toCharArray()) {
				if(c == '(') u.append(')');
				else u.append('(');
			}
			v.setLength(0);
		}
		
		return u.toString();
	}
}
