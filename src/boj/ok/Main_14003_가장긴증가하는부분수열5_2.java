package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main_14003_가장긴증가하는부분수열5_2 {
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		
		int prev = Integer.MIN_VALUE;
		int cur = 0;
		for (int i = 0; i < N; i++) {
			cur = Integer.parseInt(input[i]);
			if(prev == cur) continue;
			if(prev < cur) {
				prev = cur;
				list.add(cur);
			}
			else list.set(-1 - Collections.binarySearch(list, cur), cur);
//			else list.set(binarySearch(cur, 0, list.size() - 1), cur);
		}
		
		br.close();
		System.out.println(list.size());
		System.out.println();
	}

	private static int binarySearch(int cur, int start, int end) {
		if(start >= end) return end;
		
		int temp = (end - start + 1) / 2 + start;
		int next = list.get(temp);
		if(cur == next) return temp;
		else if(cur > next) return binarySearch(cur, temp + 1, end);
		else return binarySearch(cur, start, temp - 1);
	}
}
