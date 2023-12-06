package programmers.lv2.ok.프렌즈4블록;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution_sum {
	
	int[] dr = {0,0,1,1};
	int[] dc = {0,1,0,1};
	int[] rdr = {0,0,-1,-1};
	int[] rdc = {0,-1,0,-1};
	final int INF = Integer.MAX_VALUE >> 2;
	int M, N;
	
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        Map<Character, Integer> filter = new HashMap<>();
        Set<Integer> filterIdxSet = new HashSet<>();
        int filterIdx = 1;
        filterIdxSet.add(filterIdx * 4);
        M = m;
        N = n;
        
        int[][] map = new int[m][n];
        int[][] sum = new int[m + 1][n + 1];	// 1 ~ m - 1, 1 ~ n - 1
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		char c = board[i].charAt(j);
        		
        		// filter에 문자와 그에 대응하는 idx 연결
        		if(!filter.containsKey(c)) {
        			filter.put(c, filterIdx);
        			filterIdx *= 4;
        			filterIdxSet.add(filterIdx * 4);
        		}
        		
        		map[i][j] = filter.get(c);
        		
        		// sum 채우기
        		for (int k = 0; k < 4; k++) {
					sum[i + dr[k]][j + dc[k]] += map[i][j];
				}
			}
		}
        
        boolean[][] visited = new boolean[m][n];
        for (int i = 1; i < m; i++) {
        	for (int j = 1; j < n; j++) {
        		if(filterIdxSet.contains(sum[i][j])) {
        			for (int k = 0; k < 4; k++) {
        				rr = i + rdr[k];
        				cc = j + rdc[k];
        				visited[]
        			}
        		}
        	}
        }
        
        return answer;
    }
}
