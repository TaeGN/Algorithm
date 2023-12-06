package programmers.lv2.ok.뒤에_있는_큰_수_찾기;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1 {
	public static void main(String[] args) {
		
	}
	
    public int[] solution(int[] numbers) {
    	final int INF = Integer.MAX_VALUE;
    	int len = numbers.length;
    	int[] result = new int[len];
    	Deque<Integer> stack = new ArrayDeque<>();
    	stack.push(INF);
    	int prev;
    	for(int i = len - 1; i >= 0; i--) {
    		prev = stack.peek();
    		if(prev > numbers[i]) {
    			result[i] = prev == INF ? -1 : prev;
    			stack.push(numbers[i]);
    		} else {
    			stack.pop();
    			while(stack.peek() <= numbers[i]) stack.pop();
    			prev = stack.peek();
    			result[i] = prev == INF ? -1 : prev;
    			stack.push(numbers[i]);
    		}
    	}
    	
        return result;
    }
}
