package programmers.lv2.ok.가장_큰_정사각형_찾기??옣_?겙_?젙?궗媛곹삎_李얘린;

public class Solution1 {
	
    public int solution(int[][] board){
    	int rLen = board.length;
    	int cLen = board[0].length;
    	int minLen = Math.min(rLen, cLen);
    	for(int i = 1; i < minLen; i++) {
    		if(!searchArr(board, rLen - i, cLen - i)) return i * i;
    	}
    	
        return minLen * minLen;
    }

	private boolean searchArr(int[][] board, int rLen, int cLen) {
		boolean result = false;
		boolean tf = false;
		for(int r = 0; r < rLen; r++) {
			for(int c = 0; c < cLen; c++) {
				tf = true;
				a:for(int i = r; i <= r + 1; i++) {
					for(int j = c; j <= c + 1; j++) {
						if(board[i][j] == 0) {
							tf = false;
							break a;
						}
					}
				}
				if(tf) {
					board[r][c] = 1;
					result = true;
				}
				else board[r][c] = 0;
			}
		}
		
		return result;
	}
}
