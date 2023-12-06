package programmers.lv1.ok.콜라_문제;

public class Solution1 {
    public int solution(int a, int b, int n) {
    	int result = 0, cnt;
    	while(n >= a) {
    		cnt = n / a;
    		result += b * cnt;
    		n = n % a + b * cnt;
    	}
        return result;
    }
}
