package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_버블소트2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Point[] arr = new Point[N];
		
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = new Point(i, Integer.parseInt(input[i]));
		}
		
		br.close();
		
		mergeSort(0, N - 1, arr, new Point[N]);
		
		long res = 0;
		SegmentTree tree = new SegmentTree(N);
		for (int i = 0; i < N; i++) {
			Point point = arr[i];
			res += tree.sum(point.idx);
			tree.add(point);
		}
		
		System.out.println(res);
	}
	
	static class Point {
		int idx, val;
		public Point(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}
	
	static class SegmentTree {
		int[] tree;
		int dataParam, dataSize;
		public SegmentTree(int size) {
			int h = (int) Math.ceil(Math.log(size) / Math.log(2));
			int treeSize = (int) Math.pow(2, h + 1);
			this.tree = new int[treeSize];
			this.dataParam = treeSize / 2;
			this.dataSize = size;
		}
		public void add(Point point) {
			int idx = this.dataParam + point.idx;
			this.tree[idx] = 1;
			while(idx > 1) {
				this.tree[idx /= 2]++;
			}
		}
		public int sum(int left) {
			return this.sum(1, 1, this.dataSize, left, this.dataSize);
		}
		public int sum(int node, int start, int end, int left, int right) {
			if(right < start || end < left) return 0;
			if(left <= start && end <= right) return this.tree[node];
			return sum(getLeftNode(node), start, getMid(start, end), left, right)
					+ sum(getRightNode(node), getMid(start, end) + 1, end, left, right);
		}
		
		private int getLeftNode(int node) {
			return node * 2;
		}
		
		private int getRightNode(int node) {
			return node * 2 + 1;
		}
		
		private int getMid(int start, int end) {
			return (start + end) / 2;
		}
	}
	
	static void mergeSort(int start, int end, Point[] arr, Point[] temp) {
		if(start < end) {
			int mid = (start + end) / 2;
			mergeSort(start, mid, arr, temp);
			mergeSort(mid + 1, end, arr, temp);
			merge(start, mid, end, arr, temp);
		}
	}
	
	static void merge(int start, int mid, int end, Point[] arr, Point[] temp) {
		int idx1 = start;
		int idx2 = mid + 1;
		int idx3 = 0;
		
		while(idx1 <= mid && idx2 <= end) {
			if(arr[idx1].val > arr[idx2].val) temp[idx3++] = arr[idx2++];
			else temp[idx3++] = arr[idx1++];
		}
		
		while(idx1 <= mid) {
			temp[idx3++] = arr[idx1++];
		}
		
		while(idx2 <= end) {
			temp[idx3++] = arr[idx2++];
		}
		
		for (int i = 0; i < idx3; i++) {
			arr[start + i] = temp[i];
		}
	}
}
