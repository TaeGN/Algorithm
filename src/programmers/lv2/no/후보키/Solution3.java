package programmers.lv2.no.ÈÄº¸Å°;

import java.util.HashSet;
import java.util.Set;

public class Solution3 {
    public int solution(String[][] relation) {
    	keyCnt = cLen = relation[0].length;
    	rLen = relation.length;
    	
    	for (tLen = 1; tLen <= keyCnt; tLen++) {
			combination(0, 0, visited);
		}
    	relation = this.relation;
        return answer;
    }
    
    int keyCnt, cLen, rLen, tLen, visited, answer;
    String[][] relation;
    boolean combination(int cnt, int sIdx, int flag) {
    	if(cnt == tLen) {
    		if(isCandidateKey(flag)) {
    			answer++;
    			visited = (visited | flag);
    			keyCnt -= tLen;
    			if(keyCnt < tLen) return true;
    		}
    		return false;
    	}
    	for (int i = sIdx; i < cLen; i++) {
			if((flag & i) != 0) continue;
			if(combination(cnt + 1, i + 1, flag | i)) return true;
		}
    	
    	return false;
	}
    
	boolean isCandidateKey(int flag) {
		Set<String> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				if((flag | i) != 0) {
					sb.append(relation[i][j]);
				}
				if(!set.add(sb.toString())) return false;
				sb.setLength(0);
			}
		}
		return true;
	}
}
