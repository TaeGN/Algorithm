package programmers.lv2.ok.다리를_지나는_트럭;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution2 {
	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = {7,4,5,6};
		int result = Solution(bridge_length, weight, truck_weights);
		System.out.println(result);
	}

	private static int Solution(int bridge_length, int weight, int[] truck_weights) {
		queue = new ArrayDeque<>();
		
		for(int i = 0; i < bridge_length; i++) {
			queue.add(0);
		}
		
		tWeights = truck_weights;
		tLen = truck_weights.length;
		
		return cBridge(weight, 0);
	}
	
	static Deque<Integer> queue;
	static int[] tWeights;
	static int tLen;
	private static int cBridge(int weight, int idx) {
		if(queue.isEmpty()) return 0;
		
		weight += queue.pop();
		if(idx < tLen) {
			if(weight >= tWeights[idx]) {
				weight -= tWeights[idx];
				queue.add(tWeights[idx++]);
			} else {
				queue.add(0);
			}
		}
		
		return 1 + cBridge(weight, idx);
	}
}
