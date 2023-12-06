package programmers.lv1.no.신규_아이디_추천;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution1 {
	public static void main(String[] args) {
		System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
	}
    public static String solution(String new_id) {
    	Deque<Character> id = new ArrayDeque<>();
    	id.offer(' ');
        for(char c : new_id.toLowerCase().toCharArray()) {
        	if(Character.isAlphabetic(c) 
        			|| Character.isDigit(c) 
        			|| c == '-'
        			|| c == '_'
        			|| (c == '.' && id.peekLast() != '.')) {
        		id.offer(c);
        	}
        }
        id.poll();
        
        while(!id.isEmpty() && id.peek() == '.') id.poll();
        while((!id.isEmpty() && id.peekLast() == '.') || id.size() >= 16) id.pollLast();
        if(id.isEmpty()) return "aaa";
        while(id.size() <= 2) id.offer(id.peekLast());

        StringBuilder sb = new StringBuilder();
        while(!id.isEmpty()) sb.append(id.poll());
        return sb.toString();
    }
}
