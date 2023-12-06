package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main_2636_치즈_노태균 {
	static Set<Integer> edgeSet = new HashSet<>();
	static List<Set<Integer>> insideList = new ArrayList<>();
	static Set<Integer> noCheese = new HashSet<>();
	static final int CHEESE = -1;
	static final int EMPTY = -2;
	static final int EDGE = -3;
	static int N, M, insideIdx, cheeseCnt;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int[][] map;
	static boolean isEdge;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				if(input[j].equals("1")) {
					map[i][j] = CHEESE;
					cheeseCnt++;
				} else {
					map[i][j] = EMPTY;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == EMPTY) {
					getNoCheese(i, j);
					if(isEdge) {
						setMap(noCheese, EDGE);
						edgeSet = getEdge(noCheese, EDGE);
					} else {
						insideList.add(getEdge(noCheese, insideIdx));
						insideIdx++;
					}
					isEdge = false;
				}
			}
		}
		int cnt = 0;
//		Set<Integer> prev = null; 
//		while(cheeseCnt != 0) {
//			cnt++;
//			prev = edgeSet;
//			cheeseCnt -= removeCheese(edgeSet);
//			edgeSet = getEdge(noCheese, EDGE);
//			break;
//		}
		
		System.out.println(edgeSet);
		System.out.println(insideList);
		print(map);
		System.out.println(cnt);
	}
	
	// 제거할 치즈 개수 리턴
	private static int removeCheese(Set<Integer> set) {
		int r, c, rr, cc, cnt = 0;
		for(int num : edgeSet) {
			r = num / M;
			c = num % M;
			for(int i = 0; i < 4; i++) {
				rr = r + dr[i];
				cc = c + dc[i];
				if(isBoundary(rr, cc)) continue;
				switch(map[rr][cc]) {
				case EDGE:
					continue;
				case CHEESE:
					noCheese.add(rr * M + cc);
					map[rr][cc] = EDGE;
					cnt++;
					break;
				default:
					System.out.println("===============" + map[rr][cc]);
					noCheese.addAll(insideList.get(map[rr][cc]));
					setMap(insideList.get(map[rr][cc]), EDGE);
				}
			}
		}
		return cnt;
	}

	static void print(int[][] map) {
		for(int[] a : map) {
			for(int b : a) {
				if(b == EDGE) System.out.print(0);
				else if(b == 0) System.out.print(2);
				else System.out.print(1);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	// 치즈의 안쪽 빈 공간은 -값으로 채우기
	private static void setMap(Set<Integer> set, int idx) {
		int r, c;
		for(int num : set) {
			r = num / M;
			c = num % M;
			map[r][c] = idx;
		}
	}
	
	// 치즈 없는 구역의 가장자리부분 (맵의 경계 제외)
	private static Set<Integer> getEdge(Set<Integer> prevSet, int idx) {
		Set<Integer> set = new HashSet<>();
		int r, c, rr, cc;
		a:for(int num : prevSet) {
			r = num / M;
			c = num % M;
			for(int i = 0; i < 4; i++) {
				rr = r + dr[i];
				cc = c + dc[i];
				if(isBoundary(rr, cc)) continue;
				if(map[rr][cc] != idx) {
					set.add(rr * M + cc);
					continue a;
				}
			}
		}
		prevSet.clear();
		return set;
	}
	
	// 초기 치즈 없는 구역 ( 치즈의 안과 밖 구분 - boolean isEdge )
	private static void getNoCheese(int r, int c) {
		noCheese.add(r * M + c);
		
		int rr, cc;
		for(int i = 0; i < 4; i++) {
			rr = r + dr[i];
			cc = c + dc[i];
			if(isBoundary(rr, cc)) { 
				isEdge = true;
				continue;
			}
			if(map[rr][cc] == EMPTY) {
				map[rr][cc] = insideIdx;
				getNoCheese(rr, cc);
			}
		}
	}

	private static boolean isBoundary(int rr, int cc) {
		return rr >= N || rr < 0 || cc >= M || cc < 0;
	}
}

