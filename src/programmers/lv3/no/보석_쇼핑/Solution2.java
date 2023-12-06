package programmers.lv3.no.보석_쇼핑;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {
	public static void main(String[] args) {
		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		System.out.println(solution(gems));
	}
    public static int[] solution(String[] gems) {
    	Map<String, List<Integer>> jewelMap = new HashMap<>();
    	int idx = 0;
    	for(String gem : gems) {
    		jewelMap.compute(gem, (k, v) -> v == null ? new ArrayList<>() : v).add(idx++);
    	}
    	jewelCnt = jewelMap.size();
    	List<Integer>[] jewelArr = new List[jewelCnt];
    	idx = 0;
    	for(List<Integer> list : jewelMap.values()) {
    		jewelArr[idx++] = list;
    	}
    	System.out.println(jewelCnt);
    	combi(0, Integer.MAX_VALUE, 0, jewelArr);
        return new int[] {minIdx + 1, maxIdx + 1};
    }
    
    static int jewelCnt;
    static int min = Integer.MAX_VALUE;
    static int minIdx, maxIdx;
    static private void combi(int cnt, int minNum, int maxNum, List<Integer>[] jewelArr) {
    	if(min <= maxNum - minNum) return;
    	
    	if(cnt == jewelCnt) {
    		min = maxNum - minNum;
    		minIdx = minNum;
    		maxIdx = maxNum;
    		return;
    	}
    	
    	List<Integer> cur = jewelArr[cnt];
    	for(int num : cur) {
    		combi(cnt + 1, Math.min(minNum, num), Math.max(maxNum, num), jewelArr);
    	}
	}

}
