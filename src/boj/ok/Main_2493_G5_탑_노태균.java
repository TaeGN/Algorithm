package boj.ok;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_2493_G5_탑_노태균 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] tArr = new int[N + 1]; 
		int k = 0;
		for(String s : br.readLine().split(" ")) {
			tArr[++k] = Integer.parseInt(s);
		}
		br.close();
		
		Deque<Integer> hStack = new ArrayDeque<>();
		Deque<Integer> idxStack = new ArrayDeque<>();
		
		hStack.push(Integer.MAX_VALUE);
		idxStack.push(0);
		
		int current;
		
		for(int i = 1; i <= N; i++) {
			current = tArr[i];
			while(hStack.peek() < current) {
				hStack.pop();
				idxStack.pop();
			}
			
			if(hStack.peek() == current) {
				bw.append(idxStack.pop() + " ");
				idxStack.push(i);
			} else {
				bw.append(idxStack.peek() + " ");
				hStack.push(current);
				idxStack.push(i);
			}
		}
		bw.close();
	}
}
