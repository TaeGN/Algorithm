package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_소수의연속합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		boolean[] isPrime = new boolean[N + 1];
		List<Integer> primeList = new ArrayList<>();
		
		for (int i = 2; i <= N; i++) {
			if(isPrime[i]) continue;
			primeList.add(i);
			for (int j = i + i; j <= N; j += i) {
				isPrime[j] = true;
			}
		}
		
		int size = primeList.size();
		int res = 0;
		int sum = 0;
		int start = 0;
		int end = 0;
		while(end < size) {
			if(sum < N) sum += primeList.get(end++);
			while(sum > N && start < end) sum -= primeList.get(start++);
			if(sum == N) {
				sum -= primeList.get(start++);
				res++;
			}
		}
		System.out.println(res);
	}
}
