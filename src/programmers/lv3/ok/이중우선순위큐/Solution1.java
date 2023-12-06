package programmers.lv3.ok.이중우선순위큐;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution1 {
	public static void main(String[] args) {
		String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
		System.out.println(Arrays.toString(Solution(operations)));
	}

	private static int[] Solution(String[] operations) {
		PriorityQueue<Integer> maxQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
		PriorityQueue<Integer> minQueue = new PriorityQueue<>();
		String[] input;
		for(String operation : operations) {
			input = operation.split(" ");
			switch(input[0]) {
			case "I":
				maxQueue.offer(Integer.parseInt(input[1]));
				minQueue.offer(Integer.parseInt(input[1]));
				break;
			case "D":
				if(!maxQueue.isEmpty()) {
					if(input[1].equals("1")) {
						minQueue.remove(maxQueue.poll());
					} else {
						maxQueue.remove(minQueue.poll());
					}
				}
				break;
			}
		}
		
		return maxQueue.isEmpty() ? new int[] {0,0} : new int[] {maxQueue.peek(), minQueue.peek()};
	}
}
