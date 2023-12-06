package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main_2636_G4_치즈_노태균 {
	static final int CHEESE = 0;
	static final int INSIDE = 10000;
	static final int OUTSIDE = -1;
	static final int EMPTY = -2;
	static int N, M, cheeseCnt, cheeseIdx = 0, insideIdx = INSIDE;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int[][] map;
	static boolean isOutside;
	static Set<Integer> set = new HashSet<>();
	static Set<Integer> cheeseSet = new HashSet<>();
	static List<Set<Integer>> insideList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new int[N][M];
		Arrays.fill(map[0], OUTSIDE);
		Arrays.fill(map[N - 1], OUTSIDE);
		br.readLine();
		for(int i = 1; i < N - 1; i++) {
			map[i][0] = map[i][M - 1] = OUTSIDE;
			input = br.readLine().split(" ");
			for(int j = 1; j < M - 1; j++) {
				if(input[j].equals("1")) {
					map[i][j] = cheeseIdx;
					cheeseSet.add(i * M + j);
				} else {
					map[i][j] = EMPTY;
				}
			}
		}
		br.readLine();
		
		// 초기 맵 셋팅
		for(int i = 1; i < N - 1; i++) {
			for(int j = 1; j < M - 1; j++) {
				if(map[i][j] == EMPTY) {
					isOutside = false;
					dfs(i, j);
					if(isOutside) {
						setMap(set, OUTSIDE);
					} else {
						insideList.add(new HashSet<>(set.size()));
						insideList.get(insideIdx++ - INSIDE).addAll(set);
					}
					set.clear();
				}
			}
		}
		
		// 치즈의 초기 상태의 가장자리
		Set<Integer> from = cheeseSet, to = set;
		getEdgeSet(from, to, ++cheeseIdx);
		
		// 치즈 제거 반복
		while(to.size() != 0) {
			if(cheeseIdx % 2 == 1) {
				from = set;
				to = cheeseSet;
			} else {
				from = cheeseSet;
				to = set;
			}
			to.clear();
			removeCheese(from, to, ++cheeseIdx);
		}
		System.out.println(cheeseIdx - 1);
		System.out.println(from.size());
	}
	
	// 치즈 제거
	private static void removeCheese(Set<Integer> from, Set<Integer> to, int idx) {
		int r, c, rr, cc, idxx;
		for(int num : from) {
			r = num / M;
			c = num % M;
			for(int i = 0; i < 4; i++) {
				rr = r + dr[i];
				cc = c + dc[i];
				if(map[rr][cc] == CHEESE) {
					map[rr][cc] = idx;
					to.add(rr * M + cc);
				} else if(map[rr][cc] >= INSIDE) {
					idxx = map[rr][cc] - INSIDE;
					setMap(insideList.get(idxx), OUTSIDE);
					removeCheese(insideList.get(idxx), to, idx);
					insideList.get(idxx).clear();
				}
			}
		}
	}

	// 초기 상태의 치즈에서 가장자리 부분 구하기
	private static void getEdgeSet(Set<Integer> from, Set<Integer> to, int idx) {
		int r, c, rr, cc;
		for(int num : from) {
			r = num / M;
			c = num % M;
			for(int i = 0; i < 4; i++) {
				rr = r + dr[i];
				cc = c + dc[i];
				if(map[rr][cc] == OUTSIDE) {
					map[r][c] = idx;
					to.add(r * M + c);
					break;
				}
			}
		}
	}

	// map에 숫자 덮어쓰기
	private static void setMap(Set<Integer> set2, int idx) {
		for(int num : set2) {
			map[num / M][num % M] = idx;
		}
	}

	// empty부분이 치즈의 outside인지 inside인지 판단
	private static void dfs(int r, int c) {
		map[r][c] = insideIdx;
		set.add(r * M + c);
		
		int rr, cc;
		for(int i = 0; i < 4; i++) {
			rr = r + dr[i];
			cc = c + dc[i];
			if(map[rr][cc] == OUTSIDE) {
				isOutside = true;
				continue;
			}
			if(map[rr][cc] == EMPTY) {
				dfs(rr, cc);
			}
		}
	}
}

