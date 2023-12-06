package swea.b형특강.b형문제.p8물류허브;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

class UserSolution_pass_t79 {
	
	final int MAX_CITY = 600;
	final int MAX_LOAD = 1450;
	final int INF = Integer.MAX_VALUE >> 2;
	ArrayList<Node>[] forwardLoad, reverseLoad;
	Map<Integer, Integer> cityMap;
	Queue<Node> queue;
	int[] forwardDistance, reverseDistance;
	Node[] nodePool;
	int nodePoolIdx;
	int cityIdx;
	
	public UserSolution_pass_t79() {
		this.forwardLoad = new ArrayList[this.MAX_CITY];
		this.reverseLoad = new ArrayList[this.MAX_CITY];
		for (int i = 0; i < this.MAX_CITY; i++) {
			this.forwardLoad[i] = new ArrayList<Node>();
			this.reverseLoad[i] = new ArrayList<Node>();
		}
		this.forwardDistance = new int[MAX_CITY];
		this.reverseDistance = new int[MAX_CITY];
		
		this.cityMap = new HashMap<>(MAX_CITY);
		this.queue = new ArrayDeque<>();
		
		this.nodePool = new Node[MAX_LOAD * 2];
		for (int i = 0; i < MAX_LOAD * 2; i++) {
			this.nodePool[i] = new Node();
		}
	}
	
	class Node implements Comparable<Node> {
		int idx, weight;
		
		public Node() {}
		public Node(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}

		public Node set(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
			return this;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public int init(int N, int sCity[], int eCity[], int mCost[]) {
		this.cityMap.clear();
		this.cityIdx = -1;
		this.nodePoolIdx = -1;
		
		for (int i = 0; i < N; i++) {
			// city 정보 없으면 map에 넣기
			if(!cityMap.containsKey(sCity[i])) {
				cityMap.put(sCity[i], ++this.cityIdx);
				this.forwardLoad[this.cityIdx].clear();
				this.reverseLoad[this.cityIdx].clear();
			}
			if(!cityMap.containsKey(eCity[i])) {
				cityMap.put(eCity[i], ++this.cityIdx);
				this.forwardLoad[this.cityIdx].clear();
				this.reverseLoad[this.cityIdx].clear();
			}
			this.add(sCity[i], eCity[i], mCost[i]);
		}
		
		return this.cityIdx + 1;
	}

	public void add(int sCity, int eCity, int mCost) {
		sCity = this.cityMap.get(sCity);
		eCity = this.cityMap.get(eCity);
		
		Node forward = this.nodePool[++this.nodePoolIdx].set(eCity, mCost);
		Node reverse = this.nodePool[++this.nodePoolIdx].set(sCity, mCost);
		
		this.forwardLoad[sCity].add(forward);
		this.reverseLoad[eCity].add(reverse);
	}

	public int cost(int mHub) {
		mHub = this.cityMap.get(mHub);
		return dijkstra(mHub, this.forwardLoad, this.forwardDistance)
				+ dijkstra(mHub, this.reverseLoad, this.reverseDistance);
	}
	
	int dijkstra(int mHub, ArrayList<Node>[] load, int[] distance) {
		Arrays.fill(distance, this.INF);
		distance[mHub] = 0;
		Queue<Node> queue = this.queue;
		queue.clear();
		queue.offer(new Node(mHub, 0));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			// 유효한 값이 아니면 다시 뽑기
			if(node.weight > distance[node.idx]) continue;
			
			for (Node next : load[node.idx]) {
				if(distance[next.idx] > node.weight + next.weight) {
					distance[next.idx] = node.weight + next.weight;
					queue.offer(new Node(next.idx, distance[next.idx]));
				}
			}
		}
		
		int res = 0;
		for (int i = 0; i <= this.cityIdx; i++) {
			res += distance[i];
		}
		return res;
	}
}