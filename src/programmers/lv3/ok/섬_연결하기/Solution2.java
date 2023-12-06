package programmers.lv3.ok.섬_연결하기;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution2 {
	public static void main(String[] args) {
		int n = 4;
		int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		System.out.println(solution(n, costs));
	}
	
    static int solution(int n, int[][] costs) {
    	ArrayList<Cost>[] costArr = new ArrayList[n];
    	for(int i = 0; i < n; i++) {
    		costArr[i] = new ArrayList<>();
    	}
    	PriorityQueue<Cost> queue = new PriorityQueue<>();
    	boolean[] visited = new boolean[n];
    	for(int[] cost : costs) {
    		costArr[cost[0]].add(new Cost(cost[1], cost[2]));
    		costArr[cost[1]].add(new Cost(cost[0], cost[2]));
    	}
    	int start = 0;
    	queue.addAll(costArr[start]);
    	visited[start] = true;
    	Cost cur;
    	int cnt = 1, result = 0;
    	while(cnt != n) {
    		cur = queue.poll();
    		if(visited[cur.to]) continue;
    		queue.addAll(costArr[cur.to]);
    		visited[cur.to] = true;
    		result += cur.cost;
    		cnt++;
    	}
        return result;
    }
    
    static class Cost implements Comparable<Cost> {
    	int to;
    	int cost;
    	
		public Cost(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Cost o) {
			if(this.cost != o.cost) return Integer.compare(this.cost, o.cost);
			return Integer.compare(this.to, o.to);
		}
    }
}
