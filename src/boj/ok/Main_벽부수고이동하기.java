package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_벽부수고이동하기 {
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int N, M, NM;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		NM = N * M;
		
		boolean[] map = new boolean[NM];
		boolean[] visited = new boolean[NM * 2];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i * M + j] = (str.charAt(j) == '0');
			}
		}
		
		Deque<Integer> queue = new ArrayDeque<>();
		if(NM != 1) queue.add(0);
		visited[0] = visited[NM] = true;
		
		int len = 1, val, r, c, rc, rr, cc, rrcc, newVal, crash;
		boolean isOk = false;
		a:while(!queue.isEmpty()) {
			// 길이 증가
			len++;
			
//			print(visited, 0, NM);
			print(visited, NM, NM * 2);
			
			for (int i = 0, size = queue.size(); i < size; i++) {
				val = queue.poll();
				crash = val / NM;
				rc = val % NM;
				r = rc / M;
				c = rc % M;
				
				for (int d = 0; d < 4; d++) {
					rr = r + dr[d];
					cc = c + dc[d];
					
					// 경계선 너머 제외
					if(!isValid(rr, cc)) continue;
					
					rrcc = rc + dr[d] * M + dc[d];
					// 도착
					if(rrcc == NM - 1) {
						isOk = true;
						break a;
					}
					
					
					newVal = rrcc + crash * NM;
					// 이동 가능
					if(map[rrcc]) {
						if(visited[newVal]) continue;
						visited[newVal] = true;
						queue.offer(newVal);
					}
					// 벽
					else {
						if(crash == 1 || visited[newVal + NM]) continue;
						visited[newVal + NM] = true;
						queue.offer(newVal + NM);
					}
				}
			}
		}
		
		if(isOk || NM == 1) System.out.println(len);
		else System.out.println(-1);
	}
	
	static void print(boolean[] map, int start, int end) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < end; i++) {
			if(i % M == 0) sb.append("\n");
			if(map[i]) sb.append(0);
			else sb.append(1);
			sb.append(" ");
		}
		System.out.println(sb);
	}
	
	static boolean isValid(int r, int c) {
		return r < N && c < M && r >= 0 && c >= 0;
	}
}
