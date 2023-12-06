package programmers.lv2.ok.택배_배달과_수거하기;

public class Solution1 {
	public static void main(String[] args) {
		
	}
	
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long result = 0;
        int d = deliveries.length - 1;
        int p = pickups.length - 1;
        int a;
        while(d >= 0 || p >= 0) {
        	a = cap;
        	while(d >= 0 && deliveries[d] == 0) d--;
        	while(p >= 0 && pickups[p] == 0) p--;
        	result += Math.max(d, p) + 1;
        	
        	while(d >= 0 && deliveries[d] < a) {
        		a -= deliveries[d--];
        	}
        	if(d >= 0) deliveries[d] -= a;
        	
        	a = cap;
        	while(p >= 0 && pickups[p] < a) {
        		a -= pickups[p--];
        	}
        	if(p >= 0) pickups[p] -= a;
        }
        
        return result * 2;
    }
}
