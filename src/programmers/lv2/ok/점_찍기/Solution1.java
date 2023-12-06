package programmers.lv2.ok.점_찍기;

public class Solution1 {
	public static void main(String[] args) {
		int k = 2;
		int d = 4;
		long answer = Solution(k, d);
		System.out.println(answer);
	}
	
	private static long Solution(int k, int d) {
		
		double maxPow = Math.pow(d, 2);
		
		long count = 0;
		for(int i = 0; i <= d; i += k) {
			double iPow = Math.pow(i, 2);
			count += (long) Math.sqrt(maxPow - iPow) / k + 1;
		}
		
		return count;
	}
	
	// 시간 초과
//	private static int Solution(int k, int d) {
//		int a = d / k;
//		double maxPow = Math.pow(d, 2);
//		
//		int count = 0;
//		for(int i = 0; i <= d; i += k) {
//			int j = 0;
//			double iPow = Math.pow(i, 2);
//			double sum = Math.pow(i, 2);
//			while(sum <= maxPow && j <= d) {
//				count++;
//				j += k;
//				sum =  iPow + Math.pow(j, 2);
//			}
//		}
//		return count;
//	}

	// 시간 초과
//	private static int Solution(int k, int d) {
//		int count = 0;
//		for(int i = 0; i <= d; i += k) {
//			for(int j = 0; j <= d; j += k) {
//				if(Math.pow(i, 2) + Math.pow(j, 2) <= Math.pow(d, 2)) {
//					count++;
//				}
//			}
//		}
//		return count;
//	}
}
