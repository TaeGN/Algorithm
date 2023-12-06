package programmers.lv3.ok.숫자_게임;

import java.util.PriorityQueue;

public class Solution1 {
    public int solution(int[] A, int[] B) {
    	int len = A.length;
    	PriorityQueue<Integer> apq = new PriorityQueue<Integer>(len);
    	PriorityQueue<Integer> bpq = new PriorityQueue<Integer>(len);
    	for(int a : A) apq.add(a);
    	for(int b : B) bpq.add(b);
    	
    	int aCur, result = 0;
    	while(!bpq.isEmpty()) {
    		aCur = apq.poll();
    		while(aCur >= bpq.poll()) {
    			if(bpq.isEmpty()) return result;
    		}
    		result++;
    	}
        return result;
    }
}
