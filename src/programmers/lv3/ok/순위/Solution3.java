package programmers.lv3.ok.순위;

public class Solution3 {
	public static void main(String[] args) {
		int n = 5;
		int[][] results = {{4,3},{4,2},{3,2},{1,2},{2,5}};
		System.out.println(Solution(n, results));
	}
	
    public static int Solution(int n, int[][] results) {
    	resultArr = new int[n + 1][n + 1];
    	visited = new boolean[n + 1]; N = n;
    	for(int[] arr : results) resultArr[arr[0]][arr[1]] = 1;
    	for(int i = 1; i <= n; i++) dfs(1, i);
    	return max;
    }
    
    static int max, N;
    static int[][] resultArr;
    static boolean[] visited;
    private static void dfs(int cnt, int idx) {
    	visited[idx] = true;
    	
    	for(int i = 1; i <= N; i++) {
    		if(!visited[i]) dfs(cnt + 1, i);
    	}
    	
    	max = Math.max(max, cnt);
		visited[idx] = false;
	}

	
}
