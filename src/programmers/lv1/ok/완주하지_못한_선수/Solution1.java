package programmers.lv1.ok.완주하지_못한_선수;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {
	public static void main(String[] args) {
		
	}
	
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0, len = completion.length; i < len; i++) {
        	map.compute(participant[i], (k,v) -> v == null ? 1 : v + 1);
        	map.compute(completion[i], (k,v) -> v == null ? -1 : v - 1);
        }
        map.compute(participant[participant.length - 1], (k,v) -> v == null ? 1 : v + 1);
        
        for(Map.Entry<String, Integer> entrySet : map.entrySet()) {
        	if(entrySet.getValue() == 1) return entrySet.getKey();
        }
        
        return null;
    }
}
