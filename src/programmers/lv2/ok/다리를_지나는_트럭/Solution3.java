package programmers.lv2.ok.다리를_지나는_트럭;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution3 {
	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = {7,4,5,6};
		int result = Solution(bridge_length, weight, truck_weights);
		System.out.println(result);
	}

	private static int Solution(int bridge_length, int weight, int[] truck_weights) {
		Deque<Integer> queue = new ArrayDeque<>();
		int tLen = truck_weights.length;
		
		for(int i = 0; i < bridge_length; i++) {
			queue.add(0);
		}
		
		int idx = 0, cnt = 0;
		while(!queue.isEmpty()) {
			weight += queue.pop();
			if(idx < tLen) {
				if(weight >= truck_weights[idx]) {
					weight -= truck_weights[idx];
					queue.add(truck_weights[idx++]);
				} else {
					queue.add(0);
				}
			}
			cnt++;
		}
		return cnt;
	}

}
