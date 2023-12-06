package programmers.lv3.ok.순위;

import java.util.Arrays;

public class Solution2 {
	public static void main(String[] args) {
		
	}
	
    public int solution(int n, int[][] results) {
    	int[][] resultArr = new int[n + 1][n + 1];
    	// 0번째 인덱스에 선수 번호 입력
    	for(int i = 1; i <= n; i++) resultArr[i][0] = i;
    	// 경기 결과 resultArr에 입력
    	for(int[] arr : results) resultArr[arr[0]][arr[1]] = 1;
    	
    	Arrays.sort(resultArr, (o1, o2) -> {
    		int o1Num = o1[0];
    		int o2Num = o2[0];
    		if(o1[o2Num] == 1) return -1;
    		if(o2[o1Num] == 1) return 1;
    		return 0;
    	});
    	
    	
        int answer = 0;
        return answer;
    }
}
