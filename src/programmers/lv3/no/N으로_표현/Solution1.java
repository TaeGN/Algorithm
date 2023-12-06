package programmers.lv3.no.N으로_표현;

import java.util.Arrays;

public class Solution1 {
    public int solution(int N, int number) {
    	final int INF = Integer.MAX_VALUE >> 2;
        int[] D = new int[number + 1];
        Arrays.fill(D, INF);
        int i = N;
        int cnt = 0;
        while(i <= number) {
        	cnt++;
        	for(int j = i, num = cnt; j <= number; j += i) {
        		D[i] = ++cnt;
        	}
        	i = i * 10 + N;
        }
        D[N] = 1;
        D[1] = 2;
        for()
        
        return 1;
    }
}
