package programmers.lv2.ok.귤_고르기;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution1 {
	public static void main(String[] args) {
		int k = 6;
		int[] tangerine = {1,3,2,5,4,5,2,3};
		int answer = Solution1(k,tangerine);
		System.out.println(answer);
	}

	private static int Solution1(int k, int[] tangerine) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int size : tangerine) {
			map.compute(size, (key, v) -> (v == null) ? 1 : v + 1);
		}
		List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(map.entrySet());
		entryList.sort((a, b) -> map.get(b.getKey()) - map.get(a.getKey()));
		
		int count = 0;
		for(int i = 0; i < entryList.size(); i++) {
			count += entryList.get(i).getValue();
			if(count >= k) {
				return i + 1;
			}
		}
		
		System.out.println(map);
		System.out.println(entryList);
		return 0;
	}
}
