package programmers.lv2.ok.빛의_경로_사이클;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution1 {
	public static void main(String[] args) {
		String[] grid = {"SL","LR"};
		System.out.println(Arrays.toString(Solution(grid)));
	}

	private static int[] Solution(String[] grid) {
		changeDirection.put('S', 0);
		changeDirection.put('L', -1);
		changeDirection.put('R', 1);
		o1 = o2 = dd = 0;
		len = grid.length;
		light(grid, o1, o2, dd);
		for(int[] a : list) {
			System.out.println(Arrays.toString(a));
		}
		return new int[] {cnt};
	}
	
	static Map<Character, Integer> changeDirection = new HashMap<>();
	static List<int[]> list = new ArrayList<>();
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int o1, o2, cnt, len, dd;
	private static void light(String[] grid, int x, int y, int d) {
		if(x == o1 && y == o2 && d == dd && cnt != 0) {
			return;
		}
		
		d = (d + changeDirection.get(grid[y].charAt(x))) % 4;
		cnt++;
		list.add(new int[] {x,y});
		light(grid, (x + dx[d]) % len, (y + dy[d]) % len, d);
		
	}
	
}
