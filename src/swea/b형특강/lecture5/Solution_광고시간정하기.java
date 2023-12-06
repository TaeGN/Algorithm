package swea.b형특강.lecture5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_광고시간정하기 {
	
	static int N, L;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int MAX_N = 100000;
		Time[] timeArr = new Time[MAX_N];
		int[] sum = new int[MAX_N + 1];
		for (int i = 0; i < MAX_N; i++) {
			timeArr[i] = new Time();
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			L = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				String[] input = br.readLine().split(" ");
				timeArr[i].set(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
			}
			Arrays.sort(timeArr, 0, N);
			
			for (int i = 0; i < N; i++) {
				sum[i + 1] = sum[i] + timeArr[i].getDiff();
			}
			
			int res = 0;
			for (int i = 0; i < N; i++) {
				res = Math.max(res, binarySearch(i, timeArr, sum));
			}
			
			sb.append(res).append("\n");
		}
		
		br.close();
		System.out.print(sb);
	}	
	
	static int binarySearch(int idx, Time[] timeArr, int[] sum) {
		if(timeArr[idx].getDiff() >= L) return timeArr[idx].getDiff();
		
		int start = idx;
		int end = N - 1;
		int mid;
		int res = idx;
		
		while(start <= end) {
			mid = start + (end - start) / 2;
			
			if(timeArr[start].s + timeArr[end].e > L) {
				end = mid - 1;
			} else {
				start = mid + 1;
				res = mid;
			}
		}
		
		int ans = sum[res + 1] - sum[idx];
		if(res != N - 1 && timeArr[res + 1].s < timeArr[idx].s + L) {
			ans += timeArr[idx].s + L - timeArr[res + 1].s;
		}
		return ans;
	}
	
	static class Time implements Comparable<Time> {
		int s, e;

		public void set(int s, int e) {
			this.s = s;
			this.e = e;
		}
		
		public int getDiff() {
			return this.e - this.s;
		}

		@Override
		public int compareTo(Time o) {
			return Integer.compare(this.s, o.s);
		}
	}
}
