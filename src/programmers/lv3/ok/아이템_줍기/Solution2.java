package programmers.lv3.ok.아이템_줍기;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution2 {
	public static void main(String[] args) {
		int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
		int characterX = 1;
		int characterY = 3; 
		int itemX = 7;
		int itemY = 8;
		System.out.println(Solution(rectangle, characterX, characterY, itemX, itemY));
	}

	static boolean[][] map = new boolean[51][51];
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	private static int Solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		for(int[] arr : rectangle) {
			markOnTheMap(arr);
		}
		boolean[] firstPos = new boolean[4];
		for(int i = 0; i < 4; i++) {
			firstPos[i] = map[characterY + dy[0]][characterX + dx[0]];
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(characterY);
		queue.offer(characterX);
		
		
		return 0;
	}
	
	// 주어진 rectangle을 map에 표시
	private static void markOnTheMap(int[] arr) {
		int x1 = arr[0];
		int y1 = arr[1];
		int x2 = arr[2];
		int y2 = arr[3];
		
		for(int i = y1; i <= y2; i++) {
			for(int j = x1; j <= x2; j++) {
				map[i][j] = map[i][j] = true;
			}
		}
	}
}
