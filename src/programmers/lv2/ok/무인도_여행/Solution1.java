package programmers.lv2.ok.무인도_여행;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution1 {
	public static void main(String[] args) {
		System.out.println(solution(new String[] {"X591X","X1X5X","X231X", "1XXX1"}));
	}
	
    public static int[] solution(String[] maps) {
    	rLen = maps.length; 
    	cLen = maps[0].length();
    	map = new char[rLen][];
    	for(int r = 0; r < rLen; r++) {
    		map[r] = maps[r].toCharArray();
    	}
    	
    	for(int r = 0; r < rLen; r++) {
    		for(int c = 0; c < cLen; c++) {
    			if(map[r][c] != 'X') {
    				dfs(r, c);
    				list.add(sum);
    				sum = 0;
    			}
    		}
    	}
    	
    	Collections.sort(list);
    	int[] result = new int[list.size()];
    	int i = 0;
    	for(int num : list) {
    		result[i++] = num;
    	}
        return list.size() != 0 ? result : new int[] {-1};
    }
    
    static List<Integer> list = new ArrayList<>();
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static int rLen, cLen, sum;
    static char[][] map;
    static void dfs(int r, int c) {
    	sum += map[r][c] - '0';
    	map[r][c] = 'X';
    	
    	int rr, cc;
    	for(int i = 0; i < 4; i++) {
    		rr = r + dr[i];
    		cc = c + dc[i];
    		if(rr >= rLen || cc >= cLen || rr < 0 || cc < 0 || map[rr][cc] == 'X') continue;
    		dfs(rr, cc);
    	}
    }
}
