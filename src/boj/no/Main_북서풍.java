package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_북서풍 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		final int MAX_N = 75000;
		IslandPool islandPool = new IslandPool(MAX_N);
		
		for (int tc = 0; tc < T; tc++) {
			// 초기화
			islandPool.init();
			
			int N = Integer.parseInt(br.readLine());
			Island[] sortedX = new Island[N];
			for (int i = 0; i < N; i++) {
				String[] input = br.readLine().split(" ");
				int x = Integer.parseInt(input[0]);
				int y = Integer.parseInt(input[1]);
				sortedX[i] = islandPool.get(x, y);
			}
			Arrays.sort(sortedX, (o1, o2) -> {
				if(o1.x != o2.x) return Integer.compare(o1.x, o2.x);
				return Integer.compare(o2.y, o1.y);
			});
			
			for (int i = 0; i < N; i++) {
				sortedX[i].setIdx(i);
			}
			
			Island[] sortedY = sortedX.clone();
			Arrays.sort(sortedY, (o1, o2) -> {
				if(o1.y != o2.y) return Integer.compare(o2.y, o1.y);
				return Integer.compare(o1.x, o2.x);
			});
			
			int res = 0;
			for (int i = 0; i < N; i++) {
				Island X = sortedX[i];
				int cnt = N - i - 1;
				for (int j = 0; j < N; j++) {
					Island Y = sortedY[j];
					if(X == Y) break;
					if(Y.idx > i) cnt--;
				}
				res += cnt;
			}
			sb.append(res).append("\n");
		}
		br.close();
		System.out.println(sb);
	}
	
	static class Island {
		int x, y, idx;
		public Island() {}
		public void setPos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public void setIdx(int idx) {
			this.idx = idx;
		}
	}
	
	static class IslandPool {
		Island[] pool;
		int idx;
		public IslandPool(int size) {
			this.pool = new Island[size];
			for (int i = 0; i < size; i++) {
				this.pool[i] = new Island();
			}
			this.init();
		}
		public void init() {
			this.idx = -1;
		}
		public Island get(int x, int y) {
			Island island = this.pool[++idx];
			island.setPos(x, y);
			return island;
		}
	}
}
