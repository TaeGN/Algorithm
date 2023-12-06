package programmers.lv3.no.파괴되지_않은_건물;

import java.util.Arrays;

public class Solution_test {
	public static void main(String[] args) {
		System.out.println(solution(new int[][]{
			{1,2,3},
			{4,5,6},
			{7,8,9}
		},new int[][]{
				{1,1,1,2,2,4},
				{1,0,0,1,1,2},
				{2,2,0,2,0,100}
		}));
	}

	public static int solution(int[][] board, int[][] skill) {
		rLen = board.length;
		cLen = board[0].length;
		tree1 = new long[rLen + 1][cLen + 1];
		tree2 = new long[rLen + 1][cLen + 1];
		for (int r = 1; r <= rLen; r++) {
			for (int c = 1; c <= cLen; c++) {
				update(r, c, board[r - 1][c - 1]);
			}
		}
		print();
		for (int i = 0, end = skill.length; i < end; i++) {
			System.out.println(Arrays.toString(skill[i]));
			sectionUpdate(skill[i]);
			print();
		}
		print();
		int answer = 0;
		for (int r = 1; r <= rLen; r++) {
			for (int c = 1; c <= cLen; c++) {
				if (sum(r, c) > 0)
					answer++;
			}
		}
		print();
		return answer;
	}

	static int rLen, cLen;
	static long[][] tree1, tree2;
	
	static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= rLen; i++) {
			for (int j = 1; j <= cLen; j++) {
				sb.append(sum(i, j)).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	static long sum(long[][] tree, int r, int c) {
		long res = 0;
		while (r > 0) {
			int cc = c;
			long[] cur = tree[r];
			while (cc > 0) {
				res += cur[cc];
				cc -= (cc & -cc);
			}
			r -= (r & -r);
		}
		return res;
	}

	static long sum(int r, int c) {
		return sectionSum(r, r, c, c);
	}

	static long lineSum(int r, int cs, int ce) {
		return (sum(tree1, r, ce) * ce - sum(tree2, r, ce))
				- (sum(tree1, r, cs - 1) * (cs - 1) - sum(tree2, r, cs - 1));
	}

	static long sectionSum(int rs, int re, int cs, int ce) {
		return lineSum(re, cs, ce) - lineSum(rs - 1, cs, ce);
	}

	static void update(long[][] tree, int r, int c, int num) {
		while (r <= rLen) {
			int cc = c;
			long[] cur = tree[r];
			while (cc <= cLen) {
				cur[cc] += num;
				cc += (cc & -cc);
			}
			r += (r & -r);
		}
	}

	static void update(int r, int c, int num) {
		lineUpdate(r, c, c, num);
	}

	static void lineUpdate(int r, int cs, int ce, int num) {
		update(tree1, r, cs, num);
		update(tree1, r, ce + 1, -num);
		update(tree2, r, cs, num * (cs - 1));
		update(tree2, r, ce + 1, -num * ce);
	}

	static void sectionUpdate(int rs, int re, int cs, int ce, int num) {
		for (int r = rs; r <= re; r++) {
			lineUpdate(r, cs, ce, num);
		}
	}

	static void sectionUpdate(int[] input) {
		int rs = input[1] + 1;
		int cs = input[2] + 1;
		int re = input[3] + 1;
		int ce = input[4] + 1;
		int num = input[0] == 1 ? -input[5] : input[5];
		sectionUpdate(rs, re, cs, ce, num);
	}
}
