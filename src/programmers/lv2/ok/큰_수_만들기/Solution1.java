package programmers.lv2.ok.큰_수_만들기;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1 {
	public static void main(String[] args) {
		String number = "4321";
		int k = 2;
//		String number = "4177252841";
//		int k = 4;
		System.out.println(Solution(number, k));
	}

	private static String Solution(String number, int k) {
		Deque<Integer> stack = new ArrayDeque<>();
		int i, prev, cur;
		a:for(i = 0; i < number.length(); i++) {
			cur = number.charAt(i) - '0';
			while(!stack.isEmpty()) {
				prev = stack.peek();
				if(prev >= cur) break;
				stack.pop();
				if(--k == 0) break a;
			}
			stack.push(cur);
		}
		for(;k > 0; k--) {
			stack.pop();
		}
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pollLast());
		}
		sb.append(number.substring(i));
		return sb.toString();
	}
}
