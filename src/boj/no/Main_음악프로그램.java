package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main_음악프로그램 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		// singer - pd 관계성 저장
		Set<Integer>[] singer = new HashSet[N + 1];
		for (int i = 1; i <= N; i++) {
			singer[i] = new HashSet<>();
		}
		
		// pd - singer 관계성 저장
		Queue<Integer>[] pd = new ArrayDeque[M + 1];
		for (int i = 1; i <= M; i++) {
			pd[i] = new ArrayDeque<>();
		}
		
		for (int pdIdx = 1; pdIdx < M; pdIdx++) {
			Queue<Integer> curPd = pd[pdIdx];
			input = br.readLine().split(" ");
			for (String str : input) {
				int singerIdx = Integer.parseInt(str);
				curPd.offer(singerIdx);
				singer[singerIdx].add(pdIdx);
			}
		}
		
		int[] isOk = new int[N + 1];
		for (int singerIdx = 1; singerIdx <= N; singerIdx++) {
			isOk[singerIdx] = singer[singerIdx].size();
		}
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		a:while(cnt < N) {
			
			for (int pdIdx = 1; pdIdx <= M; pdIdx++) {
				int singerIdx = pd[pdIdx].peek();
				if(--isOk[singerIdx] == 0) {
					sb.append(singerIdx).append(" ");
					for (int idx : singer[singerIdx]) {
						pd[idx].poll();
					}
					continue a;
				}
			}
			
			
		}
	}
}
