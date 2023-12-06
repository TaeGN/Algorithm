package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main_23291_어항_정리 {
	static List<Integer>[] fishbowl;
	static List<Integer> minIdx = new ArrayList<>();
	static int N, K, min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		fishbowl = new LinkedList[N];
		input = br.readLine().split(" ");
		min = Integer.MAX_VALUE;
		int num = 0;
		for(int i = 0; i < N; i++) {
			num = Integer.parseInt(input[i]);
			if(min > num) {
				min = num;
				minIdx.clear();
				minIdx.add(i);
			} else if(min == num) minIdx.add(i);
			fishbowl[i] = new LinkedList<>();
			fishbowl[i].add(num);
		}
		
		addFish();
		rotateFishbowl();
		System.out.println(Arrays.toString(fishbowl));
	}
	
	// 공중부양 -> 회전 -> 위에 올려놓기
	private static void rotateFishbowl() {
		int i = 2, sum = 2, idx = 1;
		fishbowl[1].add(fishbowl[0].remove(0));
		a:while(sum + i <= N) {
			for(int j = 0; j < 2; j++) {
				if((sum += i) > N) break a;
				for(int l = 0; l < i + j - 1; l++) {
					for(int k = 0; k < i; k++) {
						fishbowl[sum + l].add(fishbowl[sum - k - 1].remove(0));
					}
				}
			}
			i++;
		}
		
	}

	// 물고기의 수가 최소인 어항들에 +1
	private static void addFish() {
		for(int idx : minIdx) {
			fishbowl[idx].set(0, fishbowl[idx].get(0) + 1);
		}
		minIdx.clear();
		min = Integer.MAX_VALUE;
	}
	

}
