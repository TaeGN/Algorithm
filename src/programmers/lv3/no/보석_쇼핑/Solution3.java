package programmers.lv3.no.보석_쇼핑;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution3 {
    public int[] solution(String[] gems) {
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
    	combi(0, Integer.MAX_VALUE, 0, jewelArr);
        return new int[] {minNum, maxNum};
    }
    
    int jewelCnt;
    int min = Integer.MAX_VALUE;
    int minNum, maxNum;
    private void combi(int cnt, int minNum, int maxNum, List<Integer>[] jewelArr) {
    	if(min <= maxNum - minNum) return;
    	
    	if(cnt == jewelCnt) {
    		min = maxNum - minNum;
    		this.minNum = minNum;
    		this.maxNum = maxNum;
    	}
    	
    	List<Integer> cur = jewelArr[cnt];
    	for(int num : cur) {
    		combi(cnt + 1, Math.min(minNum, num), Math.max(maxNum, num), jewelArr);
    	}
	}

}
