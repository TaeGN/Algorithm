package programmers.lv2.ok.혼자서_하는_틱택토;

public class Solution1 {
	public static void main(String[] args) {
		String[] board = {"O.X", ".O.", "..X"};
		System.out.println(solution(board));
	}
	
    public static int solution(String[] board) {
    	boolean winO = false, winX = false;
    	int cntO = 0, cntX = 0, j = 0;
    	StringBuilder sb1 = new StringBuilder();
    	StringBuilder sb2 = new StringBuilder();
    	StringBuilder sb3 = new StringBuilder();
    	for(String s : board) {
    		sb2.append(s.charAt(j));
    		sb3.append(s.charAt(2 - j));
    		if(s.equals("OOO")) winO = true;
    		if(s.equals("XXX")) winX = true;
    		for(int i = 0; i < 3; i++) {
    			if(s.charAt(i) == 'O') cntO++;
    			else if(s.charAt(i) == 'X') cntX++;
    			sb1.append(board[i].charAt(j));
    		}
    		if(sb1.toString().equals("OOO")) winO = true;
    		else if(sb1.toString().equals("XXX")) winX = true;
    		System.out.println(sb1);
    		sb1.setLength(0);
    		j++;
    	}
    	System.out.println(sb2);
    	System.out.println(sb3);
    	if(sb2.toString().equals("OOO") || sb3.toString().equals("OOO")) winO = true;
    	if(sb2.toString().equals("XXX") || sb3.toString().equals("XXX")) winX = true;
    	
    	if(winO) {
    		if(winX || cntO == cntX) return 0;
    	} else {
    		if(winX && cntO - cntX == 1) return 0;
    	}
    	return cntO == cntX || cntO - cntX == 1 ? 1 : 0;
    }
}
