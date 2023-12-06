package boj.no;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main_버블소트 {
	static long res;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		final String path = "src/boj/res/버블소트input.txt";
		
		try(FileWriter fw = new FileWriter(path);
		BufferedWriter bw = new BufferedWriter(fw);) {
			for (int i = 0; i < 100000; i++) {
				bw.append(String.valueOf((int) (Math.random() * 100000))).append(" ");
			}
			bw.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
//		FileInputStream fis = new FileInputStream(path);
//		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
//		int N = Integer.parseInt(br.readLine());
//		Point[] arr = new Point[N];
//		
//		String[] input = br.readLine().split(" ");
//		for (int i = 0; i < N; i++) {
//			arr[i] = new Point(i, Integer.parseInt(input[i]));
//		}
//		
//		br.close();
//		
//		mergeSort(0, N - 1, arr, new Point[N]);
//		
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i].val + " ");
//		}
//		System.out.println();
//		System.out.println(res);
	}
	
	static class Point {
		int idx, val;
		public Point(int idx, int val) {
			this.idx = idx;
			this.val = val;
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
			if(arr[idx1].val > arr[idx2].val) {
				res += idx2 - idx1;
				temp[idx3++] = arr[idx2++];
			}
			else {
				temp[idx3++] = arr[idx1++];
			}
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
