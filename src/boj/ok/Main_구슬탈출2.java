package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// 빨간구슬, 파란구슬 같은 자리 x
public class Main_구슬탈출2 {
	
	static final char GROUND = '.';
	static final char WALL = '#';
	static int N, M;
	static Point end;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		// 입력 받기
		char[][] map = new char[N][M];
		Marble marble = new Marble();
		end = new Point();
		for (int r = 0; r < N; r++) {
			int c = 0;
			for (char cha : br.readLine().toCharArray()) {
				switch(cha) {
				case 'B':
					marble.blue.r = r;
					marble.blue.c = c;
					map[r][c] = GROUND;
					break;
				case 'R':
					marble.red.r = r;
					marble.red.c = c;
					map[r][c] = GROUND;
					break;
				case 'O':
					end.r = r;
					end.c = c;
					map[r][c] = GROUND;
					break;
				default:
					map[r][c] = cha;
					break;
				}
				c++;
			}
		}
		System.out.println(move(marble, map));
	}
	
	public static int move(Marble marble, char[][] map) {
		boolean[][][][] visited = new boolean[N][M][N][M];
		int cnt = 0;
		Queue<Marble> queue = new ArrayDeque<>();
		queue.offer(marble);
		marble.visit(visited);
		
		int[] dr = {1,0,-1,0};
		int[] dc = {0,1,0,-1};
		
		while(!queue.isEmpty() && cnt < 10) {
			cnt++;
			
			for (int i = 0, size = queue.size(); i < size; i++) {
				// queue에서 구슬 꺼내기
				Marble cur = queue.poll();
				
				for (int j = 0; j < 4; j++) {
					// 구슬 객체 복사
					Marble next = cur.clone();
					
					// 이동가능한 만큼 구슬 이동
					next.moveAll(dr[j], dc[j], map);
					
					// 파란 구슬 도착점 도달 시 continue
					if(next.blue.equals(end)) continue;
					
					// 빨간 구슬 도착점 도달 시 횟수 리턴
					if(next.red.equals(end)) return cnt;
					
					// 구슬 방문 체크, 성공 시 queue에 넣기
					if(next.visit(visited)) {
						queue.offer(next);
					}
				}
			}
		}
		
		return -1;
	}
	
	static void print(char[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static public void print(int cnt, char[][] map) {
		System.out.println("횟수 : " + cnt);
		print(map);
	}
	
	static class Marble {
		Point red, blue;
		
		public Marble() {
			this.red = new Point();
			this.blue = new Point();
		}
		
		// 이동가능한 만큼 구슬 이동
		public void moveAll(int dr, int dc, char[][] map) {
			boolean redOk = false;
			boolean blueOk = false;
			map[red.r][red.c] = WALL;
			map[blue.r][blue.c] = WALL;
			while(!(redOk && blueOk)) {
				redOk = move(this.red, dr, dc, map);
				blueOk = move(this.blue, dr, dc, map);
			}
			map[red.r][red.c] = GROUND;
			map[blue.r][blue.c] = GROUND;
		}
		
		// 구슬이 이동 가능하면 한 칸 움직이기
		private boolean move(Point point, int dr, int dc, char[][] map) {
			
			// 도착점 도달 하면 true
			if(point.equals(end)) return true;
			
			// 다음 지점 ground일 때 move
			if(map[point.r + dr][point.c + dc] == GROUND) {
				map[point.r][point.c] = GROUND;
				point.r += dr;
				point.c += dc;
				
				// 도착지 도달 시 중단
				if(point.equals(end)) return true;
				
				map[point.r][point.c] = WALL;
				return false;
			} 
			
			return true;
		}

		// visit 체크 성공 -> true, 실패 -> false
		public boolean visit(boolean[][][][] visited) {
			if(visited[red.r][red.c][blue.r][blue.c]) return false;
			visited[red.r][red.c][blue.r][blue.c] = true;
			return true;
		}
		
		// red, blue 각각의 r, c값을 복사한 새로운 객체 리턴
		public Marble clone() {
			Marble newMarble = new Marble();
			newMarble.red.r = this.red.r;
			newMarble.red.c = this.red.c;
			newMarble.blue.r = this.blue.r;
			newMarble.blue.c = this.blue.c;
			return newMarble;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Marble [red=").append(red).append(", blue=").append(blue).append("]");
			return builder.toString();
		}
		
	}
	
	static class Point {
		int r, c;
		public Point() {}
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Point [r=").append(r).append(", c=").append(c).append("]");
			return builder.toString();
		}
		
		
	}
}
