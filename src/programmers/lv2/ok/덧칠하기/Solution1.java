package programmers.lv2.ok.덧칠하기;

public class Solution1 {
	public static void main(String[] args) {
		
	}
	
    public int solution(int n, int m, int[] section) {
    	int len = section.length, i = 0, cnt = 0, start;
    	while(i < len) {
    		cnt++;
    		start = section[i];
    		while(++i < len && section[i] < start + m);
    	}
        return cnt;
    }
}
