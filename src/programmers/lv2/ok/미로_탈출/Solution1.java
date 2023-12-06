package programmers.lv2.ok.미로_탈출;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution1 {
	public static void main(String[] args) {
		String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
		System.out.println(solution(maps));
	}
	
    public static int solution(String[] maps) {
    	int r = 1, rLen = maps.length, cLen = maps[0].length();
    	Point S = null, E = null, L = null;
    	boolean[][] map = new boolean[rLen + 2][cLen + 2];
    	initBoundary(map);
    	for(String s : maps) {
    		for(int c = 1; c <= cLen; c++) {
    			switch(s.charAt(c - 1)) {
    			case 'X':
    				map[r][c] = true;
    				break;
    			case 'S':
    				S = new Point(r, c);
    				break;
    			case 'E':
    				E = new Point(r, c);
    				break;
    			case 'L':
    				L = new Point(r, c);
    				break;
    			}
    		}
    		r++;
    	}
    	
        return minTimeMove(L, E, map, minTimeMove(S, L, copyMap(map), 0));
    }
    
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static int minTimeMove(Point s, Point e, boolean[][] map, int time) {
    	if(time == -1) return -1;
    	
    	Queue<Point> queue = new ArrayDeque<>();
    	queue.offer(s);
    	map[s.r][s.c] = true;
    	
    	Point cur;
    	int r, c;
    	while(!queue.isEmpty()) {
    		time++;
    		for(int i = 0, size = queue.size(); i < size; i++) {
    			cur = queue.poll();
    			for(int j = 0; j < 4; j++) {
    				r = cur.r + dr[j];
    				c = cur.c + dc[j];
    				if(!map[r][c]) {
    					if(r == e.r && c == e.c) return time;
    					queue.offer(new Point(r, c));
    					map[r][c] = true;
    				}
    			}
    		}
    	}
    	
		return -1;
	}
    
    static void initBoundary(boolean[][] map) {
    	int rLen = map.length;
    	int cLen = map[0].length;
    	Arrays.fill(map[0], true);
    	Arrays.fill(map[rLen - 1], true);
    	for(int i = 1; i <= rLen - 2; i++) {
    		map[i][0] = true;
    		map[i][cLen - 1] = true;
    	}
    }

	static boolean[][] copyMap(boolean[][] map) {
		boolean[][] newMap = new boolean[map.length][];
    	for(int r = 0, rLen = map.length; r < rLen; r++) {
    		newMap[r] = map[r].clone();
    	}
    	return newMap;
    }
    
    static class Point {
    	int r, c;
    	
    	public Point() {}
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
    }
}
