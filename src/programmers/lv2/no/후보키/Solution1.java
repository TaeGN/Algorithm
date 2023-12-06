package programmers.lv2.no.후보키;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution1 {
	
	public static void main(String[] args) {
		System.out.println(solution(new String[][] {{"100","ryan","music","2"},{"200","apeach","math","2"}}));
	}
	static int rLen, cLen, R, result, colCnt;
	static int[] numbers;
	static boolean[] visited;
	static String[][] input;
    public static int solution(String[][] relation) {
    	rLen = relation.length; 
    	cLen = relation[0].length;
    	visited = new boolean[cLen];
    	numbers = new int[cLen];
    	input = relation;
    	colCnt = cLen;
    	for(R = 1; R <= colCnt; R++) {
    		combination(0, 0);
    	}
    	
        return result;
    }
    
	private static void combination(int cnt, int idx) {
		if(cnt == R) {
			if(isKey()) {
				for(int i = 0; i < cnt; i++) {
//					System.out.println(Arrays.toString(numbers));
					visited[numbers[i]] = true;
					result++;
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

	private static boolean isKey() {
		StringBuilder sb = new StringBuilder();
		Set<String> set = new HashSet<>();
		String[] cur;
		for(int i = 0; i < rLen; i++) {
			cur = input[i];
			for(int j = 0; j < R; j++) {
				sb.append(cur[numbers[j]]).append(" ");
			}
			System.out.println(sb.toString());
			if(!set.add(sb.toString())) return false;
			System.out.println(set);
			sb.setLength(0);
		}
		return true;
	}
}
