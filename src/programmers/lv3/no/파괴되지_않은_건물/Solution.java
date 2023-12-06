package programmers.lv3.no.파괴되지_않은_건물;

public class Solution {
	int N, M;
	long[][] tree1, tree2;
    public int solution(int[][] board, int[][] skill) {
    	N = board.length;
    	M = board[0].length;
    	tree1 = new long[N + 1][M + 1];
    	tree2 = new long[N + 1][M + 1];
    	
    	
    	
        return 1;
    }
    
    void updateRange(int sr, int sc, int er, int ec, int diff) {
    	update(tree1, sr, sc, diff);
    	update(tree1, er + 1, ec + 1, -diff);
    	update(tree2, sr, sc, diff * (sc - 1));
    	update(tree2, er + 1, ec + 1, diff * -ec);
    }
    
    void update(long[][] tree, int r, int c, int diff) {
    	while(r < N) {
    		int cc = c;
    		while(cc < M) {
    			tree[r][cc] += diff;
    			cc += (cc & -cc);
    		}
    	}
    }
    
    long sum(long[][] tree, int r, int c) {
    	return getPrifixSum(tree, r, c)
    }
    
    long getPrifixSum(long[][] tree, int r, int c) {
    	long res = 0;
    	while(r > 0) {
    		int cc = c;
    		while(cc > 0) {
    			res += tree[r][cc];
    			cc -= (cc & -cc);
    		}
    	}
    	return res;
    }
}
