package programmers.lv3.ok.합승_택시_요금;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution2 {
	Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
	boolean[] visited;
	PriorityQueue<Point> pq;
	final int INF = Integer.MAX_VALUE;
    public int solution(int n, int s, int a, int b, int[][] fares) {
    	int c, d, f;
    	for(int[] fare : fares) {
    		c = fare[0];
    		d = fare[1];
    		f = fare[2];
    		map.compute(c, (k, v) -> v == null ? new HashMap<>() : v).put(d, f);
    		map.compute(d, (k, v) -> v == null ? new HashMap<>() : v).put(c, f);
    	}
    	
    	// 시작지점 s
    	pq = new PriorityQueue<>(map.get(s).size());
    	visited = new boolean[n + 1];
    	int[] sMD = getMinDistance(s, n);
    	int[] aMD = getMinDistance(a, n);
    	int[] bMD = getMinDistance(b, n);
    	
    	for(int i = 1; i <= n; i++) {
    		if(!map.containsKey(i)) continue;
    		pq.offer(new Point(i, sMD[i] + aMD[i] + bMD[i]));
    	}
    	
        return pq.peek().minDistance;
    }
    
    private int[] getMinDistance(int s, int n) {
    	int[] minDistance = new int[n + 1];
    	Arrays.fill(minDistance, INF);
    	pq.offer(new Point(s, 0));
    	minDistance[s] = 0;
    	int cnt = 0, mapSize = map.size(), curIdx, curD, nextIdx, nextD;
    	Point cur;
    	while(!pq.isEmpty()) {
    		cur = pq.poll();
    		curIdx = cur.idx;
    		if(visited[curIdx]) continue;
    		visited[curIdx] = true;
    		curD = cur.minDistance;
    		if(++cnt == mapSize) break;
    		for(Map.Entry<Integer, Integer> entry : map.get(curIdx).entrySet()) {
    			nextIdx = entry.getKey();
    			nextD = curD + entry.getValue();
    			if(visited[nextIdx] || minDistance[nextIdx] <= nextD) continue;
    			minDistance[nextIdx] = nextD;
    			pq.offer(new Point(nextIdx, nextD));
    		}
    	}
    	
    	pq.clear();
    	Arrays.fill(visited, false);
    	return minDistance;
	}

	class Point implements Comparable<Point> {
    	int idx, minDistance;

		public Point(int idx, int minDistance) {
			super();
			this.idx = idx;
			this.minDistance = minDistance;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.minDistance, o.minDistance);
		}
    }
}
