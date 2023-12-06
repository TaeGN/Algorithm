package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_마법사상어와블리자드 {
	
	static int map[][], changeMap[];
	static int N, M, center, maxS;
	static final int[][] BLIZZARD_D = {null,{-1,0},{1,0},{0,-1},{0,1}};
	static final int[][] MOVE_D = {{1,0},{0,1},{-1,0},{0,-1}};
	static final int[] EXPLOSION_CNT = new int[4];
	static DeleteList deleteList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new int[N + 1][N + 1];
		changeMap = new int[N * N];
		center = (N + 1) / 2;
		maxS = (N - 1) / 2;
		deleteList = new DeleteList();
		
		// map 초기화
		for (int i = 1; i <= N; i++) {
			int j = 1;
			for (String str : br.readLine().split(" ")) {
				map[i][j] = Integer.parseInt(str);
				j++;
			}
		}
		
		// 마법 시전
		for (int i = 1; i <= M; i++) {
			input = br.readLine().split(" ");
			int d = Integer.parseInt(input[0]);
			int s = Integer.parseInt(input[1]);
//			print(i,"초기값");
			
			// 블리자드 시전
			blizzard(d, s);
//			print(i,"블리자드 후");
			
			// 이동
			move();
//			print(i,"이동 후");
			
			// 폭발 - 이동 반복
			while(explosion()) {
				move();
//				print(i,"폭발 후");
			}
			
			// 변화
			change();
			print(i,"변화 후");
			System.out.println(Arrays.toString(EXPLOSION_CNT));
		}
		br.close();
		
		int res = 0;
		for (int i = 1; i <= 3; i++) {
			res += i * EXPLOSION_CNT[i];
		}
		System.out.println(res);
	}


	public static void print(int cnt, String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("----").append(cnt).append("번째 : ").append(msg).append("----\n");
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}	
	
	
	private static void change() {
		int num = 0;
		int cnt = 0;
		int idx = 0;
		int i = 1, j = 0, d = 0, r = center - i, c = center - i;
		for (; i <= maxS; d = 0, r = c = center - ++i) {
			for (; d < 4; d++, j = 0) {
				for (; j < 2 * i; j++) {
					r += MOVE_D[d][0];
					c += MOVE_D[d][1];
					if(map[r][c] == num) cnt++;
					else {
						if(num != 0 && idx < N * N - 1) {
							changeMap[idx++] = cnt;
							changeMap[idx++] = num;
						} 
						num = map[r][c];
						cnt = 1;
					}
				}
			}
		}
		
		int end = idx;
		idx = 0;
		i = 1; j = 0; d = 0; r = center - i; c = center - i;
		for (; i <= maxS; d = 0, r = c = center - ++i) {
			for (; d < 4; d++, j = 0) {
				for (; j < 2 * i; j++) {
					r += MOVE_D[d][0];
					c += MOVE_D[d][1];
					if(idx < end) map[r][c] = changeMap[idx++];
					else map[r][c] = 0;
				}
			}
		}
	}
	
	private static boolean explosion() {
		deleteList.init();
		
		int i = 1, j = 0, d = 0, r = center - i, c = center - i;
		for (; i <= maxS; d = 0, r = c = center - ++i) {
			for (; d < 4; d++, j = 0) {
				for (; j < 2 * i; j++) {
					r += MOVE_D[d][0];
					c += MOVE_D[d][1];
					
					// 4개 이상 연속하는 구슬 폭발
					deleteList.add(r, c);
				}
			}
		}
		
		deleteList.delete();
		return deleteList.isDelete;
	}


	private static void move() {
		Blank blank = new Blank();
		int i = 1, j = 0, d = 0, r = center - i, c = center - i;
		for (; i <= maxS; d = 0, r = c = center - ++i) {
			for (; d < 4; d++, j = 0) {
				for (; j < 2 * i; j++) {
					r += MOVE_D[d][0];
					c += MOVE_D[d][1];
					
					// 빈칸이면 넘어가기
					if(map[r][c] == 0) continue;
					
					// 현재 지점까지 빈칸 없을 경우 continue;
					if(!blank.isBlank) {
						if(!setBlank(r, c, blank)) continue;
					} 
					
					// 이동
					map[blank.r][blank.c] = map[r][c];
					blank.isBlank = false;
					map[r][c] = 0;
				}
			}
		}
		
	}

	private static boolean setBlank(int curR, int curC, Blank blank) {
		int i = blank.i;
		int j = blank.j;
		int d = blank.d;
		int r = blank.r;
		int c = blank.c;
		for (; i <= maxS; d = 0 , r = c = center - ++i) {
			for (; d < 4; d++, j = 0) {
				for (; j < 2 * i; j++) {
					r += MOVE_D[d][0];
					c += MOVE_D[d][1];
					if(r == curR && c == curC) {
						blank.init(r, c, i, j + 1, d);
						return blank.isBlank = false;
					}
					if(map[r][c] == 0) {
						blank.init(r, c, i, j + 1, d);
						return blank.isBlank = true;
					}
				}
			}
		}
		return false;
	}

	private static void blizzard(int d, int s) {
		int r = center;
		int c = center;
		for (int i = 0; i < s; i++) {
			map[r += BLIZZARD_D[d][0]][c += BLIZZARD_D[d][1]] = 0;
		}
	}
	
	static class DeleteList {
		int[] rArr, cArr;
		int size, num;
		boolean isDelete;
		
		public DeleteList() {
			this.rArr = new int[N * N];
			this.cArr = new int[N * N];
		}
		
		public void delete() {
			if(this.num == 0 || this.size < 4) return;
			EXPLOSION_CNT[this.num] += this.size;
			for (int i = 0; i < size; i++) {
				map[rArr[i]][cArr[i]] = 0;
			}
			this.isDelete = true;
		}

		public void init() {
			this.size = 0;
			this.num = 0;
			this.isDelete = false;
		}
		
		public boolean add(int r, int c) {
			if(map[r][c] != this.num) {
				this.delete();
				this.size = 0;
				this.num = map[r][c];
				this.rArr[this.size] = r;
				this.cArr[this.size] = c;
				this.size++;
				return false;
			} else {
				this.rArr[this.size] = r;
				this.cArr[this.size] = c;
				this.size++;
				return true;
			}
		}
		
		public void print() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < this.size; i++) {
				sb.append(this.rArr[i]).append(",").append(this.cArr[i]).append(" ");
			}
			System.out.println(sb);
		}
	}
	
	static class Blank {
		int r, c, i, j, d;
		boolean isBlank;
		
		public Blank() {
			this.i = 1;
			this.r = center - this.i;
			this.c = center - this.i;
		}
		
		public void init(int r, int c, int i, int j, int d) {
			this.r = r;
			this.c = c;
			this.i = i;
			this.j = j;
			this.d = d;
		}

		public Blank(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}
