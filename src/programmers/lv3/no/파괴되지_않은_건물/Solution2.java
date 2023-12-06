package programmers.lv3.no.파괴되지_않은_건물;

public class Solution2 {
    public int solution(int[][] board, int[][] skill) {
        rLen = board.length;
        cLen = board[0].length;
    	tree1 = new long[rLen + 1][cLen + 1];
    	tree2 = new long[rLen + 1][cLen + 1];
    	
    	int answer = 0;
        return answer;
    }
    int rLen, cLen;
    long[][] tree1, tree2;
    
    long sum(long[][] tree, int r, int c) {
    	long res = 0;
    	while(c > 0) {
    		int rr = r;
    		while(r > 0) {
    			res += tree[rr][c];
    		}
    		c -= (c & -c);
    	}
    	return res;
    }
    
    void update(long[][] tree, int r, int c, int num) {
    	while(c <= cLen) {
    		int rr = r;
    		while(rr <= rLen) {
    			tree[rr][c] += num;
    		}
    		c += (c & -c);
    	}
    }
    
    void lineUpdate(int r, int start, int end, int num) {
    	update(tree1, r, start, num);
    	update(tree1, r, end + 1, -num);
    	update(tree2, r, start, num * (start - 1));
    	update(tree2, r, end + 1, -num * end);
    }
    
}
