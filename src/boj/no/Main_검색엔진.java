package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main_검색엔진 {
	static final int MAX_N = 24 * 50;
	static int N, linkIdx;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> linkMap = new HashMap<>(N);
		linkIdx = -1;
		N = Integer.parseInt(br.readLine());
		long[] link = new long[MAX_N];
		
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			
			// linkMap에 없는 link 추가
			if(!linkMap.containsKey(input[0])) {
				linkMap.put(input[0], ++linkIdx);
			}
			
			int cur = linkMap.get(input[0]);
			
			for (int j = 2, len = Integer.parseInt(input[1]) + 2; j < len; j++) {
				// linkMap에 없는 link 추가
				if(!linkMap.containsKey(input[j])) {
					linkMap.put(input[j], ++linkIdx);
				}
				
				// link에 추가
				link[cur] |= (1 << linkMap.get(input[j]));
			}
		}
		
		boolean[] visited = new boolean[linkIdx + 1];
		for (int i = 0; i <= linkIdx; i++) {
			if(visited[i]) continue;
			setLink(i, visited, link);
		}
		
		// 주어진 웹사이트 점수 구하기
		int[] linkScore = new int[linkIdx + 1];
		Arrays.fill(linkScore, 1);
		Arrays.fill(visited, false);
		int idx = linkMap.get(br.readLine());
		for (int i = 0; i <= linkIdx; i++) {
			if(visited[i]) continue;
			setScore(i, visited, link, linkScore);
		}
		
		System.out.println(linkScore[idx]);
	}
	
	static long setLink(int idx, boolean[] visited, long[] link) {
		if(!visited[idx]) {
			visited[idx] = true;
			
			for (int next = 0; next <= linkIdx; next++) {
				if((link[idx] & (1 << next)) == 0) continue;
				link[idx] |= setLink(next, visited, link);
			}
		}
		
		return link[idx];
	}
	
	static int setScore(int idx, boolean[] visited, long[] link, int[] linkScore) {
		
		if(!visited[idx]) {
			visited[idx] = true;
			
			for (int next = 0; next <= linkIdx; next++) {
				if((link[idx] & (1 << next)) == 0) continue;
				if((link[next] & (1 << idx)) != 0) continue;
				linkScore[idx] += setScore(next, visited, link, linkScore);
			}
		}
		
		return linkScore[idx];
	}
}
