package programmers.lv3.ok.아이템_줍기;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution4_dfs {
	public static void main(String[] args) {
		int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
		int characterX = 1;
		int characterY = 3; 
		int itemX = 7;
		int itemY = 8;
		System.out.println(Solution(rectangle, characterX, characterY, itemX, itemY));
	}
	
    public static int Solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[52][52];	// 50x50 경계 주변을 false로 채운다.
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0 ,-1};
        int x1, x2, y1, y2, size, result = 0;
    	
    	// 테두리 맵에 표기
        int i = 1;
    	for(int[] arr : rectangle) {
    		x1 = arr[0]; y1 = arr[1]; x2 = arr[2]; y2 = arr[3];
    		for(int y = y1; y <= y2; y++) {
    			map[y][x1] = i;
    			map[y][x2] = i;
    		}
    		
    		for(int x = x1; x <= x2; x++) {
    			map[y1][x] = i;
    			map[y2][x] = i;
    		}
    		i++;
    	}
    	
    	// 테두리 내부 제거
    	for(int[] arr : rectangle) {
    		x1 = arr[0]; y1 = arr[1]; x2 = arr[2]; y2 = arr[3];
    		for(int y = y1 + 1; y < y2; y++) {
    			Arrays.fill(map[y], x1 + 1, x2, 0);
    		}
    	}
    	
    	
    	for(int[] a : map) {
    		for(int b : a) {
    			System.out.print(b + " ");
    		}
    		System.out.println();
    	}
    	
//    	for(int i = 0; i < 4; i++) {
//    		dfs(characterX, characterY, 숫자, 방향);
//    	}
//    	
    	return -1;
    }
}
