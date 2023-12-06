package programmers.lv2.ok.배달;

import java.util.Arrays;

public class Solution2 {
	public static void main(String[] args) {
		
	}
    public int solution(int N, int[][] road, int K) {
    	final int INF = Integer.MAX_VALUE >> 2;
    	int[][] map = new int[N + 1][N + 1];
    	for(int[] m : map) {
    		Arrays.fill(m, INF);
    	}
    	
    	int a, b, c;
    	for(int[] r : road) {
    		a = r[0];
    		b = r[1];
    		c = r[2];
    		if(map[a][b] > c) map[a][b] = map[b][a] = c;
    	}
    	
    	for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if(k == i || map[i][k] == INF) continue;
				for (int j = 1; j <= N; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
    	
    	int result = 1;
    	for (int i = 2; i <= N; i++) {
			if(map[1][i] <= K) result++;
		}
        return result;
    }
}
