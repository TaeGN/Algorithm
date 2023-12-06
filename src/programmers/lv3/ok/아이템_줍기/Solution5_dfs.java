package programmers.lv3.ok.아이템_줍기;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution5_dfs {
	public static void main(String[] args) {
		int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
		int characterX = 1;
		int characterY = 3; 
		int itemX = 7;
		int itemY = 8;
		System.out.println(Solution(rectangle, characterX, characterY, itemX, itemY));
	}
	
    public static int Solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int x1, x2, y1, y2, result = Integer.MAX_VALUE;

    	// 테두리 맵에 표기
    	for(int[] arr : rectangle) {
    		x1 = arr[0]; y1 = arr[1]; x2 = arr[2]; y2 = arr[3];
    		for(int y = y1; y <= y2; y++) {
    			map[y][x1] = true;
    			map[y][x2] = true;
    		}
    		
    		for(int x = x1; x <= x2; x++) {
    			map[y1][x] = true;
    			map[y2][x] = true;
    		}
    	}
    	
    	// 테두리 내부 제거
    	for(int[] arr : rectangle) {
    		x1 = arr[0]; y1 = arr[1]; x2 = arr[2]; y2 = arr[3];
    		for(int y = y1 + 1; y < y2; y++) {
    			Arrays.fill(map[y], x1 + 1, x2, false);
    		}
    	}
    	
    	// character 위치에서 두 방향으로 탐색, 결과값 중에서 min값 return
    	endX = itemX; endY = itemY;
    	map[characterY][characterX] = false;
    	for(int i = 0; i < 4; i++) {
    		x1 = characterX + dx[i];
    		y1 = characterY + dy[i];
    		if(!map[y1][x1]) continue;
	    	dfs(x1, y1, 1);
	    	result = Math.min(result, max);
    	}
    	
    	return result;
    }
    
    static int max, endX, endY;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0 ,-1};
    static boolean[][] map = new boolean[52][52];	// 50x50 경계 주변을 false로 채운다.
    private static void dfs(int x, int y, int cnt) {
    	if(x == endX && y == endY) {
    		max = Math.max(max, cnt);	// cnt값이 가장 큰 경로가 올바른 길
    		return;
    	}
    	
    	map[y][x] = false;
    	
    	for(int i = 0; i < 4; i++) {
    		int xx = x + dx[i];
    		int yy = y + dy[i];
    		if(map[yy][xx]) dfs(xx, yy, cnt + 1);
    	}
    	
    	map[y][x] = true;
	}
}
