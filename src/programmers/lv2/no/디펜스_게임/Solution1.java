package programmers.lv2.no.디펜스_게임;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
	public static void main(String[] args) {
		int n = 7;
		int k = 3;
		int[] enemy = {4, 2, 4, 5, 3, 3, 1};
		int answer = Solution(n, k, enemy);
		System.out.println(answer);
	}

	private static int Solution(int n, int k, int[] enemy) {
		int sum = 0;
		
		// 무적권
		List<Integer> list = new ArrayList<>();
		list.add(enemy[0]);
		
		for(int i = 1; i < enemy.length; i++) {
			if(list.size() >= k) {
				if(list.get(0) < enemy[i]) {
					sum += list.get(0);
					list.remove(0);
					list.add(enemy[i]);
					list.sort((o1, o2) -> o1 - o2);
				} else {
					sum += enemy[i];
				}
			} else {
				if(list.get(0) > enemy[i]) {
					list.add(0, enemy[i]);
				} else {
					list.add(enemy[i]);
				}
			}
			System.out.println("list: " + list + " - i : " + i + " - sum : " + sum);
			if(sum > n) {
				return i;
			}
		}
		
		return enemy.length;
	}
}
