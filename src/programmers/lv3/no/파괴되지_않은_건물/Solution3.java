package programmers.lv3.no.파괴되지_않은_건물;

public class Solution3 {
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
    	while(r > 0) {
    		int cc = c;
    		long[] cur = tree[r];
    		while(cc > 0) {
    			res += cur[cc];
    			cc -= (cc & -cc);
    		}
    		r -= (r & -r);
    	}
    	return res;
    }
    
    long sum(int r, int c) {
    	return sectionSum(r, r, c, c);
    }
    
    long lineSum(int r, int cs, int ce) {
    	return (sum(tree1, r, ce) * ce - sum(tree2, r, ce))
    			- (sum(tree1, r, cs - 1) * (cs - 1) - sum(tree2, r, cs - 1));
    }
    
    long sectionSum(int rs, int re, int cs, int ce) {
    	return lineSum(re, cs, ce) - lineSum(rs - 1, cs, ce);
    }
    
    void update(long[][] tree, int r, int c, int num) {
    	while(r <= rLen) {
    		int cc = c;
    		long[] cur = tree[r];
    		while(cc <= cLen) {
    			cur[cc] += num;
    			cc += (cc & -cc);
    		}
    		r += (r & -r);
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
