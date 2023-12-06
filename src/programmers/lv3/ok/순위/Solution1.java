package programmers.lv3.ok.순위;

import java.util.Arrays;

public class Solution1 {
	public static void main(String[] args) {
		System.out.println(solution(5, new int[][] {{4,3},{4,2},{3,2},{1,2},{2,5}}));
	}
    public static int solution(int n, int[][] results) {
        boolean[][] map = new boolean[n + 1][n + 1];
        
        for(int[] result : results) {
        	map[result[0]][result[1]] = true;
        }
        
        for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				if(i == k || !map[i][k]) continue;
				for (int j = 1; j <= n; j++) {
					map[i][j] = map[i][j] || map[k][j];
				}
			}
		}
        
        int result = 0;
        for (int i = 1; i <= n; i++) {
        	int cnt = 0;
			for (int j = 1; j <= n; j++) {
				if(map[i][j] || map[j][i]) cnt++;
			}
			if(cnt == n - 1) result++;
		}
        
        return result;
    }
}
