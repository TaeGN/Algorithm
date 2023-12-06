package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main_20298_파인애플피자2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		int[] piece = new int[N];
		int n = 0;
		for(String s : br.readLine().split(" ")) {
			piece[n++] = Integer.parseInt(s);
		}
		n = 0;
		int[] customer = new int[K];
		for(String s : br.readLine().split(" ")) {
			customer[n++] = Integer.parseInt(s);
		}
		
		int[] fail = new int[K];
		for (int i = 1, j = 0; i < K; i++) {
			while(j > 0 && customer[i] != customer[j]) {
				j = fail[j - 1];
			}
			
			if(customer[i] == customer[j]) fail[i] = ++j;
		}
		System.out.println(Arrays.toString(piece));
		System.out.println(Arrays.toString(customer));
		System.out.println(Arrays.toString(fail));
		int ii, k, idx, cnt = 0;
		Map<Integer, int[]> map = new HashMap<>();
		for (int i = 0, j = 0; i < N + K - 1; i++) {
			ii = i % N;
			while(j > 0 && map.containsKey(customer[j]) && piece[ii] != map.get(customer[j])[0]) {
				k = j - 1;
				j = fail[j - 1];
				for(; k >= j; k--) {
					idx = customer[k];
					if(!map.containsKey(idx)) continue;
					if(map.get(idx)[1] == 1) map.remove(idx);
					else map.get(idx)[1] -= 1;
				}
			}
			
			if(!map.containsKey(customer[j]) || piece[ii] == map.get(customer[j])[0]) {
				if(!map.containsKey(customer[j])) map.put(customer[j], new int[] {piece[ii], 1});
				else map.get(customer[j])[1]++;
				if(++j == K) {
					cnt++;
					k = j;
					j = fail[j - 1];
					for(; k >= j; k--) {
						idx = customer[k];
						if(!map.containsKey(idx)) continue;
						if(map.get(idx)[1] == 1) map.remove(idx);
						else map.get(idx)[1] -= 1;
					}
					continue;
				}
				System.out.println(1 + " : " + Arrays.toString(map.get(1)));
				System.out.println(2 + " : " + Arrays.toString(map.get(2)));
			}
		}
		
		System.out.println(cnt);
	}
}
