package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_트리의순회 {
	
	static int N;
	static int[] inOrder, postOrder;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		String[] input = br.readLine().split(" ");
		inOrder = new int[N];
		for (int i = 0; i < N; i++) {
			inOrder[i] = Integer.parseInt(input[i]);
		}
		input = br.readLine().split(" ");
		postOrder = new int[N];
		for (int i = 0; i < N; i++) {
			postOrder[i] = Integer.parseInt(input[i]);
		}
		
		preOrder(0, N - 1, 0, N - 1);
		
		br.close();
		System.out.println(sb);
	}
	
	public static void preOrder(int inStart, int inEnd, int postStart, int postEnd) {
		if(postEnd < 0 || postEnd >= N) return;
		
		int root = postOrder[postEnd];
		sb.append(root).append(" ");
		
		// leaf node return
		if(inStart == inEnd) return;
		
		int idx = inEnd;
		while(inOrder[idx] != root) idx--;
		int rightSize = inEnd - idx;
		int leftSize = idx - inStart;
		
		// left node
		if(leftSize > 0) {
			preOrder(inStart, idx - 1, postStart, postStart + leftSize - 1);
		}
		// right node
		if(rightSize > 0) {
			preOrder(idx + 1, inEnd, postEnd - rightSize, postEnd - 1);
		}
	}	
}
