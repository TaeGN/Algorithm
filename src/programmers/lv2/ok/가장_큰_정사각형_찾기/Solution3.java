package programmers.lv2.ok.가장_큰_정사각형_찾기;

import java.util.Arrays;

public class Solution3 {
    public int solution(int[][] board)
    {	
    	int rLen = board.length, cLen = board[0].length;
    	int[][] D = new int[rLen + 1][cLen + 1];
    	for (int i = 1; i <= rLen; i++) {
			for (int j = 1; j <= cLen; j++) {
				D[i][j] = D[i - 1][j] + D[i][j - 1] - D[i - 1][j - 1] + board[i - 1][j - 1];
			}
		}
    	int len = Math.min(rLen, cLen);
    	for (; len >= 1; len--) {
    		int answer = len * len;
			for (int i = rLen; i >= len; i--) {
				for (int j = cLen; j >= len; j--) {
					if(D[i][j] - D[i - len][j] - D[i][j - len] + D[i - len][j - len] == answer)
						return answer;
				}
			}
		}
    	System.out.println(Arrays.deepToString(D));
		return 0;
    }
}
