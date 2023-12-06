package programmers.lv2.ok.두개_이하로_다른_비트;

import java.util.Arrays;

public class Solution1 {
	public static void main(String[] args) {
		long[] numbers = {2,7};
		long[] answer = Solution(numbers);
		System.out.println(Arrays.toString(answer));
	}

	private static long[] Solution(long[] numbers) {
		long[] result = new long[numbers.length];
		for(int i = 0; i < numbers.length; i++) {
			result[i] = minFun(numbers[i]);
		}
		return result;
	}

	private static long minFun(long number) {
		String bitNum = Long.toBinaryString(number);
		
		int i = bitNum.length() - 1;
		
		if(bitNum.charAt(i) == '0') {
			return number + 1L;
		}
		
		while(bitNum.charAt(i) == '1') {
			i--;
			if(i < 0) {
				String result = "10" + bitNum.substring(1, bitNum.length());
				return Long.parseLong(result, 2);
			}
		}
		
		String result = bitNum.substring(0, i) + "10" + bitNum.substring(i + 2, bitNum.length());
		return Long.parseLong(result, 2);
	}
	
	

//	private static long minFun(long number) {
//		String bitNum = Long.toBinaryString(number);
//		
//		if(bitNum.charAt(bitNum.length() - 1) == '0') {
//			return number + 1;
//		}
//		
//		int i = bitNum.length() - 1;
//		while(bitNum.charAt(i--) != '0') {
//			if(i < 0) {
//				String result = "10" + bitNum.substring(1,bitNum.length());
//				return Long.parseLong(result, 2);
//			}
//		}
//		
//		String result = bitNum.substring(0, i) + "10" + bitNum.substring(i + 2, bitNum.length());
//		System.out.println(bitNum);
//		System.out.println(result);
//		
//		return Long.parseLong(result, 2);
//	}
}
