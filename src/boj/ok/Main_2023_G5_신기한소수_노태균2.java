package boj.ok;

import java.util.Scanner;

public class Main_2023_G5_신기한소수_노태균2 {
	static StringBuilder sb;
	static boolean[] primes;
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		N = sc.nextInt();
		
		// false : 소수, true : 소수아님 
		primes = new boolean[(int)Math.pow(10,N)];
		int size = primes.length;
		primes[0] = primes[1] = true;
		
		int i = 1;
		while(++i * i < size) {
			if(!primes[i]) {
				for(int j = 2 * i; j < size; j += i) {
					primes[j] = true;
				}
			}
		}
		
		for(int j = 1; j < 10; j++) {
			if(!primes[j]) iPrime(1, j);
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
			if(!primes[prime + i]) {
				iPrime(cnt + 1, prime + i);
			}
		}
	}
}
