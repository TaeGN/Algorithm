package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main_17411_가장긴증가하는부분수열6 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		List<Integer> list = new ArrayList<>();
		List<Integer> res = new ArrayList<>();
		list.add(Integer.MIN_VALUE);
		res.add(0);
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(input[i]);
			int size = list.size();
			int prev = list.get(size - 1);
			if(prev == num) {
				res.add(size - 1);
				continue;
			}
			if(prev < num) {
				list.add(num);
				res.add(size);
			}
			else {
				int idx = Collections.binarySearch(list, num);
				if(idx < 0) {
					list.set(-1-idx, num);
					res.add(-1-idx);
				} else {
					res.add(idx);
				}
			}
		}
		br.close();
		int len = list.size() - 1;
		
		System.out.println(list);
		System.out.println(res);
		
		long[] D = new long[len + 1];
		D[0] = 1;
		final long MOD = (long) Math.pow(10, 9) + 7;
//		final long MOD = 1000000007L;
		for (int i = 1, end = res.size(); i < end; i++) {
			int num = res.get(i);
			D[num] = (D[num] + D[num - 1] + MOD) % MOD;
		}
		System.out.println(Arrays.toString(D));
		sb.append(len).append(" ").append(D[len] % MOD);
		System.out.println(sb);
	}
}
