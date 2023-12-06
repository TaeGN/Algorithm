package programmers.lv2.no.시소_짝꿍;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    public long solution(int[] weights) {
    	Map<Integer, Integer> weightMap = new HashMap<>();
    	for(int weight : weights) {
    		weightMap.compute(weight, (k, v) -> v == null ? 1 : v + 1);
    	}
    	
    	long result = 0, cnt;
    	int weight;
    	for(Map.Entry<Integer, Integer> entry : weightMap.entrySet()) {
    		weight = entry.getKey();
    		cnt = entry.getValue();
    		result += cnt * (cnt - 1) / 2;
    		if(weight % 2 == 0) result += weightMap.getOrDefault(weight * 3 / 2, 0) * cnt;
    		if(weight % 3 == 0) result += weightMap.getOrDefault(weight * 4 / 3, 0) * cnt;
    		result += weightMap.getOrDefault(weight * 2, 0) * cnt;
    	}
        return result;
    }
}
