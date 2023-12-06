package programmers.lv2.ok.빛의_경로_사이클;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution3 {
	public static void main(String[] args) {
		String[] grid = {"SL","LR"};
		System.out.println(Arrays.toString(Solution(grid)));
	
	}
	
	static List<Integer> cycleLen = new ArrayList<>();
	static boolean[][][] visited;
	static char[][] map;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int R, C, sr, sc, sd;
    public static int[] Solution(String[] grid) {
    	R = grid.length;
    	C = grid[0].length();
    	map = new char[R][];
    	int i = 0;
    	for(String s : grid) {
    		map[i++] = s.toCharArray();
    	}
    	visited = new boolean[R][C][4];
    	
    	for(sr = 0; sr < R; sr++) {
    		for(sc = 0; sc < C; sc++) {
    			for(sd = 0; sd < 4; sd++) {
    				if(!visited[sr][sc][sd]) cycle(sr, sc, sd);
    			}
    		}
    	}
    	
    	int[] result = new int[cycleLen.size()];
    	i = 0;
    	for(int len : cycleLen) {
    		result[i++] = len;
    	}
    	Arrays.sort(result);
        return result;
    }
    
	private static void cycle(int r, int c, int d) {
		int cnt = 0;
		do {
			if(visited[r][c][d]) return;
			visited[r][c][d] = true;
			
			r += dr[d];
			c += dc[d];
			r = r >= R ? 0 : r < 0 ? R - 1 : r;
			c = c >= C ? 0 : c < 0 ? C - 1 : c;
			switch(map[r][c]) {
			case 'L':
				d = d == 0 ? d + 3 : d - 1;
				break;
			case 'R':
				d = d == 3 ? d - 3 : d + 1;
				break;
			}
			cnt++;
		} while(r != sr || c != sc || d != sd);
		
		cycleLen.add(cnt);
		return;
	}
}
