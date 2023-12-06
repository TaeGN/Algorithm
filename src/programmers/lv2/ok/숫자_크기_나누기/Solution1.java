package programmers.lv2.ok.숫자_크기_나누기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {
	public static void main(String[] args) {
        int[] arrayA = {14, 35, 119};
        int[] arrayB = {18, 30, 102};
        int answer = Solution(arrayA, arrayB);
        System.out.println(answer);
	}

	private static int Solution(int[] arrayA, int[] arrayB) {
		Arrays.sort(arrayA);
		Arrays.sort(arrayB);
		
		// 최대공약수 구하기
		int a = GetMaxCommonFactor(arrayA);
		int b = GetMaxCommonFactor(arrayB);
		
		List<Integer> aList = GetFactors(a);
		List<Integer> bList = GetFactors(b);
		System.out.println(aList);
		System.out.println(bList);
		
		int result = 0;
		int i = 0;
		int j = 0;
		// a, b의 약수들 중 큰 수부터 판별
		while(i < aList.size() && j <bList.size()) {
			boolean tf = true;
			a = aList.get(i);
			b = bList.get(j);
			if(a >= b) {
				for(int e : arrayB) {
					if(e % a == 0) {
						tf = false;
						break;
					}
				}
				if(tf) {
					return a;
				}
				i++;
			} else {
				for(int e : arrayA) {
					if(e % b == 0) {
						tf = false;
						break;
					}
				}	
				if(tf) {
					return b;
				}
				j++;
			}
		}
		
		return 0;
	}

	private static int GetMaxCommonFactor(int[] array) {
		// array에서 가장 작은 수의 약수 구하기
		List<Integer> list = GetFactors(array[0]);
		for(int factor : list) {
			boolean tf = true;
			for(int num : array) {
				if(num % factor != 0) {
					tf = false;
					break;
				}
			}
			if(tf) {
				return factor;
			}
		}
		return 0;
	}

	private static List<Integer> GetFactors(int num) {
		List<Integer> list = new ArrayList<>();
		for(int i = 1; i <= Math.sqrt(num); i++) {
			if(num % i == 0) {
				if(num != i * i) {
					list.add(num / i);
				}
				list.add(i);
			}
		}
		list.sort((a,b) -> b - a);
		return list;
	}
}
