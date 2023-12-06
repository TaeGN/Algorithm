package programmers.lv2.ok.문자열_압축;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
	public static void main(String[] args) {
		System.out.println(solution("aabbaccc"));
		System.out.println(solution("ababcdcdababcdcd"));
		System.out.println(solution("abcabcdede"));
		System.out.println(solution("abcabcabcabcdededededede"));
		System.out.println(solution("xababcdcdababcdcd"));
	}
    public static int solution(String s) {
    	int sLen = s.length();
    	String prev, cur = "";
    	int cnt = 1, result, min = sLen;
    	List<String> list = new ArrayList<>();
    	String a = "";
    	for(int i = 1, len = sLen / 2; i <= len; i++) {
    		a = "";
    		prev = s.substring(0, i);
    		result = 0;
    		cnt = 0;
    		for(int j = 0; j + i <= sLen; j += i) {
    			cur = s.substring(j, j + i);
    			if(prev.equals(cur)) cnt++;
    			else {
    				if(cnt != 1) result += i + 1;
    				else result += i;
    				a += ((cnt == 1 ? "" : cnt) + prev);
    				System.out.print(result + " ");
    				cnt = 1;
    				prev = cur;
    			}
    		}
    		a += ((cnt == 1 ? "" : cnt) + cur);
    		list.add(a);
    		if(cnt != 1) result += i + 1;
			else result += i;
    		System.out.print(result + " ");
    		System.out.println();
    		min = Math.min(min, result + sLen % i);
    	}
    	System.out.println(list);
        return min;
    }
}
