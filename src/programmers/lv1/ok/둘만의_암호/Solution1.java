package programmers.lv1.ok.둘만의_암호;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution1 {
	public static void main(String[] args) {
		System.out.println(solution("aukks","wbqd",	5));
	}
    public static String solution(String s, String skip, int index) {
        
    	Set<Integer> set = new HashSet<>();
    	for(char c : skip.toCharArray()) {
    		set.add(c - 'a');
    	}
    	
    	List<Integer> list = new ArrayList<>();
    	for(int i = 0; i < 26; i++) {
    		if(!set.contains(i)) list.add(i);
    	}
    	
    	int len = list.size();
    	StringBuilder sb = new StringBuilder();
    	for(char c : s.toCharArray()) {
    		sb.append((char) (list.get((list.indexOf(Integer.valueOf(c - 'a')) + index) % len) + 'a'));
    	}
    	
        return sb.toString();
    }
}
