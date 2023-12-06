package programmers.lv1.ok.명예의_전당;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {
	public static void main(String[] args) {
		int k = 3;
		int[] score = {10, 100, 20, 150, 1, 100, 200};
		int[] answer = Solution(k,score);
		System.out.println(Arrays.toString(answer));
	}

	private static int[] Solution(int k, int[] score) {
		List<Integer> hall = new ArrayList<>();
		int[] result = new int[score.length];
		
		hall.add(score[0]);
		result[0] = score[0];
		
		for(int i = 1; i < score.length; i++) {
			if(hall.size() >= k) {
				if(hall.get(0) < score[i]) {
					hall.remove(0);		
					hall.add(score[i]);
					hall.sort((a,b) -> a-b);
				}
			} else {
				hall.add(score[i]);
				hall.sort((a,b) -> a-b);
			}
			result[i] = hall.get(0);
		}
		
		return result;
	}
}
