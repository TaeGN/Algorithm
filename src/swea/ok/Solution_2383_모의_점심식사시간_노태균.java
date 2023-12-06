package swea.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_2383_모의_점심식사시간_노태균 {
	static int N, M, minTime, stairs[][] = new int[2][2];
	static List<Integer> posList = new ArrayList<>();
	static Person[] people;
	static PriorityQueue<Person> pq1 = new PriorityQueue<>(10, (o1, o2) -> Integer.compare(o1.t1, o2.t1));
	static PriorityQueue<Person> pq2 = new PriorityQueue<>(10, (o1, o2) -> Integer.compare(o1.t2, o2.t2));
	static Queue<Integer> q = new ArrayDeque<>(3);
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		String[] input;
		int idx, num;
		for (int tc = 1; tc <= T; tc++) {
			minTime = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			idx = 0;
			for (int i = 0; i < N; i++) {
				input = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					num = Integer.parseInt(input[j]);
					switch(num) {
					case 0:
						break;
					case 1:
						posList.add(i * N + j);
						break;
					default:
						stairs[idx][0] = i * N + j;
						stairs[idx++][1] = num;
						break;
					}
				}
			}
			M = posList.size();
			people = new Person[M];
			for(int i = 0; i < M; i++) {
				people[i] = new Person(getLength(i, 0), getLength(i, 1));
			}
			
			subSet(0, 0);
			
			sb.append("#").append(tc).append(" ").append(minTime).append("\n");
			posList.clear();
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static void subSet(int idx, int flag) {
		if(idx == M) {
			move(flag);
			return;
		}
		
		subSet(idx + 1, flag | (1 << idx));
		subSet(idx + 1, flag);
	}
	
	static void move(int flag) {
		for (int i = 0; i < M; i++) {
			if((flag & (1 << i)) != 0) pq1.offer(people[i]);
			else pq2.offer(people[i]);
		}
		
		int t1 = getTime(stairs[0][1], pq1, true);
		int t2 = getTime(stairs[1][1], pq2, false);
		minTime = Math.min(minTime, Math.max(t1, t2));
	}
	
	static int getTime(int sLen, PriorityQueue<Person> pq, boolean tf) {
		Person cur;
		int t = 0;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			if(tf) {
				if(t < cur.t1) t = cur.t1;
			} else {
				if(t < cur.t2) t = cur.t2;
			}
			if(q.size() < 3) q.offer(t + sLen);
			else {
				t = Math.max(t, q.poll());
				q.offer(t + sLen);
			}
		}
		while(!q.isEmpty()) {
			t = Math.max(t, q.poll());
		}
		return t;
	}

	private static int getLength(int i, int j) {
		int pos = posList.get(i);
		int sPos = stairs[j][0];
		return Math.abs(pos / N - sPos / N) + Math.abs(pos % N - sPos % N) + 1;
	}
	
	static class Person {
		int t1, t2;

		public Person(int t1, int t2) {
			super();
			this.t1 = t1;
			this.t2 = t2;
		}
	}
}
