package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_h2048 {
	
	static int N, maxVal;
	static final int MAX_COUNT = 10;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		N = Integer.parseInt(br.readLine());
		
		// map 초기화
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(input[j]);
				map[i][j] = num;
				maxVal = Math.max(maxVal, num);
			}
		}
		System.out.println(maxVal);
		boardGame(0, map, maxVal);
		System.out.println(maxVal);
	}
	
	public static void print(int cnt, int[][] map) {
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append(" : \n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void boardGame(int cnt, int[][] curMap, int max) {
		
		// 현재 지점에서 끝까지 진행해도 최대값보다 작으면 return
		if(max * (int)Math.pow(2, MAX_COUNT - cnt) <= maxVal) return;
		
		// MAX_COUNT번 이동 완료 후 가장 큰 블록 구하기
		if(cnt == MAX_COUNT) {
			maxVal = Math.max(maxVal, max);
			return;
		}
		
		// 4방향 모두 가보기
		int[][] newMap = null;
//		newMap = copyMap(curMap);
//		int newMax = move(1, newMap, max);
//		print(cnt, curMap);
//		boardGame(cnt + 1, newMap, newMax);
		for (int i = 0; i < 4; i++) {
			newMap = copyMap(curMap);
			int newMax = move(i, newMap, max);
			if(isSame(curMap, newMap)) continue;
			print(cnt, curMap);
			boardGame(cnt + 1, newMap, newMax);
		}
	}

	private static boolean isSame(int[][] curMap, int[][] newMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(curMap[i][j] != newMap[i][j]) return false;
			}
		}
		return true;
	}

	private static int getMaxVal(int[][] curMap) {
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				res = Math.max(res, curMap[i][j]);
			}
		}
		return res;
	}

	private static int move(int d, int[][] newMap, int max) {
		switch(d) {
		case 0:
			for (int i = 0; i < N; i++) {
				for (int j = 1, k = 0; j < N; j++) {
					if(newMap[i][j] != 0) {
						// 같으면 합치고, 다르면 붙인다.
						if(newMap[i][k] == newMap[i][j]) {
							max = Math.max(max, newMap[i][k++] *= 2);
//							newMap[i][k++] *= 2;
							newMap[i][j] = 0;
						} else {
							if(newMap[i][k] != 0) k++;
							newMap[i][k] = newMap[i][j];
							if(k != j) newMap[i][j] = 0;
						}
					}
				}
			}
			break;
		case 1:
			for (int i = 0; i < N; i++) {
				for (int j = N - 2, k = N - 1; j >= 0; j--) {
					if(newMap[i][j] != 0) {
						if(newMap[i][k] == newMap[i][j]) {
							max = Math.max(max, newMap[i][k--] *= 2);
//							newMap[i][k--] *= 2;
							newMap[i][j] = 0;
						} else {
							if(newMap[i][k] != 0) k--;
							newMap[i][k] = newMap[i][j];
							if(k != j) newMap[i][j] = 0;
						}
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				for (int j = 1, k = 0; j < N; j++) {
					if(newMap[j][i] != 0) {
						if(newMap[k][i] == newMap[j][i]) {
							max = Math.max(max, newMap[k++][i] *= 2);
//							newMap[k++][i] *= 2;
							newMap[j][i] = 0;
						} else {
							if(newMap[k][i] != 0) k++;
							newMap[k][i] = newMap[j][i];
							if(k != j) newMap[j][i] = 0;
						}
					}
				}
			}
			break;
		case 3:
			for (int i = 0; i < N; i++) {
				for (int j = N - 2, k = N - 1; j >= 0; j--) {
					if(newMap[j][i] != 0) {
						if(newMap[k][i] == newMap[j][i]) {
							max = Math.max(max, newMap[k--][i] *= 2);
//							newMap[k--][i] *= 2;
							newMap[j][i] = 0;
						} else {
							if(newMap[k][i] != 0) k--;
							newMap[k][i] = newMap[j][i];
							if(k != j) newMap[j][i] = 0;
						}
					}
				}
			}
			break;
		}

		return max;
	}

	private static int[][] copyMap(int[][] curMap) {
		int[][] newMap = new int[N][];
		for (int i = 0; i < N; i++) {
			newMap[i] = curMap[i].clone();
		}
		return newMap;
	}
	
}
