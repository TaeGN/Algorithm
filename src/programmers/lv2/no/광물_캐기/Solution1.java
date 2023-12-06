package programmers.lv2.no.±¤¹°_Ä³±â;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution1 {
    public int solution(int[] picks, String[] minerals) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<String, Integer> mineral = new HashMap<>();
        mineral.put("diamond", 100);
        mineral.put("iron", 10);
        mineral.put("stone", 1);
        int end = (picks[0] + picks[1] + picks[2]) * 5;
        end = Math.min(end, minerals.length);
        int num = 0;
        for (int i = 0, j = 0; i < end; i++) {
			if(j++ == 5) {
				map.compute(num, (k, v) -> v == null ? 1 : v + 1);
				num = 0;
				j = 1;
			}
			num += mineral.get(minerals[i]);
		}
        map.compute(num, (k, v) -> v == null ? 1 : v + 1);
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, Collections.reverseOrder());
        int aIdx = 0, bIdx = 0, answer = 0;
        int[][] fatigue = {{1,1,1},{1,1,5},{1,5,25}};
        a:while(bIdx < list.size()) {
        	num = list.get(bIdx);
        	int cnt = map.get(num);
        	for (int i = 0; i < cnt; i++) {
        		while(picks[aIdx]-- == 0) {
            		if(++aIdx == 3) break a;
            	}
        		for (int j = 2; j >= 0; j--) {
					answer += (num / Math.pow(10, j)) * fatigue[aIdx][j];
					num %= Math.pow(10, j); 
				}
			}
        	bIdx++;
        }
        System.out.println(map);
        System.out.println(list);
        return answer;
    }
}
