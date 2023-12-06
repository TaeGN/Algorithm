package programmers.lv1.ok.대충_만든_자판;

import java.util.Arrays;

public class Solution1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public int[] solution(String[] keymap, String[] targets) {
        int[] count = new int[26];
        Arrays.fill(count, Integer.MAX_VALUE);
        int idx;
        for(String s : keymap) {
            for(int i = 0, len = s.length(); i < len; i++) {
                idx = s.charAt(i) - 'A';
                count[idx] = Math.min(i + 1, count[idx]);
            }
        }
        
        int[] answer = new int[targets.length];
        int cnt;
        for(String s : targets) {
            for(int i = 0, len = s.length(); i < len; i++) {
                cnt = count[s.charAt(i) - 'A'];
                if(cnt == Integer.MAX_VALUE) {
                    answer[i] = -1;
                    break;
                }
                answer[i] += cnt;
            }
        }
        return answer;
    }

}
