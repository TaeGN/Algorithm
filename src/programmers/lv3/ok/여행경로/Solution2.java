package programmers.lv3.ok.여행경로;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (o1, o2) -> {
        	if(!o1[0].equals(o2[0])) return o1[0].compareTo(o2[0]);
        	else return o1[1].compareTo(o2[1]);
        });
        
        Map<String, ArrayDeque<String>> info = new HashMap<>();
        for(String[] sArr : tickets) {
        	info.compute(sArr[0], (k, v) -> v == null ? new ArrayDeque<>() : v).offer(sArr[1]);
        }
        
        List<String> result = new ArrayList<String>();
        String cur = "ICN";
        result.add(cur);
        for(int i = 0, size = tickets.length; i < size; i++) {
        	cur = info.get(cur).poll();
        	result.add(cur);
        }
        return result.toArray(new String[result.size()]);
    }
}
