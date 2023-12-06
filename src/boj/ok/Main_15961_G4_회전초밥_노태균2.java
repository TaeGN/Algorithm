package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_15961_G4_회전초밥_노태균2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int d = Integer.parseInt(input[1]);
		int k = Integer.parseInt(input[2]);
		int c = Integer.parseInt(input[3]);
		
		k = Math.min(k, N);
		Map<Integer, Integer> map = new HashMap<>();
		int[] sushi = new int[k];
		int num = 0, prev = 0;
		for (int i = 0; i < k; i++) {
			num = Integer.parseInt(br.readLine());
			sushi[i] = num;
			map.compute(num, (key, v) -> v == null ? 1 : v + 1);
		}
		
		int[] prevSushi = sushi.clone();
		int maxCnt = map.size();
		int curCnt = maxCnt;
		for (int i = k; i < N + k - 1; i++) {
			prev = sushi[i % k];
			map.put(prev, map.get(prev) - 1);
			if(map.get(prev) == 0) curCnt--;
			if(i < N) sushi[i % k] = num = Integer.parseInt(br.readLine());
			else num = prevSushi[i - N];
			map.compute(num, (key, v) -> v == null ? 1 : v + 1);
			if(map.get(num) == 1) {
				maxCnt = Math.max(maxCnt, ++curCnt + (map.getOrDefault(c, 0) == 0 ? 0 : 1));
			}
			if(maxCnt == d) break;
		}
		
		for (int i = 0; i < k - 1; i++) {
			prev = sushi[(i + N) % k];
			map.put(prev, map.get(prev) - 1);
			if(map.get(prev) == 0) curCnt--;
			num = prevSushi[i];
			map.compute(num, (key, v) -> v == null ? 1 : v + 1);
			if(map.get(num) == 1) {
				maxCnt = Math.max(maxCnt, ++curCnt + (map.getOrDefault(c, 0) == 0 ? 0 : 1));
			}
			if(maxCnt == d) break;
		}
		
		System.out.println(maxCnt);
	}
}
