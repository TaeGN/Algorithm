package programmers.lv3.ok.아이템_줍기;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution6_bfs_ok {
	public static void main(String[] args) {
		int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
		int characterX = 1;
		int characterY = 3; 
		int itemX = 7;
		int itemY = 8;
		System.out.println(Solution(rectangle, characterX, characterY, itemX, itemY));
	}
	
    public static int Solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 직사각형의 테두리들이 인접한 경우를 배제하기 위해 모든 좌표를 x2 해준다.
    	boolean[][] map = new boolean[102][102];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0 ,-1};
        int x1, x2, y1, y2, size, result = 0;
    	
    	// 테두리 맵에 표기
    	for(int[] arr : rectangle) {
    		x1 = arr[0] * 2; y1 = arr[1] * 2; x2 = arr[2] * 2; y2 = arr[3] * 2;
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
    		x1 = arr[0] * 2; y1 = arr[1] * 2; x2 = arr[2] * 2; y2 = arr[3] * 2;
    		for(int y = y1 + 1; y < y2; y++) {
    			Arrays.fill(map[y], x1 + 1, x2, false);
    		}
    	}
    	
    	// bfs
    	Queue<Integer> queue = new ArrayDeque<>();
    	queue.offer(characterX * 2);
    	queue.offer(characterY * 2);
    	map[characterY * 2][characterX * 2] = false;
    	while(!queue.isEmpty()) {
    		result++;
    		size = queue.size() / 2;
    		for(int i = 0; i < size; i++) {
    			x1 = queue.poll();
    			y1 = queue.poll();
    			for(int j = 0; j < 4; j++) {
    				x2 = x1 + dx[j];
    				y2 = y1 + dy[j];
    				if(x2 == itemX * 2 && y2 == itemY * 2) return result / 2;
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
