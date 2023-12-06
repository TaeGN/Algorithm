package programmers.lv2.ok.연속된_부분_수열의_합;

public class Solution2 {
    public int[] solution(int[] sequence, int k) {
    	int[] answer = new int[2];
    	int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE, len = sequence.length;
    	while(true) {
    		while(sum < k) {
    			if(end == len) break;
    			sum += sequence[end++];
    		} 
    		while(sum > k) {
    			sum -= sequence[start++];
    		}
    		if(sum == k) {
    			if(end - start < minLen) {
    				minLen = end - start;
    				answer[0] = start;
    				answer[1] = end - 1;
    			}
    			if(end == len) break;
    			sum -= sequence[start++];
    			sum += sequence[end++];
    		}
    	}
        return answer;
    }
}
