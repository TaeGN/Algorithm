package programmers.lv2.ok.프렌즈4블록;

import java.util.HashSet;
import java.util.Set;

public class Solution_print {
	
	int[] dr = {0,0,1,1};
	int[] dc = {0,1,0,1};
	final int INF = Integer.MAX_VALUE >> 2;
	
    public int solution(int m, int n, String[] board) {
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		map[i][j] = board[i].charAt(j);
			}
		}
        
        for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((map[i][j] == INF ? 0 : change(map[i][j])) + " ");
			}
			System.out.println();
		}
        System.out.println();
        
        Set<Integer> removeSet = new HashSet<>();
        int r, c;
        while(true) {
        	// 삭제 리스트 만들기
        	for (int i = 0; i < m - 1; i++) {
        		a:for (int j = 0; j < n - 1; j++) {
        			int val = map[i][j];
        			if(map[i][j] == INF) continue;
        			for (int k = 1; k < 4; k++) {
        				if(map[i + dr[k]][j + dc[k]] != val) continue a;
        			}
        			removeSet.add(i * n + j);
        		}
        	}
        	// 삭제 없으면 종료
        	if(removeSet.isEmpty()) break;
        	
        	// 삭제
        	for (int rc : removeSet) {
        		r = rc / n;
        		c = rc % n;
        		for (int i = 0; i < 4; i++) {
					map[r + dr[i]][c + dc[i]] = INF;
				}
        	}
        	removeSet.clear();
        	
        	
            for (int i = 0; i < m; i++) {
    			for (int j = 0; j < n; j++) {
    				System.out.print((map[i][j] == INF ? 0 : change(map[i][j])) + " ");
    			}
    			System.out.println();
    		}
            System.out.println();
        	
        	// 빈 곳 메우기
        	for (int i = 0; i < n; i++) {
        		// 가장 첫번째로 만나는 빈 공간
        		int j = m;
				while(--j >= 0 && map[j][i] != INF);
				// 빈 공간 없으면 생략
				if(j < 0) continue;
				
				// 빈 공간 위에서 가장 첫번째로 만나는 블록
				int k = j;
				while(--k >= 0 && map[k][i] == INF);
				// 블록 없으면 생략
				if(k < 0) continue;
				
				// 블록 이동
				while(k >= 0) {
					map[j--][i] = map[k][i];
					map[k][i] = INF;
					while(--k >= 0 && map[k][i] == INF);
				}
			}
        	
            for (int i = 0; i < m; i++) {
    			for (int j = 0; j < n; j++) {
    				System.out.print((map[i][j] == INF ? 0 : change(map[i][j])) + " ");
    			}
    			System.out.println();
    		}
            System.out.println();
        	
            int res = 0;
            for (int i = 0; i < m; i++) {
    			for (int j = 0; j < n; j++) {
    				if(map[i][j] == INF) res++;
    			}
    		}
        	System.out.println(res);
        }
        
        int res = 0;
        for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j] == INF) res++;
			}
		}
        return res;
    }
    
    int change(int val) {
    	return val;
    }
}
