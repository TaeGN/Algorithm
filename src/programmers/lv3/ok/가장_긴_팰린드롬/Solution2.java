package programmers.lv3.ok.가장_긴_팰린드롬;

public class Solution2 {
    public int solution(String s)
    {
    	int sLen = s.length();
    	int i = sLen / 2;
    	int result = 1;
    	while(i >= 0) {
    		if(i < result / 2) break;
    		int j = 0, cnt = 1;
    		while(++j <= i && s.charAt(i - j) == s.charAt(i + j)) cnt += 2;
    		result = Math.max(result, cnt);
    		
    		if(i + 1 < sLen && s.charAt(i) == s.charAt(i + 1)) {
        		j = 0; cnt = 2;
        		while(++j <= i && s.charAt(i - j) == s.charAt(i + 1 + j)) cnt += 2;
        		result = Math.max(result, cnt);
    		}
    		i--;
    	}
    	
    	i = sLen / 2;
    	while(i < sLen) {
    		if(sLen - 1 - i < result / 2) break;
    		int j = 0, cnt = 1;
    		while(++j < sLen - i && s.charAt(i - j) == s.charAt(i + j)) cnt += 2;
    		result = Math.max(result, cnt);
    		
    		if(i + 1 < sLen && s.charAt(i) == s.charAt(i + 1)) {
        		j = 0; cnt = 2;
        		while(++j <= sLen - i - 1 && s.charAt(i - j) == s.charAt(i + 1 + j)) cnt += 2;
        		result = Math.max(result, cnt);
    		}
    		i++;
    	}
        return result;
    }
}
