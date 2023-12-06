package programmers.lv3.no.스티커_모으기;

public class Solution1 {
	int all, minCut;
    public int solution(int sticker[]) {
    	int len = sticker.length;
    	int sum = 0, num;
    	if(len % 2 == 0) {
    		for(int i = 0; i < len; i++) {
    			num = sticker[i];
    			all += num;
    			if(i % 2 == 0) sum += num;
    		}
    		minCut = Math.min(sum, all - sum);
    	} else {
    		int sum1 = 0, sum2 = 0;
    		for(int i = 0, end = len / 2; i < end; i++) {
    			sum += sticker[i * 2];
    			sum1 += sticker[i * 2 + 1];
    			sum2 += sticker[i * 2 + 2];
    		}
    		minCut = Math.min(sum, Math.min(sum1, sum2));
    	}
    	
    	getMaxSum(0, len - 2, 0, sticker);
    	getMaxSum(1, len - 2, sticker[len - 1], sticker);

        return all - minCut;
    }
    
	private void getMaxSum(int s, int e, int sum, int[] sticker) {
		if(sum >= minCut) return;
		if(s >= e) {
			if(s == e) sum += sticker[s];
			minCut = Math.min(minCut, sum);
			return;
		}
		getMaxSum(s + 2, e, sum + sticker[s], sticker);
		if(s + 1 < e) getMaxSum(s + 3, e, sum + sticker[s] + sticker[s + 1], sticker);
	}
}
