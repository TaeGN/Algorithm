package programmers.lv3.ok.여행경로;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (o1, o2) -> {
        	if(!o1[0].equals(o2[0])) return o1[0].compareTo(o2[0]);
        	else return o1[1].compareTo(o2[1]);
        });
        
        for(String[] sArr : tickets) {
        	info.compute(sArr[0], (k, v) -> v == null ? new ArrayList<>() : v).add(sArr[1]);
        }
        size = tickets.length;
        result = new String[size + 1];
        dfs("ICN", 0);
        return result;
    }
    
    int size;
    String[] result;
    Map<String, ArrayList<String>> info = new HashMap<>();
    boolean dfs(String cur, int cnt) {
    	if(cnt == size + 1) return true;
    	result[cnt] = cur;
    	for(int i = 0, len = info.get(cur).size(); i < len; i++) {
    		String s = info.get(cur).remove(i);
    		if(dfs(s, cnt + 1)) return true;
    		info.get(cur).add(i, s);
    	}
    	return false;
    }
}
