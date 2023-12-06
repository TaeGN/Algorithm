package programmers.lv2.ok.하노이의_탑;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
	public static void main(String[] args) {
		int n = 2;
		Solution(n);
	}
	
	private static int[][] Solution(int n) {
		hanoy(n, 1, 2, 3);
		int[][] result = new int[list.size()][2]; 
		list.toArray(result);
		
		return result;
	}
	static List<int[]> list = new ArrayList<>();
	private static void hanoy(int n, int start, int sub, int end) {
		if(n == 0) return;
		
		hanoy(n - 1, start, end, sub);
		list.add(new int[] {start, end});
		hanoy(n - 1, sub, start, end);
	}
}
