package programmers.lv3.ok.가장_긴_팰린드롬;

public class Solution1 {
	public static void main(String[] args) {
		System.out.println(solution("ab"));
	}
    public static int solution(String s)
    {
    	int last = s.length() - 1;
    	int m;
    	if(last % 2 == 0) m = last / 2 - 1;
    	else m = last / 2;
    	int i = m;
    	int result = 1;
    	while(i >= 0) {
    		if((i + 1) * 2 <= result) break;
    		int j = 0, cnt = 1;
    		while(++j <= i && s.charAt(i - j) == s.charAt(i + j)) cnt += 2;
    		result = Math.max(result, cnt);
    		
    		if(i < last && s.charAt(i) == s.charAt(i + 1)) {
        		j = 0; cnt = 2;
        		while(++j <= i && s.charAt(i - j) == s.charAt(i + 1 + j)) cnt += 2;
        		result = Math.max(result, cnt);
    		}
    		i--;
    	}
    	
    	i = m + 1;
    	while(i <= last) {
    		if((last - i) * 2 + 1 <= result) break;
    		int j = 0, cnt = 1;
    		while(++j <= last - i && s.charAt(i - j) == s.charAt(i + j)) cnt += 2;
    		result = Math.max(result, cnt);
    		
    		if(i < last && s.charAt(i) == s.charAt(i + 1)) {
        		j = 0; cnt = 2;
        		while(++j < last - i && s.charAt(i - j) == s.charAt(i + 1 + j)) cnt += 2;
        		result = Math.max(result, cnt);
    		}
    		i++;
    	}
        return result;
    }
}
