package programmers.lv2.ok.문자열_압축;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
	public static void main(String[] args) {
		System.out.println(solution("aabbaccc"));
		System.out.println(solution("ababcdcdababcdcd"));
		System.out.println(solution("abcabcdede"));
		System.out.println(solution("abcabcabcabcdededededede"));
		System.out.println(solution("xababcdcdababcdcd"));
		System.out.println(solution("aaaaaaaaaabbbbbbbbbb"));
	}
    public static int solution(String s) {
    	int sLen = s.length();
    	String prev, cur = "";
    	int cnt, result, min = sLen;
    	for(int i = 1, len = sLen / 2; i <= len; i++) {
    		prev = s.substring(0, i);
    		result = 0;
    		cnt = 0;
    		for(int j = 0; j + i <= sLen; j += i) {
    			cur = s.substring(j, j + i);
    			if(prev.equals(cur)) cnt++;
    			else {
    				if(cnt > 1) result += String.valueOf(cnt).length();
    				result += i;
    				cnt = 1;
    				prev = cur;
    			}
    		}
    		if(cnt > 1) result += String.valueOf(cnt).length();
    		min = Math.min(min, result + i + sLen % i);
    	}
        return min;
    }
}
