package programmers.lv3.ok.아이템_줍기;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution3_bfs_fail {
	public static void main(String[] args) {
		int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
		int characterX = 1;
		int characterY = 3; 
		int itemX = 7;
		int itemY = 8;
		System.out.println(Solution(rectangle, characterX, characterY, itemX, itemY));
	}
	
    public static int Solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        boolean[][] map = new boolean[52][52];	// 50x50 경계 주변을 false로 채운다.
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0 ,-1};
        int x1, x2, y1, y2, size, result = 0;
    	
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
    	for(boolean[] a : map) {
    		for(boolean b : a) {
    			if(b) System.out.print(1 + " ");
    			else System.out.print(0 + " ");
    		}
    		System.out.println();
    	}
    	// bfs
    	Queue<Integer> queue = new ArrayDeque<>();
    	queue.offer(characterX);
    	queue.offer(characterY);
    	map[characterY][characterX] = false;
    	while(!queue.isEmpty()) {
    		result++;
    		size = queue.size() / 2;
    		for(int i = 0; i < size; i++) {
    			x1 = queue.poll();
    			y1 = queue.poll();
    			for(int j = 0; j < 4; j++) {
    				x2 = x1 + dx[j];
    				y2 = y1 + dy[j];
    				if(x2 == itemX && y2 == itemY) return result;
    				if(!map[y2][x2]) continue;
    				queue.offer(x2);
    				queue.offer(y2);
    				map[y2][x2] = false;
    			}
    		}
    	}
    	
    	return -1;
    }
}
