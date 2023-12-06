package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_14003_가장긴증가하는부분수열5_11 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		final int INF = Integer.MAX_VALUE;
		List<Integer> list = new ArrayList<>();
		list.add(Integer.MIN_VALUE);
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(input[i]);
			int prev = list.get(list.size() - 1);
			if(prev == num) continue;
			if(prev < num) list.add(num);
			else {
				int idx = Collections.binarySearch(list, num);
				if(idx < 0) list.set(-1-idx, num);
			}
		}
//		System.out.println(list);
		br.close();
		int len = list.size() - 1;
		sb.append(len).append("\n");
		
		int prev = Integer.MAX_VALUE;
		int next = list.get(len);
		for (int i = N - 1, j = len; i >= 0; i--) {
			int num = Integer.parseInt(input[i]);
			if(num >= next && num < prev) {
				list.set(j, num);
				if(j == 0) break;
				next = list.get(--j);
				prev = num;
				
			}
		}
		for (int i = 1; i <= len; i++) {
			sb.append(list.get(i)).append(" ");
		}
		System.out.println(sb);
	}

//	private static int binarySearch(List<Integer> list, int num) {
//		int start = 0, end = list.size() - 1, mid = 0;
//		
//		while(start <= end) {
//			mid = (start + end) / 2;
//			if(list.get(mid) == num) return mid;
//			if(list.get(mid) < num) end = mid - 1;
//			else start = mid + 1;
//		}
//		return end;
//	}
}
