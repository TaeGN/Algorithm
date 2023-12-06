package programmers.lv2.no.디펜스_게임;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2 {
	public static void main(String[] args) {
		int n = 7;
		int k = 3;
		int[] enemy = {4, 2, 4, 5, 3, 3, 1};
		int answer = solution(n, k, enemy);
		System.out.println(answer);
	}
    public static int solution(int n, int k, int[] enemy) {
    	int sum = 0, i;
    	for(i = 0; i < enemy.length; i++) {
    		if(n < (sum += enemy[i])) break;
    	}
    	for(int l = 0; l < k; l++) {
    		sum += enemy[++i];
    	}
    	i += k;
    	if(i > enemy.length) return enemy.length;
    	PriorityQueue<Integer> queue = new PriorityQueue<>();
    	int[] e = Arrays.copyOf(enemy, i + 1);
    	Arrays.sort(e);
    	for(int j = i; j > i - k; j--) {
    		queue.offer(e[j]);
    		sum -= e[j];
    	}
    	System.out.println(queue);
    	i++;
    	for(int len = enemy.length; i < len; i++) {
    		if(queue.peek() < enemy[i]) {
    			queue.poll();
    			queue.offer(enemy[i]);
    			sum -= (enemy[i] - queue.peek());
    		} else {
    			if((sum += enemy[i]) < 0) break;
    		}
    	}
    	return i - 1;
    }
	
}
