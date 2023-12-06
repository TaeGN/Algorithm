package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_책페이지 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		br.close();
		
		long[] cnt = new long[10];
		int power = 1;
		int n = N;
		while(n > 0) {
			
			// 나머지 부분 더하기
			addCountOne(cnt, n % 10, N % power);
			addCount(cnt, n % 10 - 1, power);
			System.out.println(Arrays.toString(cnt));
			cnt[0] -= power;
			
			// 몫 만큼 더해주기
			addCountAll(cnt, n / 10);
			System.out.println(Arrays.toString(cnt));
			
			n /= 10;
			power *= 10;
		}
		
		for (long l : cnt) {
			sb.append(l).append(" ");
		}
		
		System.out.println(sb);
	}
	
	private static void addCountOne(long[] cnt, int idx, int weight) {
		cnt[idx] += weight + 1;
	}

	private static void addCountAll(long[] cnt, int weight) {
		addCount(cnt, 9, weight);
	}
	
	private static void addCount(long[] cnt, int idx, int weight) {
		if(idx < 0) return;
		for (int i = 0; i <= idx; i++) {
			cnt[i] += weight;
		}
	}
}
