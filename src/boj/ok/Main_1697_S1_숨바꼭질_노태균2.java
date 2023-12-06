package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_1697_S1_숨바꼭질_노태균2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		br.close();
		boolean[] visited = new boolean[K + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		if(N != K) {
			queue.offer(N);
			if(N < K) {
				visited[N] = true;
			}
		}
		int cur, cnt = 0, a, b, c, n = 0, vLen = 500000;
		a:while(!queue.isEmpty()) {
			cnt++;
			if((K += ++n) > 500000) {
				cnt = -1;
				break;
			}
			for(int i = 0, size = queue.size(); i < size; i++) {
				cur = queue.poll();
				a = cur * 2;
				b = cur + 1;
				c = cur - 1;
				if(a == K || b == K || c == K) break a;
				if(a < vLen && N < K) {
					queue.offer(a);
					if(a < K && !visited[a]) {
						visited[a] = true;
					}
				}
				if(b < vLen && N < K) {
					queue.offer(b);
					if(b < K && !visited[b]) {
						visited[b] = true;
					}
				}
				if(c >= 0 && !visited[c]) {
					queue.offer(c);
					visited[c] = true;
				}
			}
		}
		System.out.println(cnt);
		
	}
}
