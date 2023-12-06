package programmers.lv3.no.보석_쇼핑;

import java.util.HashMap;
import java.util.Map;

public class Solution4 {
    public int[] solution(String[] gems) {
        Map<String, Integer> map = new HashMap<>();
        int idx = 0;
        for(String s : gems) {
        	if(map.containsKey(s)) continue;
        	map.put(s, idx++);
        }
        int len = gems.length;
        int size = map.size();
        Jewel jewel = new Jewel(size);
        
        int sIdx = 0, eIdx = 0;
        while(!jewel.isOk() && eIdx < len) jewel.add(map.get(gems[eIdx++]));
        jewel.end = eIdx;
        while(jewel.isOk() && sIdx < len) jewel.remove(map.get(gems[sIdx++]));
        jewel.start = sIdx;
        jewel.minLen = eIdx - sIdx;
        
        while(eIdx < len) {
        	jewel.add(map.get(gems[eIdx++]));
        	while(jewel.isOk() && sIdx < len) jewel.remove(map.get(gems[sIdx++]));
        	if(eIdx - sIdx < jewel.minLen) {
        		jewel.end = eIdx;
        		jewel.start = sIdx;
                jewel.minLen = eIdx - sIdx;
        	}
        }
        
        return new int[] {jewel.start, jewel.end};
    }
    
    class Jewel {
    	int start, end, minLen;
    	int[] cntArr;
    	public Jewel(int size) {
    		this.cntArr = new int[size];
    		this.minLen = size;
    	}
    	
    	boolean isOk() {
    		for(int num : cntArr) {
    			if(num == 0) return false;
    		}
    		return true;
    	}
    	
    	void add(int idx) {
    		cntArr[idx]++;
    	}
    	
    	void remove(int idx) {
    		cntArr[idx]--;
    	}
    }
}
