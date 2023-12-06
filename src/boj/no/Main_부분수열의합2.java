package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main_부분수열의합2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int S = Integer.parseInt(input[1]);
		
		Map<Integer, Long> countMap = new HashMap<>(1000);
		Set<Integer> newCount = new HashSet<>(40);
		
		input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(input[i]);
			for (int key : countMap.keySet()) {
				Integer newKey = key + num;
				if(countMap.containsKey(newKey)) {
					countMap.put(newKey, countMap.get(newKey) + countMap.get(key));
				} else {
					newCount.add(newKey);
				}
			}
			if(countMap.containsKey(num)) {
				countMap.put(num, countMap.get(num) + countMap.get(num));
			} else {
				newCount.add(num);
			}
			for (Integer key : newCount) {
				countMap.put(key, 1L);
			}
			newCount.clear();
			System.out.println(countMap);
		}
		
		System.out.println(countMap.get(S));
	}
}
	
