package programmers.lv2.no.메뉴리뉴얼;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Solution {
    public String[] solution(String[] orders, int[] course) {
    	Map<String, Integer> menuMap = new HashMap<>();
    	Set<Integer> courseSet = new HashSet<>();
    	char[] courseArr = new char[10];
    	for (int i = 0; i < course.length; i++) {
    		courseSet.add(course[i]);
		}
    	for (int i = 0, len = orders.length; i < len; i++) {
    		subSet(0, 0, orders[i], courseArr, courseSet, menuMap);
		}
    	
    	String[] answer = new String[menuMap.size()];
    	int idx = 0;
    	Set<Entry<String, Integer>> entrySet = menuMap.entrySet();
    	for (Entry<String, Integer> entry : entrySet) {
			if(entry.getValue() > 1) {
				answer[idx++] = entry.getKey();
				System.out.println(entry.getKey());
			}
		}
    	answer = Arrays.copyOf(answer, idx);
    	Arrays.sort(answer);
    	
        return answer;
    }
    
    void subSet(int idx, int cnt, String order, char[] courseArr, Set<Integer> courseSet, Map<String, Integer> menuMap) {
    	if(courseSet.contains(cnt)) {
    		menuMap.compute(String.valueOf(courseArr, 0, cnt), (k, v) -> v == null ? 1 : v + 1);
    	}
    	
    	if(idx == order.length()) return;
    	
    	courseArr[idx] = order.charAt(idx);
    	subSet(idx + 1, cnt + 1, order, courseArr, courseSet, menuMap);
    	subSet(idx + 1, cnt, order, courseArr, courseSet, menuMap);
    }
}
