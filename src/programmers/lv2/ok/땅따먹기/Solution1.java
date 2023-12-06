package programmers.lv2.ok.땅따먹기;

import java.util.Arrays;

public class Solution1 {
	public static void main(String[] args) {
		int[][] land = {
				{1,2,3,5},
				{5,6,7,8},
				{4,3,2,1}
		};
		System.out.println(Solution(land));
	}

	private static int Solution(int[][] land) {
		int[] sumArr = new int[4];
		int max1, max2, idx1, idx2;
		
		for(int[] row : land) {
			// sumArr에서 1,2번째로 큰 값과 인덱스 구하기
			max1 = max2 = 0;
			idx1 = idx2 = 0;
			for(int i = 0; i < 4; i++) {
				if(max1 < sumArr[i]) {
					if(max2 < max1) {
						max2 = max1;
						idx2 = idx1;
					}
					max1 = sumArr[i];
					idx1 = i;
				} else {
					if(max2 < sumArr[i]) {
						max2 = sumArr[i];
						idx2 = i;
					}
				}
			}
			
			// sumArr에 현재 row의 각 col값과 지금까지의 sumArr의 1,2번째 큰 값 더하기
			for(int i = 0; i < 4; i++) {
				if(i == idx1) sumArr[i] = row[i] + max2;
				else sumArr[i] = row[i] + max1;
			}
		}
		Arrays.sort(sumArr);
		return sumArr[3];
	}
}
