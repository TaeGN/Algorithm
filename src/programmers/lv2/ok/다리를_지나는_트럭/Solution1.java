package programmers.lv2.ok.다리를_지나는_트럭;

public class Solution1 {
	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = {7,4,5,6};
		int result = Solution(bridge_length, weight, truck_weights);
		System.out.println(result);
	}

	private static int Solution(int bridge_length, int weight, int[] truck_weights) {
		int idx = 0, cnt = 1, cWeight;
		int tLen = truck_weights.length;
		
		while(idx < tLen) {
			cWeight = weight;
			while((cWeight -= truck_weights[idx]) >= 0) {
				System.out.println(cWeight);
				cnt++;
				if(++idx >= tLen) {
					break;
				}
			}
			cnt += (bridge_length - 1);
		}
		
		return cnt;
	}
	
//	private static int cBridge(int weight, int idx) {
//		if(queue.isEmpty()) return 0;
//		
//		queue.pop();
//		if(weight >= tWeights[idx]) {
//			queue.add(t)
//		}
//		
//		return 1 + cBridge();
//	}
}
