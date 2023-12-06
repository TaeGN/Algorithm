package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_용액 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] res = new int[2];	// 결과값 저장
		
		String[] input = br.readLine().split(" ");
		int lIdx = 0;
		int rIdx = N - 1;
		int left = Integer.parseInt(input[lIdx]);
		int right = Integer.parseInt(input[rIdx]);
		int min = Integer.MAX_VALUE;
		while(lIdx != rIdx) {
			int val = Math.abs(left + right);
			System.out.println(val + " : " + left + " : " + right);
			if(min > val) {
				min = val;
				res[0] = left;
				res[1] = right;
			}
			if(left + right >= 0) {
				right = Integer.parseInt(input[--rIdx]);
			} else {
				left = Integer.parseInt(input[++lIdx]);
			}
		}
		br.close();
		StringBuilder sb = new StringBuilder();
		sb.append(res[0]).append(" ").append(res[1]);
		System.out.println(sb);
	}
}
