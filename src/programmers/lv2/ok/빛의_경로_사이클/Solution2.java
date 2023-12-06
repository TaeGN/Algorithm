package programmers.lv2.ok.빛의_경로_사이클;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {
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
    				if(visited[sr][sc][sd]) continue;
    				cycle(sr, sc, sd, 0);
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
    
	private static void cycle(int r, int c, int d, int cnt) {
		if(r == sr && c == sc && d == sd && cnt != 0) {
			cycleLen.add(cnt);
			return;
		}
		
		if(visited[r][c][d]) return;
		visited[r][c][d] = true;
		
		int rr = r + dr[d];
		int cc = c + dc[d];
		rr = rr >= R ? 0 : rr < 0 ? R - 1 : rr;
		cc = cc >= C ? 0 : cc < 0 ? C - 1 : cc;
		switch(map[rr][cc]) {
		case 'L':
			d = d == 0 ? d + 3 : d - 1;
			break;
		case 'R':
			d = d == 3 ? d - 3 : d + 1;
			break;
		}
		cycle(rr, cc, d, cnt + 1);
	}
}
