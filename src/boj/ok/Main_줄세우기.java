package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main_줄세우기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		ArrayList<Integer>[] list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		// 진입차수 저장
		int[] inCnt = new int[N + 1];
		
		// 학생의 키 비교한 데이터 저장
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			list[from].add(to);
			inCnt[to]++;
		}
		br.close();
		
		Queue<Integer> queue = new ArrayDeque<>();
		// 현 단계에서 진입차수가 0인 idx queue에 넣기
		for (int i = 1; i <= N; i++) {
			if(inCnt[i] == 0) queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int from = queue.poll();
			sb.append(from).append(" ");
			
			for (int to : list[from]) {
				if(--inCnt[to] == 0) queue.offer(to);
			}
		}
		System.out.println(sb);
	}
}
