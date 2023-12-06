package programmers.lv2.ok.게임_맵_최단거리;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution1 {
	public static void main(String[] args) {
		int[][] maps = {
				{1,0,1,1,1},
				{1,0,1,0,1},
				{1,0,1,1,1},
				{1,1,1,0,1},
				{0,0,0,0,1}
		};
		System.out.println(Solution(maps));
	}
	
	private static int Solution(int[][] maps) {
		Queue<Integer> queue = new ArrayDeque<>();
		int[] dr = {0,1,0,-1};
		int[] dc = {1,0,-1,0};
		int r, c, rr, cc, i, j, size, cnt = 1;
		int rLen = maps.length;
		int cLen = maps[0].length;
		queue.offer(0);
		queue.offer(0);
		while(!queue.isEmpty()) {
			cnt++;
			for(i = 0, size = queue.size() / 2; i < size; i++) {
				r = queue.poll();
				c = queue.poll();
				for(j = 0; j < 4; j++) {
					rr = r + dr[j];
					cc = c + dc[j];
					if(rr == rLen - 1 && cc == cLen - 1) return cnt;
					if(rr >= 0 && cc >= 0 && rr < rLen && cc < cLen && maps[rr][cc] == 1) {
						maps[rr][cc] = 0;
						queue.offer(rr);
						queue.offer(cc);
					}
				}
			}
		}
		return -1;
	}
}
