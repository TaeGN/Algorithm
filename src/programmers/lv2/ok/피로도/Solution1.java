package programmers.lv2.ok.피로도;

public class Solution1 {
	public static void main(String[] args) {
		int k = 80;
		int[][] dungeons = {
				{80,20},
				{50,40},
				{30,10}
		};
		int result = Solution(k, dungeons);
		System.out.println(result);
	}
	
	static int[][] dungeonss;
	static boolean[] visited;
	static int len, result, min = Integer.MAX_VALUE;
	private static int Solution(int k, int[][] dungeons) {
		len = dungeons.length;
		visited = new boolean[len];
		dungeonss = dungeons;
		
		for(int[] numArr : dungeons) {
			min = Math.min(min, numArr[0]);
		}
		
		
		return goDungeons(k);
	}
	
	private static int goDungeons(int k) {
		int cnt = 0;
		for(int i = 0; i < len; i++) {
			if(!visited[i] && k >= dungeonss[i][0]) {
				visited[i] = true;
				cnt = Math.max(1 + goDungeons(k - dungeonss[i][1]), cnt);
				visited[i] = false;
			}
		}
		
		return cnt;
	}
	private static void goDungeons2(int cnt, int k) {
		if(k < min || cnt == len) {
			result = Math.max(result, cnt);
			return;
		}
		for(int i = 0; i < len; i++) {
			if(!visited[i] && k >= dungeonss[i][0]) {
				visited[i] = true;
				goDungeons2(cnt + 1, k - dungeonss[i][1]);
				visited[i] = false;
			}
			
		}
	}
}
