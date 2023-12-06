package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main_친구네트워크 {
	
	static int[] friendship, friendCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int MAX_F = 100000;
		friendship = new int[MAX_F * 2 + 1];
		friendCnt = new int[MAX_F * 2 + 1];
		Map<String, Integer> nameMap = new HashMap<>(MAX_F * 2);
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			int F = Integer.parseInt(br.readLine());
			// 초기화
			makeSet(F);
			Arrays.fill(friendCnt, 1, F * 2 + 1, 1);
			nameMap.clear();
			
			int idx = 0;
			for (int i = 0; i < F; i++) {
				String[] input = br.readLine().split(" ");
				// nameMap에 이름과 idx 매칭
				for (int j = 0; j < 2; j++) {
					if(!nameMap.containsKey(input[j])) {
						nameMap.put(input[j], ++idx);
					}
				}
				int a = nameMap.get(input[0]);
				int b = nameMap.get(input[1]);
				sb.append(union(a, b)).append("\n");
			}
			System.out.println(nameMap);
			print(F);
		}
		br.close();
		System.out.println(sb);
	}
	
	static void print(int F) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= F * 2; i++) {
			sb.append(friendship[i]).append(" ");
		}
		System.out.println(sb);
	}
	
	static void makeSet(int F) {
		for (int i = 1; i <= F * 2; i++) {
			friendship[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(friendship[a] == a) return a;
		return friendship[a] = findSet(friendship[a]);
	}
	
	static int union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if(a != b) {
			friendship[a] = b;
			friendCnt[b] += friendCnt[a];
		}
		return friendCnt[b];
	}
}
