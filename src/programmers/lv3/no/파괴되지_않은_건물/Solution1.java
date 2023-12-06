package programmers.lv3.no.파괴되지_않은_건물;

public class Solution1 {
    public int solution(int[][] board, int[][] skill) {
        rLen = board.length;
        cLen = board[0].length;
    	tree1 = new long[rLen + 1][cLen + 1];
    	tree2 = new long[rLen + 1][cLen + 1];
    	for (int i = 0, end = skill.length; i < end; i++) {
    		sectionUpdate(skill[i]);
		}
    	int answer = 0;
    	for (int r = 1; r <= rLen; r++) {
			for (int c = 1; c <= cLen; c++) {
				if(board[r - 1][c - 1] + sum(r, c) > 0) answer++;
			}
		}
        return answer;
    }
    int rLen, cLen;
    long[][] tree1, tree2;
    
    long sum(long[][] tree, int r, int c) {
    	long res = 0;
    	long[] cur = tree[r];
    	while(c > 0) {
    		res += cur[c];
    		c -= (c & -c);
    	}
    	return res;
    }
    
    long sum(int r, int c) {
    	return lineSum(r, c, c);
    }
    
    long lineSum(int r, int cs, int ce) {
    	return (sum(tree1, r, ce) * ce - sum(tree2, r, ce))
    			- (sum(tree1, r, cs - 1) * (cs - 1) - sum(tree2, r, cs - 1));
    }
    
    void update(long[][] tree, int r, int c, int num) {
    	long[] cur = tree[r];
    	while(c <= cLen) {
    		cur[c] += num;
    		c += (c & -c);
    	}
    }
    
    void lineUpdate(int r, int cs, int ce, int num) {
    	update(tree1, r, cs, num);
    	update(tree1, r, ce + 1, -num);
    	update(tree2, r, cs, num * (cs - 1));
    	update(tree2, r, ce + 1, -num * ce);
    }
    
    void sectionUpdate(int rs, int re, int cs, int ce, int num) {
    	for (int r = rs; r <= re; r++) {
			lineUpdate(r, cs, ce, num);
		}
    }
    
    void sectionUpdate(int[] input) {
		int rs = input[1] + 1;
		int cs = input[2] + 1;
		int re = input[3] + 1;
		int ce = input[4] + 1;
    	int num = input[0] == 1 ? -input[5] : input[5];
    	sectionUpdate(rs, re, cs, ce, num);
    }
}
