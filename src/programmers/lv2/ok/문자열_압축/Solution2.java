package programmers.lv2.ok.문자열_압축;

public class Solution2 {
    public int solution(String s) {
    	int sLen = s.length();
    	String prev, cur;
    	int cnt, result, min = sLen;
    	
    	for(int i = 1, len = sLen / 2; i <= len; i++) {
    		prev = s.substring(0, i);
    		result = 0;
    		cnt = 0;
    		for(int j = 0; j + i <= sLen; j += i) {
    			cur = s.substring(j, j + i);
    			if(prev.equals(cur)) cnt++;
    			else {
    				if(cnt != 1) result += i + 1;
    				else result += i;
    				cnt = 1;
    				prev = cur;
    			}
    		}
    		if(cnt != 1) result += i + 1;
			else result += i;
    		min = Math.min(min, result + sLen % i);
    	}
        return min;
    }
}
