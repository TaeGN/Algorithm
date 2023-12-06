package programmers.lv1.ok.신고_결과_받기;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution1 {
	public static void main(String[] args) {
		
	}
	
	 public int[] solution(String[] id_list, String[] report, int k) {
	        Map<String, Integer> idIdx = new HashMap<>(id_list.length);
	        int i = 0;
	        for(String s : id_list) {
	            idIdx.put(s, i++);
	        }
	        Map<String, Set<String>> reportMap = new HashMap<>(id_list.length);
	        String[] input;
	        for(String s : report) {
	            input = s.split(" ");
	            reportMap.compute(input[1], (key,value) -> value == null ? new HashSet<String>() : value).add(input[0]);
	        }
	        
	        int[] result = new int[id_list.length];
	        for(Map.Entry<String, Set<String>> entry : reportMap.entrySet()) {
	            if(entry.getValue().size() >= k) {
	                for(String s : entry.getValue()) {
	                    result[idIdx.get(s)]++;
	                }
	            } 
	        }

	        return result;
	    }
}
