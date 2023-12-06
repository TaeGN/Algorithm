package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_돌던지기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	}
	
	static class Board {
		Node[] heads;
		public Board(int C) {
			this.heads = new Node[C];
			for (int i = 0; i < C; i++) {
				this.heads[i] = new Node();
			}
		}
	}
	
	static class Node {
		int row;
		Node next;
		public void set(int row) {
			this.row = row;
		}
	}
	
	static class Pool {
		Node[] nodePool;
		int nodeIdx;
		public Pool(int size) {
			this.nodePool = new Node[size];
			for (int i = 0; i < size; i++) {
				this.nodePool[i] = new Node();
			}
			this.nodeIdx = -1;
		}
		public Node getNode(int row) {
			Node node = nodePool[++this.nodeIdx];
			node.set(row);
			return node;
		}
	}
}
