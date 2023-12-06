package programmers.lv3.no.파괴되지_않은_건물;

public class Main {
	static Solution solution = new Solution();
	
	public static void main(String[] args) {
		int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
		int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
		System.out.println(solution.solution(board, skill));
	}
}
