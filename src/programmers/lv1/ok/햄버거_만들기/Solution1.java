package programmers.lv1.ok.햄버거_만들기;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
	public static void main(String[] args) {
		int[] ingredient = {2, 1, 1, 2, 3, 1, 2, 3, 1};
		int answer = Solution(ingredient);
		System.out.println(answer);
	}

	private static int Solution(int[] ingredient) {
		List<Integer> list = new ArrayList<>();
		int cnt = 0;
		for(int i : ingredient) {
			if(i == 1 && list.size() >= 3 &&
				list.get(list.size() - 3) == 1 &&
				list.get(list.size() - 2) == 2 &&
				list.get(list.size() - 1) == 3) {
				
				cnt++;
				list.remove(list.size() - 1);
				list.remove(list.size() - 1);
				list.remove(list.size() - 1);
			} else {
				list.add(i);
			} 
		}
		
		return cnt;
	}
}
