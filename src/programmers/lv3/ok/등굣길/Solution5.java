package programmers.lv3.ok.등굣길;

public class Solution5 {
    public int solution(int m, int n, int[][] puddles) {
    	int[][] map = new int[n + 1][m + 1];
    	for(int[] puddle : puddles) {
    		map[puddle[1]][puddle[0]] = -1;
    	}
    	map[0][1] = 1;
    	for(int r = 1; r <= n; r++) {
    		for(int c = 1; c <= m; c++) {
    			if(map[r][c] == -1) continue;
    			if(map[r - 1][c] != -1) map[r][c] += map[r - 1][c];
    			if(map[r][c - 1] != -1) map[r][c] += map[r][c - 1];
    			map[r][c] %= 1_000_000_007;
    		}
    	}
        return map[n][m];
    }
}
