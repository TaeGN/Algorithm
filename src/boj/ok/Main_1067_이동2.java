package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1067_이동2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Long> queue = new PriorityQueue<>(N, (o1, o2) -> Long.compare(o2, o1));
		int[] X = new int[N];
		int[] Y = new int[N];
		int i = 0;
		for(String s : br.readLine().split(" ")) {
			X[i++] = Integer.parseInt(s);
		}
		
		i = 0;
		for(String s : br.readLine().split(" ")) {
			Y[i++] = Integer.parseInt(s);
		}
		br.close();
		
		for(i = 0; i < N; i++) {
			long a = 0;
			for(int j = 0; j < N; j++) {
				a += X[j] * Y[(i + j) % N];
			}
			queue.offer(a);
		}
		System.out.println(queue.poll());
	}
	
}
