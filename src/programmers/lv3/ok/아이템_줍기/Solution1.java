package programmers.lv3.ok.아이템_줍기;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution1 {
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
		int len = rectangle.length;
		if(len > 1) {
			// 포함관계 판단하기 위해 직사각형의 길이로 내림차순 정렬
			Arrays.sort(rectangle, (o1, o2) -> {
				int a = (o2[2] - o2[0]) - (o1[2] - o1[0]);
				if(a != 0) return a;
				else return (o2[3] - o2[1]) - (o1[3] - o1[1]);
			});
			
			boolean[] isSelected = new boolean[len];
			Arrays.fill(isSelected, true);
			for(int i = 0; i < len - 1; i++) {
				if(!isSelected[i]) continue;
				int x1 = rectangle[i][0];
				int y1 = rectangle[i][1];
				int x2 = rectangle[i][2];
				int y2 = rectangle[i][3];
				for(int j = i + 1; j < len; j++) {
					int xx1 = rectangle[j][0];
					int yy1 = rectangle[j][1];
					int xx2 = rectangle[j][2];
					int yy2 = rectangle[j][3];
					if(x1 < xx1 && y1 < y1 && x2 > xx2 && y2 > yy2) {
						isSelected[j] = false;
					}
				}
			}
			
			int a = 0;
			for(int[] arr : rectangle) {
				if(isSelected[a++]) {
					markOnTheMap(arr);
				}
			}
			
		} else {	//rectancle의 길이 1일 때
			markOnTheMap(rectangle[0]);
		}
		
		for(boolean[] a : map) {
			for(boolean b : a) {
				System.out.print(b + "\t");
			}
			System.out.println();
		}
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(characterX);
		queue.offer(characterY);
		int x, y, xx, yy, size, cnt = 0;
		while(!queue.isEmpty()) {
			cnt++;
			size = queue.size() / 2;
			for(int i = 0; i < size; i++) {
				x = queue.poll();
				y = queue.poll();
				for(int j = 0; j < 4; j++) {
					xx = x + dx[j];
					yy = y + dy[j];
					if(xx == itemX && yy == itemY) return cnt;
					if(xx >= 0 && yy >= 0 && xx <= 50 && yy <= 50 && map[xx][yy]) {
						map[xx][yy] = false;
						queue.offer(xx);
						queue.offer(yy);
					}
				}
			}
		}
		
		return -1;
	}
	
	// 주어진 rectangle의 테두리를 map에 표시
	private static void markOnTheMap(int[] arr) {
		int x1 = arr[0];
		int y1 = arr[1];
		int x2 = arr[2];
		int y2 = arr[3];
		
		int i;
		for(i = x1; i <= x2; i++) {
			if(map[y1][i]) {
				while(++i <= x2 && map[y1][i]);
			} else {
				map[y1][i] = true;
			}
		}
		
		for(i = x1; i <= x2; i++) {
			if(map[y2][i]) {
				while(++i <= x2 && map[y2][++i]);
			} else {
				map[y2][i] = true;
			}
		}
		
		for(i = y1 + 1; i <= y2; i++) {
			if(map[i][x1]) {
				while(++i <= y2 && map[++i][x1]);
			} else {
				map[i][x1] = true;
			}
		}
		
		for(i = y1 + 1; i <= y2; i++) {
			if(map[i][x2]) {
				while(++i <= y2 && map[++i][x2]);
			} else {
				map[i][x2] = true;
			}
		}
	}
}
