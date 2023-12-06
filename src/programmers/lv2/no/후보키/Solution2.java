package programmers.lv2.no.후보키;

import java.util.HashSet;
import java.util.Set;

public class Solution2 {
	int rLen, cLen, R, cnt, colCnt;
	int[] numbers;
	boolean[] visited;
	String[][] input;
    public int solution(String[][] relation) {
    	rLen = relation.length; 
    	cLen = relation[0].length;
    	visited = new boolean[cLen];
    	numbers = new int[cLen];
    	input = relation;
    	colCnt = cLen;
    	for(R = 1; R <= colCnt; R++) {
    		combination(0, 0);
    	}
    	
        return cnt;
    }
    
	private void combination(int cnt, int idx) {
		if(cnt == R) {
			if(isKey()) {
				for(int i = 0; i < cnt; i++) {
					visited[numbers[i]] = true;
					cnt++;
				}
				colCnt -= cnt;
			}
			return;
		}
		
		for(int i = idx; i < cLen; i++) {
			if(visited[i]) continue;
			numbers[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

	private boolean isKey() {
		StringBuilder sb = new StringBuilder();
		Set<String> set = new HashSet<>();
		String[] cur;
		for(int i = 0; i < rLen; i++) {
			cur = input[i];
			for(int j = 0; j < R; j++) {
				sb.append(cur[numbers[j]]);
			}
			if(!set.add(sb.toString())) return false;
			sb.setLength(0);
		}
		return true;
	}
}
