package programmers.lv3.ok.경주로건설;

public class Main {
	static Solution solution = new Solution();
	
	public static void main(String[] args) {
//		int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
		int[][] board = {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}};
		
		System.out.println(solution.solution(board));
	}
}
