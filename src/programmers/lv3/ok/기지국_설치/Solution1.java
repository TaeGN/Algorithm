package programmers.lv3.ok.기지국_설치;

public class Solution1 {
    public int solution(int n, int[] stations, int w) {
    	int start = 0, end = 0, result = 0, wLen = w * 2 + 1;
        for(int station : stations) {
        	end = station - w - 1;
        	if(end - start > 0) result += (end - start - 1) / wLen + 1;
        	start = station + w;
        }
        end = n;
        if(end - start > 0) result += (end - start - 1) / wLen + 1;
        return result;
    }
}
