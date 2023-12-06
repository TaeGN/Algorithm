package programmers.lv1.ok.가장_가까운_같은_글자;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {
	public static void main(String[] args) {
		String s = "banana";
		int[] answer = Solution(s);
		System.out.println(answer);
	}

	private static int[] Solution(String s) {
		Map<Character, Integer> map = new HashMap<>();
		int[] result = new int[s.length()];
		
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			result[i] = map.containsKey(c) ? i - map.get(c) : -1;
			map.put(c, i);
		}
		
		return result;
	}
}
