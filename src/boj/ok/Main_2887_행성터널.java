package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_2887_행성터널 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Planet[] xKingdom = new Planet[N];
		Planet[] yKingdom = new Planet[N];
		Planet[] zKingdom = new Planet[N];
		for (int i = 0; i < N; i++) {
			xKingdom[i] = yKingdom[i] = zKingdom[i] = new Planet(br.readLine().split(" "));
		}
		Arrays.sort(xKingdom, (o1, o2) -> Integer.compare(o1.x, o2.x));
		for (int i = 0; i < N; i++) {
			xKingdom[i].xPos = i;
		}
		Arrays.sort(yKingdom, (o1, o2) -> Integer.compare(o1.y, o2.y));
		for (int i = 0; i < N; i++) {
			yKingdom[i].yPos = i;
		}
		Arrays.sort(zKingdom, (o1, o2) -> Integer.compare(o1.z, o2.z));
		for (int i = 0; i < N; i++) {
			zKingdom[i].zPos = i;
		}
		
		Tunnel tunnel = new Tunnel(xKingdom[0], 0);
		PriorityQueue<Tunnel> pq = new PriorityQueue<>();
		pq.offer(tunnel);
		int cnt = 0, nextPos;
		long res = 0;
		while(cnt < N) {
			Tunnel curT = pq.poll();
			Planet curP = curT.planet;
			if(curP.visited) continue;
			curP.visited = true;
			res += curT.distance;
			cnt++;
			nextPos = curP.xPos + 1;
			if(nextPos < N && !xKingdom[nextPos].visited) pq.offer(new Tunnel(curP, xKingdom[nextPos], 0));
			nextPos = curP.xPos - 1;
			if(nextPos >= 0 && !xKingdom[nextPos].visited) pq.offer(new Tunnel(curP, xKingdom[nextPos], 0));
			nextPos = curP.yPos + 1;
			if(nextPos < N && !yKingdom[nextPos].visited) pq.offer(new Tunnel(curP, yKingdom[nextPos], 1));
			nextPos = curP.yPos - 1;
			if(nextPos >= 0 && !yKingdom[nextPos].visited) pq.offer(new Tunnel(curP, yKingdom[nextPos], 1));
			nextPos = curP.zPos + 1;
			if(nextPos < N && !zKingdom[nextPos].visited) pq.offer(new Tunnel(curP, zKingdom[nextPos], 2));
			nextPos = curP.zPos - 1;
			if(nextPos >= 0 && !zKingdom[nextPos].visited) pq.offer(new Tunnel(curP, zKingdom[nextPos], 2));
		}
		br.close();
		System.out.println(res);
	}
	
	static class Tunnel implements Comparable<Tunnel> {
		Planet planet;
		int distance;
		
		public Tunnel(Planet planet, int distance) {
			super();
			this.planet = planet;
			this.distance = distance;
		}

		public Tunnel(Planet cur, Planet next, int pos) {
			this.planet = next;
			setDistance(cur, next, pos);
		}
		
		public void setDistance(Planet cur, Planet next, int pos) {
			switch(pos) {
			case 0:
				this.distance = Math.abs(next.x - cur.x);
				break;
			case 1:
				this.distance = Math.abs(next.y - cur.y);
				break;
			case 2:
				this.distance = Math.abs(next.z - cur.z);
				break;
			}
		}

		@Override
		public int compareTo(Tunnel o) {
			return Integer.compare(this.distance, o.distance);
		}
	}
	
	static class Planet {
		int x, y, z, xPos, yPos, zPos;
		boolean visited;
		
		public Planet(String[] input) {
			super();
			this.x = Integer.parseInt(input[0]);
			this.y = Integer.parseInt(input[1]);
			this.z = Integer.parseInt(input[2]);
		}
	}
}
