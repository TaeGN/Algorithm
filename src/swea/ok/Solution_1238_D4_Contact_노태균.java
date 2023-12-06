package swea.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution_1238_D4_Contact_노태균 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Integer, Set<Integer>> contactMap = new HashMap<>();
		Set<Integer> visited = new HashSet<>();
		Queue<Integer> queue = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		String[] input;
		int T = 10;
		int N, M, from, to, cur, max = 0, curMax;
		for(int tc = 1; tc <= T; tc++) {
			input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			input = br.readLine().split(" ");
			for(int i = 0; i < N / 2; i++) {
				from = Integer.parseInt(input[i * 2]);
				to = Integer.parseInt(input[i * 2 + 1]);
				contactMap.compute(from, (k,v) -> v == null ? new HashSet<>() : v).add(to);
			}
			
			queue.offer(M);
			visited.add(M);
			
			while(!queue.isEmpty()) {
				curMax = 0;
				for(int i = 0, size = queue.size(); i < size; i++) {
					cur = queue.poll();
					if(!contactMap.containsKey(cur)) continue;
					for(int next : contactMap.get(cur)) {
						if(visited.add(next)) {
							queue.offer(next);
							curMax = Math.max(curMax, next);
						}
					}
				}
				if(curMax != 0) max = curMax;
			}
			
			visited.clear();
			contactMap.clear();
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		br.close();
		System.out.println(sb);
	}
}
