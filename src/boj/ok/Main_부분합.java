package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_부분합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int S = Integer.parseInt(input[1]);
		
		
		input = br.readLine().split(" ");
		br.close();
		
		int minLen = Integer.MAX_VALUE;
		int sum = 0;
		for (int start = 0, end = 0; end < N; end++) {
			sum += Integer.parseInt(input[end]);
			
			if(sum >= S) {
				while(start <= end) {
					int val = Integer.parseInt(input[start]);
					if(sum - val < S) {
						minLen = Math.min(minLen, end - start + 1);
						break;
					}
					sum -= val;
					start++;
				}
			}
//			System.out.println(start + " : " + end + " : " + sum);
		}
		
		System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
	}
}
