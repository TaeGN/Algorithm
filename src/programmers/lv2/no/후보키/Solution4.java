package programmers.lv2.no.ÈÄº¸Å°;

import java.util.HashSet;
import java.util.Set;

public class Solution4 {
	public static void main(String[] args) {
		System.out.println(solution(new String[][] {
				{"100","ryan","music","2"},
				{"200","apeach","math","2"},
				{"300","tube","computer","3"},
				{"400","con","computer","4"},
				{"500","muzi","music","3"},
				{"600","apeach","music","2"}
		}));
	}
    public static int solution(String[][] relation) {
    	keyCnt = cLen = relation[0].length;
    	rLen = relation.length;
    	relations = relation;
    	for (tLen = 1; tLen <= keyCnt; tLen++) {
			combination(0, 0, visited);
		}
        return answer;
    }
    
    static int keyCnt, cLen, rLen, tLen, visited, answer;
    static String[][] relations;
    static boolean combination(int cnt, int sIdx, int flag) {
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
			if((flag & (1 << i)) != 0) continue;
			if(combination(cnt + 1, i + 1, flag | (1 << i))) return true;
		}
    	
    	return false;
	}
    
    static boolean isCandidateKey(int flag) {
		Set<String> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				if((flag & (1 << j)) != 0 && (visited & (1 << j)) == 0) {
					sb.append(relations[i][j]).append(" ");
				}
			}
			if(!set.add(sb.toString())) return false;
			sb.setLength(0);
		}
		return true;
	}
}
