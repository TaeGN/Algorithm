package programmers.lv3.no.연속_펄스_부분_수열의_합;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution2 {
	public static void main(String[] args) {
		
	}
	
    public long solution(int[] sequence) {
    	int len = sequence.length;
    	PriorityQueue<Sum> max = new PriorityQueue<>(len, (o1, o2) -> Long.compare(o2.value, o1.value));
    	PriorityQueue<Sum> min = new PriorityQueue<>(len, (o1, o2) -> Long.compare(o1.value, o2.value));
    	int idx = 0;
    	long sum = 0;
    	max.offer(new Sum(0, 0));
    	min.offer(new Sum(0, 0));
    	for(int num : sequence) {
    		if(++idx % 2 == 1) {
    			sum += num;
    		} else {
    			sum -= num;
    		}
    		max.offer(new Sum(idx, sum));
    		min.offer(new Sum(idx, sum));
    	}
    	Sum a = max.poll();
    	Sum b = min.poll();
    	if(a.idx > b.idx) {
    		if(b.idx % 2 == 0) return a.value - b.value;
    		
    	} else {
    		if(a.idx % 2 == 1) return b.value - a.value;
    	}
        return null;
    }
    
    class Sum{
    	int idx;
    	long value;
    	
		public Sum(int idx, long value) {
			super();
			this.idx = idx;
			this.value = value;
		}
    }
}
