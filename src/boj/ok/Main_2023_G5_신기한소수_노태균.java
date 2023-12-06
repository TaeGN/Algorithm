package boj.ok;

import java.util.Scanner;

public class Main_2023_G5_신기한소수_노태균 {
	static StringBuilder sb;
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		N = sc.nextInt();
		
		for(int prime : new int[] {2, 3, 5, 7}) {
			iPrime(1, prime);
		}
		
		System.out.println(sb);
	}
	
	private static void iPrime(int cnt, int prime) {
		if(cnt == N) {
			sb.append(prime).append("\n");
			return;
		}
		
		prime *= 10;
		for(int i = 1; i < 10; i += 2) {
			if(isPrime(prime + i)) {
				iPrime(cnt + 1, prime + i);
			}
		}
	}

	private static boolean isPrime(int num) {
		int len = (int) Math.sqrt(num);
		for(int i = 2; i <= len; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
