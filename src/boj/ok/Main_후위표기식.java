package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main_후위표기식 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Map<Character, Integer> map = new HashMap<>();
		map.put(')', 0);
		map.put('(', 0);
		map.put('+', 1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
		
		char[] input = br.readLine().toCharArray();
		Deque<Character> stack = new ArrayDeque<>();
		
		for (int i = 0; i < input.length; i++) {
			
			// A-Z
			if(Character.isAlphabetic(input[i])) {
				sb.append(input[i]);
			} 
			
			// +, -, *, /
			else if(map.get(input[i]) > 0) {
				while(!stack.isEmpty() && map.get(stack.peek()) >= map.get(input[i])) {
					sb.append(stack.pop());
				}
				stack.push(input[i]);
			}
			
			// (
			else if(input[i] == '(') {
				stack.push(input[i]);
			}
			
			// )
			else {
				while(!stack.isEmpty() && stack.peek() != '(') {
					sb.append(stack.pop());
				}
				stack.pop();
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	}
}
