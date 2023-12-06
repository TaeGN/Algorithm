package programmers.lv3.ok.다단계칫솔판매;

import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int N = enroll.length;
		int M = seller.length;
		final String ROOT = "-";
		
		// tree만들기
		Map<String, Node> nodeMap = new HashMap<>(N + 1);
		nodeMap.put(ROOT, new Node(null));
		for (int i = 0; i < N; i++) {
			nodeMap.put(enroll[i], new Node(nodeMap.get(referral[i])));
		}
		
		// seller정보 입력
		for (int i = 0; i < M; i++) {
			int val = amount[i] * 100;
			for (Node node = nodeMap.get(seller[i]); node != null && val > 0; node = node.parent, val /= 10) {
				node.val += val - (val / 10);
			}
		}
		
		int[] res = new int[N];
		for (int i = 0; i < N; i++) {
			res[i] = nodeMap.get(enroll[i]).val;
		}
		return res;
	}

	class Node {
		Node parent;
		int val;
		public Node(Node parent) {
			this.parent = parent;
		}
	}
	
	
}
