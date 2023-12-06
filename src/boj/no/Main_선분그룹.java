package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
6
1 0 3 0
1 1 3 1
3 1 2 3
2 3 1 1
0 2 2 4
2 0 0 3
---------------
1
6
 * @author TaeG
 *
 */

public class Main_선분그룹 {
	static int N, set[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		set = new int[N];
		makeSet(set);
		
		Line[] lineArr = new Line[N];
		Line cur;
		for (int i = 0; i < N; i++) {
			lineArr[i] = cur = new Line(br.readLine().split(" "));
			for (int j = 0; j < i; j++) {
				if(cur.isSameLine(lineArr[j])) union(i, j);
				System.out.println(i + "-" + j + " : " + cur.isSameLine(lineArr[j]));
			}
		}
		br.close();
		System.out.println(Arrays.toString(set));
		Map<Integer, Integer> map = new HashMap<>();
		for(int num : set) {
			map.compute(num, (k,v) -> v == null ? 1 : v + 1);
		}
		System.out.println(map.size());
		int max = 0;
		for(int num : map.values()) {
			max = Math.max(max, num);
		}
		System.out.println(max);
	}
	
	static class Line {
		int sr, sc, er, ec;

		public Line(String[] input) {
			super();
			
			this.sr = Integer.parseInt(input[0]);
			this.sc = Integer.parseInt(input[1]);
			this.er = Integer.parseInt(input[2]);
			this.ec = Integer.parseInt(input[3]);
		}

		public boolean isSameLine(Line o) {
			if(sr == o.sr && sc == o.sc) return true;
			if(sr == o.er && sc == o.ec) return true;
			if(er == o.sr && ec == o.sc) return true;
			if(er == o.er && ec == o.ec) return true;
			int trMax = Math.max(sr, er);
			int orMax = Math.max(o.sr, o.er);
			int trMin = Math.min(sr, er);
			int orMin = Math.min(o.sr, o.er);
			if(trMax >= orMax && trMin >= orMax) return false;
			if(trMax <= orMax && trMax <= orMin) return false;
			int tcMax = Math.max(sc, ec);
			int ocMax = Math.max(o.sc, o.ec);
			int tcMin = Math.min(sc, ec);
			int ocMin = Math.min(o.sc, o.ec);
			if(tcMax >= ocMax && tcMin >= ocMax) return false;
			if(tcMax <= ocMax && tcMax <= ocMin) return false;
			if(((double) (ec - sc)) / (er - sr) == ((double) (o.ec - o.sc)) / (o.er - o.sr)
					&& (sr - sc) == (o.sr - o.sc)) return false;
			return true;
		}
	}
	
	static void makeSet(int[] set) {
		for (int i = 0; i < N; i++) {
			set[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(set[a] == a) return a;
		return set[a] = findSet(set[a]);
	}
	
	static void union(int a, int b) {
		set[findSet(a)] = findSet(b);
	}
}
