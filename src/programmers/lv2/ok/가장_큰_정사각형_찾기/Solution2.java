package programmers.lv2.ok.°¡Àå_Å«_Á¤»ç°¢Çü_Ã£±â??ž¥_?°_? •?‚¬ê°í˜•_ì°¾ê¸°;

public class Solution2 {
	public static void main(String[] args) {
		int [][]board = {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};
		System.out.println(solution(board));
	}
    public static int solution(int [][]board)
    {   
        int rLen = board.length;
        int cLen = board[0].length;
        int minLen = Math.min(rLen, cLen);
        for(int i = minLen; i > 0; i--) {
            for(int r = 0; r <= rLen - i; r++) {
                a:for(int c = 0; c <= cLen - i; c++) {
                    for(int rr = r; rr < r + i; rr++) {
                        for(int cc = c; cc < c + i; cc++) {
                        	System.out.println(rr + " : " + cc + " : " + board[rr][cc]);
                            if(board[rr][cc] == 0) continue a;
                        }
                    }
                    return i * i;
                }
            }
        }
        return 1;
    }
}
