package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_보석도둑 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		
		// 무게가 적은 순서로 정렬
		PriorityQueue<Jewel> minM = new PriorityQueue<>(N, (o1, o2) -> Integer.compare(o1.M, o2.M));
		for (int i = 0; i < N; i++) {
			minM.offer(new Jewel(br.readLine().split(" ")));
		}
		
		// 가방 최대 무게가 작은 순서로 정렬
		int[] bag = new int[K];
		for (int i = 0; i < bag.length; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bag);
		
		// 현재 가방의 최대 무게보다 작은 보석 중에서 가격이 가장 높은 것 뽑기
		PriorityQueue<Jewel> maxV = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.V, o1.V));
		long res = 0;
		for (int i = 0; i < K; i++) {
			int C = bag[i];
			while(!minM.isEmpty() && minM.peek().M <= C) {
				maxV.offer(minM.poll());
			}
			if(maxV.isEmpty()) continue;
			res += maxV.poll().V;
		}
		
		br.close();
		System.out.println(res);
	}
	
	static class Jewel {
		int M, V;
		
		public Jewel(String[] input) {
			this.M = Integer.parseInt(input[0]);
			this.V = Integer.parseInt(input[1]);
		}
		
	}
}
