package swea.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution_5643_D4_키순서_노태균 {
	static int N, M;
	static List<Integer>[][] map;
	static Set<Integer>[][] D;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int cnt, sum, from, to;
		String[] input;
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			map = new List[N + 1][2];
			for(int i = 1; i <= N; i++) {
				map[i][0] = new ArrayList<>();
				map[i][1] = new ArrayList<>();
			}
			
			for(int i = 0; i < M; i++) {
				input = br.readLine().split(" ");
				from = Integer.parseInt(input[0]);
				to = Integer.parseInt(input[1]);
				map[from][0].add(to);
				map[to][1].add(from);
			}
			
			cnt = 0;
			D = new Set[N + 1][2];
			for(int i = 1; i <= N; i++) {
				if(D[i][0] == null) dfs(i, 0);
				if(D[i][1] == null) dfs(i, 1);
				if(D[i][0].size() + D[i][1].size() == N - 1) cnt++;
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
	
	
	private static void dfs(int idx, int a) {
		List<Integer> cur = map[idx][a];
		Set<Integer> set = D[idx][a] = new HashSet<>();
		for(int num : cur) {
			if(set.contains(num)) continue;
			if(D[num][a] == null) dfs(num, a);
			set.add(num);
			set.addAll(D[num][a]);
		}
	}
}
