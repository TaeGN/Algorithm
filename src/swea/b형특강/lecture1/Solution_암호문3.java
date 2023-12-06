package swea.b형특강.lecture1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_암호문3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int idx, value, cnt;
		String[] input;
		int T = 10;
		
		for (int tc = 1; tc <= T; tc++) {
			LinkedList list = new LinkedList();
			
			int N = Integer.parseInt(br.readLine());
			input = br.readLine().split(" ");
			
			for (int i = 0; i < N; i++) {
				value = Integer.parseInt(input[i]);
				list.add(value);
			}
			
			int M = Integer.parseInt(br.readLine());
			input = br.readLine().split(" ");
			
			for (int i = 0, m = 0; m < M; m++, i++) {
				switch(input[i]) {
				case "I":
					idx = Integer.parseInt(input[++i]);
					cnt = Integer.parseInt(input[++i]);
					for (int j = 0; j < cnt; j++) {
						value = Integer.parseInt(input[++i]);
						list.insert(idx++, value);
					}
					break;
				case "D":
					idx = Integer.parseInt(input[++i]);
					cnt = Integer.parseInt(input[++i]);
					list.deleteAll(idx, cnt);
					break;
				case "A":
					cnt = Integer.parseInt(input[++i]);
					for (int j = 0; j < cnt; j++) {
						value = Integer.parseInt(input[++i]);
						list.add(value);
					}
					break;
				}
			}
			
			sb.append("#").append(tc);
			Node node = list.head;
			for (int i = 0; i < 10; i++) {
				node = node.next;
				sb.append(" ").append(node.value);
			}
			sb.append("\n");
		}
		br.close();
		System.out.println(sb);
	}
	
	static class LinkedList {
		Node head;
		Node tail;
		int size;
		
		public LinkedList() {
			this.head = new Node(0);
			this.tail = this.head;
		}

		// list의 맨 뒤에 추가
		public void add(int value) {
			Node node = new Node(value);
			this.tail.next = node;
			this.tail = node;
			this.size++;
		}
		
		// idx에 노드 추가
		public void insert(int idx, int value) {
			Node prev = this.get(idx);
			Node newNode = new Node(value, prev.next);
			prev.next = newNode;
			
			// 맨 뒤에 추가시 tail 변경
			if(prev == this.tail) {
				this.tail = newNode;
			}
			this.size++;
		}
		
		// idx부터 cnt개의 노드 삭제
		public void deleteAll(int idx, int cnt) {
			Node prev = this.get(idx);
			Node lastDeleteNode = prev;
			for (int i = 0; i < cnt; i++) {
				lastDeleteNode = lastDeleteNode.next;
			}
			prev.next = lastDeleteNode.next;
			
			// 맨 뒤 삭제시 tail 변경
			if(lastDeleteNode == this.tail) {
				this.tail = prev;
			}
			this.size -= cnt;
		}
		
		// idx에 해당하는 노드 리턴
		public Node get(int idx) {
			Node node = this.head;
			
			for (int i = 0; i < idx; i++) {
				node = node.next;
			}
			
			return node;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("LinkedList [head=").append(head).append(", tail=").append(tail).append(", size=")
					.append(size).append("]");
			return builder.toString();
		}
		
	}
	
	static class Node {
		int value;
		Node next;
		
		public Node(int value, Node next) {
			this.value = value;
			this.next = next;
		}
		
		public Node(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [value=").append(value).append(", next=").append(next).append("]");
			return builder.toString();
		}
		
		
	}
}
