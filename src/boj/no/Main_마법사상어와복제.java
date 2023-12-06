package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_마법사상어와복제 {
	
	static final int N = 4;
	static int M, S, maxKillCnt;
	static int[] moveSharkD = new int[3];
	static LinkedList<Fish> fishList;
	static LinkedList<Fish> copyList;
	// ↙, ←, ↖, ↑, ↗, →, ↘, ↓ 
	static int[] dr = {1,0,-1,-1,-1,0,1,1};
	static int[] dc = {-1,-1,-1,0,1,1,1,0};
	static int[] sharkDr = {-1,0,1,0};
	static int[] sharkDc = {0,-1,0,1};
	static ArrayList<Node>[][] map;
	static boolean[][][] smell = new boolean[3][N + 1][N + 1];
	static Fish shark;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		fishList = new LinkedList<>();
		copyList = new LinkedList<>();
		map = new ArrayList[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j =1; j <= N; j++) {
				map[i][j] = new ArrayList<Node>();
			}
		}
		
		M = Integer.parseInt(input[0]);
		S = Integer.parseInt(input[1]);
		
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int fx = Integer.parseInt(input[0]);
			int fy = Integer.parseInt(input[1]);
			int d = Integer.parseInt(input[2]) % 8;
			// fish 만들기
			Fish fish = new Fish(fx, fy, d);
			// fishList에 추가
			fishList.add(fish);
		}
		
		input = br.readLine().split(" ");
		int fx = Integer.parseInt(input[0]);
		int fy = Integer.parseInt(input[1]);
		// shark 만들기
		shark = new Fish(fx, fy, 0);
		
		for (int i = 1; i <= S; i++) {
			// 복제
			copyList.init();
			copyList.copyOf(fishList);
			
			// 물고기 이동
			moveFish();
			
			// 물고기 맵에 표시
			setMap();
			
			// 상어 이동
			maxKillCnt = 0;
			moveShark(shark.fx, shark.fy, 0, 0, new int[3]);
			kill();
		}
		
	}
	
	private static void kill() {
		int r = shark.fx;
		int c = shark.fy;
		int rr, cc;
		for (int d : moveSharkD) {
			rr = r + sharkDr[d];
			cc = c + sharkDc[d];
			ArrayList<Fish> list = map[rr][cc];
			for (Fish fish : list) {
				fish.remove();
			}
		}
	}

	private static void setMap() {
		initMap();
		LinkedList<Fish> list = fishList;
		for(Node<Fish> node = list.head.next; node != list.tail; node = node.next) {
			Fish curFish = node.val;
			map[curFish.fx][curFish.fy].addLast(curFish);
		}
	}

	private static void initMap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j].clear();
			}
		}
	}

	private static void moveShark(int r, int c, int cnt, int killCnt, int[] direction) {
		if(cnt == 3) {
			if(maxKillCnt < killCnt) {
				maxKillCnt = killCnt;
				copyArr(moveSharkD, direction);
			}
			return;
		}
		
		int rr, cc;
		for (int i = 0; i < 4; i++) {
			rr = r + sharkDr[i];
			cc = c + sharkDc[i];
			// boundary check
			if(!isValid(rr, cc)) continue;
			// 왔던길 다시 가는 경우 배제
			if(cnt > 0 && Math.abs(direction[cnt - 1] - i) == 2) continue;
			direction[cnt] = i;
			moveShark(rr, cc, cnt + 1, killCnt + map[rr][cc].size(), direction);
		}
	}

	private static void copyArr(int[] moveSharkD, int[] direction) {
		for (int i = 0; i < 3; i++) {
			moveSharkD[i] = direction[i];
		}
	}

	private static void moveFish() {
		LinkedList<Fish> list = fishList;
		boolean[][] smellArr = smell[0];
		for(Node<Fish> node = list.head.next; node != list.tail; node = node.next) {
			Fish curFish = node.val;
			for (int i = 0; i < 8; i++) {
				int d = (curFish.d + i) % 8;
				int r = curFish.fx + dr[d];
				int c = curFish.fy + dc[d];
				
				if(!isValid(r, c)) continue;
				
				if(smellArr[r][c]) continue;
				
				Fish newFish = new Fish(r, c, d);
				node.val = newFish;
				break;
			}
		}
	}

	private static boolean isValid(int r, int c) {
		return r <= N && c <= N && r >= 1 && c >= 1;
	}

	static class LinkedList<T> {
		Node<T> head;
		Node<T> tail;
		int size;
		
		public LinkedList() {
			this.head = new Node<T>();
			this.tail = new Node<T>();
		}

		public void copyOf(LinkedList<T> list) {
			for(Node<T> node = list.head.next; node != list.tail; node = node.next) {
				this.add(node.val);
			}
		}

		public void init() {
			connect(this.head, this.tail);
		}
		
		public void connect(Node<T> prev, Node<T> next) {
			prev.next = next;
			next.prev = prev;
		}
		
		public void add(T t) {
			Node<T> newNode = new Node<>(t);
			Node<T> prev = this.tail.prev;
			this.connect(prev, newNode);
			this.connect(newNode, this.tail);
			this.size++;
		}
		
	}
	
	static class Node<T> {
		T val;
		Node<T> prev;
		Node<T> next;
		
		public Node() {}
		public Node(T t) {
			this.val = t;
		}
	}
	
	static class Fish {
		int fx, fy, d;
		
		public Fish(int fx, int fy, int d) {
			super();
			this.fx = fx;
			this.fy = fy;
			this.d = d;
		}
	}
}
