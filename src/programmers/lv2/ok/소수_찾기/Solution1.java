package programmers.lv2.ok.소수_찾기;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution1 {
	public static void main(String[] args) {
		String numbers = "17";
		System.out.println(Solution(numbers));
	}

	private static int Solution(String numbers) {
		N = numbers.length();
		numStr = numbers;
		isSelected = new boolean[N];
		for(R = 1; R <= N; R++) {
			permutation(0, "");
			Arrays.fill(isSelected, false);
		}
		return primeCnt;
	}
	
	static int N, R, primeCnt;
	static boolean[] isSelected;
	static String numStr;
	static Set<Integer> set = new HashSet<>();
	private static void permutation(int cnt, String num) {
		if(cnt == R) {
			int n = Integer.parseInt(num);
			if(set.add(n) && isPrime(n)) primeCnt++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			permutation(cnt + 1, num + numStr.charAt(i));
			isSelected[i] = false;
		}
		
	}

	private static boolean isPrime(int n) {
		if(n < 2) return false;
		for(int i = 2; i * i <= n; i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
