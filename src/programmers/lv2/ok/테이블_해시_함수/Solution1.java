package programmers.lv2.ok.테이블_해시_함수;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {
	public static void main(String[] args) {
		int[][] data = {
				{2,2,6},{1,5,10},{4,2,9},{3,8,3}
		};
		int col = 2;
		int row_begin = 2;
		int row_end = 3;
		int result = Solution(data, col, row_begin, row_end);
		System.out.println(result);
	}

	private static int Solution(int[][] data, int col, int row_begin, int row_end) {
		
		// data 정렬
		List<int[]> list = Arrays.asList(data);
		list.sort((a,b) -> a[col - 1] != b[col - 1] ? a[col - 1] - b[col - 1] : b[0] - a[0]);
		
		// 각 튜플에서 나머지의 합
		int sum = 0;
		int xorSum = 0;
		for(int i = row_begin; i <= row_end; i++) {
			int[] arr = list.get(i - 1);
			for(int element : arr) {
				sum += element % i;
			}
			xorSum = xorSum^sum;
			sum = 0;
		}
		
		return xorSum;
		
//		String bit1 = Integer.toBinaryString(sum.get(0));
//		for(int i = 1; i < sum.size(); i++) {
//			String bit2 = Integer.toBinaryString(sum.get(i));
//			
//			while(bit1.length() != bit2.length()) {
//				if(bit1.length() > bit2.length()) {
//					bit2 = "0" + bit2;
//				} else {
//					bit1 = "0" + bit1;
//				}
//			}
//			
//			System.out.println("bit1 :" + bit1);
//			System.out.println("bit2 :" + bit2);
//			
//			String newBit = "";
//			for(int j = 0; j < bit1.length(); j++) {
//				if(bit1.charAt(j) == bit2.charAt(j)) {
//					newBit += "0";
//				} else {
//					newBit += "1";
//				}
//			}
//			
//			bit1 = newBit;
//			System.out.println("newBit : " + newBit);
//		}
//			
//		
//		return Integer.parseInt(bit1, 2);
	}
}
