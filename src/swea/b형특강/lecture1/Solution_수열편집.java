package swea.b형특강.lecture1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_수열편집 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int M = Integer.parseInt(input[1]);
			int L = Integer.parseInt(input[2]);
			
			LinkedList list = new LinkedList();
			
			// list에 입력값 add
			for (String str : br.readLine().split(" ")) {
				list.add(Integer.parseInt(str));
			}
			
			// M번의 편집
			int idx, value;
			for (int i = 0; i < M; i++) {
				input = br.readLine().split(" ");
				idx = Integer.parseInt(input[1]);
				switch(input[0]) {
				case "I":
					value = Integer.parseInt(input[2]);
					list.insert(idx, value);
					break;
				case "D":
					list.delete(idx);
					break;
				case "C":
					value = Integer.parseInt(input[2]);
					list.update(idx, value);
					break;
				}
			}
			
			// L번째의 노드 구하기
			Node node = list.get(L);
			sb.append("#").append(tc).append(" ").append(node == null ? -1 : node.value).append("\n");
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
		
		// linkedList의 마지막 위치에 추가
		public void add(int value) {
			Node node = new Node(value);
			this.tail.next = node;
			this.tail = node;
			this.size++;
		}
		
		// linkedList의 idx위치에 추가
		public void insert(int idx, int value) {
			Node prev = get(idx - 1);
			Node node = new Node(value, prev.next);
			prev.next = node;
			this.size++;
		}
		
		// idx에 해당하는 노드 삭제
		public void delete(int idx) {
			Node prev = get(idx - 1);
			Node deleteNode = prev.next;
			prev.next = deleteNode.next;
			if(deleteNode == this.tail) {
				this.tail = prev;
			}
			deleteNode = null;
			this.size--;
		}
		
		// idx에 해당하는 노드의 value값 변경
		public void update(int idx, int value) {
			Node cur = get(idx);
			cur.value = value;
		}
		
		// idx에 해당하는 노드 리턴
		private Node get(int idx) {
			if(idx >= this.size) return null;
			
			Node node = this.head;
			for (int i = 0; i <= idx; i++) {
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
		
		public Node(int value) {
			this.value = value;
		}
		
		public Node(int value, Node next) {
			this.value = value;
			this.next = next;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [value=").append(value).append(", next=").append(next).append("]");
			return builder.toString();
		}
		
	}
}
