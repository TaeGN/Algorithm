package programmers.lv3.ok.정수_삼각형;


public class Solution1 {
    public int solution(int[][] triangle) {
    	int rLen = triangle.length;
    	for(int r = 1; r < rLen; r++) {
    		triangle[r][0] = triangle[r - 1][0] + triangle[r][0];
    		triangle[r][r] = triangle[r - 1][r - 1] + triangle[r][r];
    		for(int c = 1; c < r; c++) {
    			triangle[r][c] = Math.max(triangle[r - 1][c - 1], triangle[r - 1][c]) + triangle[r][c];
    		}
    	}
    	
    	int max = 0;
    	for(int num : triangle[rLen - 1]) {
    		max = Math.max(max, num);
    	}
        return max;
    }
}
