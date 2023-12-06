package programmers.lv3.ok.등굣길;

public class Solution4 {
	public static void main(String[] args) {
		int m = 4;
		int n = 3;
		int[][] puddles = {{2,2}};
		System.out.println(Solution(m,n,puddles));
	}

	private static int Solution(int m, int n, int[][] puddles) {
		minDistance = new int[n + 2][m + 2];
		dfs(1, 1, puddles, 0);
		return minDistance[];
	}
	static int[][] minDistance;
	static int M, N;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	private static void dfs(int x, int y, int[][] puddles, int distance) {
		if(x == M && y == N) {
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(xx > 0 && yy > 0 && xx <= M && yy <= N) {
				if(minDistance[yy][xx] <= distance + 1) {
					return;
				} else {
					minDistance[yy][xx] = distance + 1;
					dfs()
				}
			}
		}
		
	}
}
