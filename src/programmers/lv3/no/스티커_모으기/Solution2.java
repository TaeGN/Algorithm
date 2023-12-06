package programmers.lv3.no.스티커_모으기;

public class Solution2 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] {14, 6, 5, 11, 3, 9, 2, 10}));
		System.out.println(solution(new int[] {1, 3, 2, 5, 4}));
	}
	static int minCut;
    public static int solution(int sticker[]) {
    	int len = sticker.length;
    	int sum1 = 0, sum2 = 0;
		for(int i = 0, end = len / 2; i < end; i++) {
			sum1 += sticker[i * 2 + 1];
			sum2 += sticker[i * 2];
		}
		minCut = Math.min(sum1, sum2);
    	
    	System.out.print(minCut + " ");
    	
    	getMaxSum(1, len - 2, sticker[len - 1], sticker, false);
    	getMaxSum(2, len - 1, sticker[0], sticker, false);
    	if(len % 2 == 1) getMaxSum(3, len - 1, sticker[1], sticker, true);

        return sum1 + sum2 - minCut;
    }
    
	private static void getMaxSum(int s, int e, int sum, int[] sticker, boolean tf) {
		if(sum >= minCut) return;
		if(s >= e) {
			if(s == e) {
				sum += sticker[s];
				if(tf) sum += sticker[0];
			}
			minCut = Math.min(minCut, sum);
			return;
		}
		if(tf || s + 1 != e) getMaxSum(s + 3, e, sum + sticker[s] + sticker[s + 1], sticker, tf);
		getMaxSum(s + 2, e, sum + sticker[s], sticker, tf);
	}
}
