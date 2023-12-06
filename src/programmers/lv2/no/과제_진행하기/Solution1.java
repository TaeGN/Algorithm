package programmers.lv2.no.과제_진행하기;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution1 {
	public String[] solution(String[][] plans) {
		Arrays.sort(plans, (o1, o2) -> {
			if (!o1[1].substring(0, 2).equals(o2[1].substring(0, 2)))
				return Integer.compare(Integer.valueOf(o1[1].substring(0, 2)), Integer.valueOf(o2[1].substring(0, 2)));
			return Integer.compare(Integer.valueOf(o1[1].substring(3, 5)), Integer.valueOf(o2[1].substring(3, 5)));
		});
		int len = plans.length, idx = 0;
		String[] answer = new String[len];
		String[] prev = plans[0], cur = null, ;
		Deque<String[]> stack = new ArrayDeque<>();
		for (int i = 1; i < len; i++) {
			cur = plans[i];
			int dTime = getDiffTime(prev, cur);
			int pTime = Integer.valueOf(prev[2]);
			if(pTime <= dTime) {
				answer[idx++] = prev[0];
				cur[2] = String.valueOf(Integer.valueOf(cur[2]) - (dTime - pTime));
			} else {
				
			}
		}
		return answer;
	}

	private int getDiffTime(String[] prev, String[] cur) {
		return Integer.valueOf(cur[1].substring(0, 2)) * 60 + Integer.valueOf(cur[1].substring(3, 5))
				- (Integer.valueOf(prev[1].substring(0, 2)) * 60 + Integer.valueOf(prev[1].substring(3, 5)));
	}
}
