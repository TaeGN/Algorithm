package programmers.lv2.no.PCCP2;

import java.util.*;

class Solution {
    final int EXIST = 1;
    final int NOT_EXIST = 0;
    
    final int[] dr = {0,1,0,-1};
    final int[] dc = {1,0,-1,0};
    
    public int solution(int[][] land) {
        int answer = 0;
        final int N = land.length;
        final int M = land[0].length;
        final Oil oil = new Oil(M);
        final Line line = new Line(M);
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(land[i][j] == NOT_EXIST) continue;
                int count = research(land, i, j, line);
                
                for(final int x: line.line) {
                    oil.add(x, count);
                }
                line.clear();
            }
        }
        
        return answer;
    }
    
    int research(final int[][] land, final int i, final int j, final Line line) {
        land[i][j] = NOT_EXIST;
        line.add(j);
        int count = 1;
        for(int d = 0; d < 4; d++) {
            int ii = i + dr[d];
            int jj = j + dc[d];
            if(!validation(ii, jj, land.length, land[0].length)) continue;
            if(land[i][j] == NOT_EXIST) continue;
            count += research(land, ii, jj, line);
        }
        System.out.println();
        return count;
    }
    
    boolean validation(final int i, final int j, final int N, final int M) {
        return i >= 0 && j >= 0 && i < N && j < M;
    }
    
    class Oil {
        final int[] line;
        
        Oil(final int M) {
            line = new int[M];
        }
        
        void add(final int j, final int count) {
            this.line[j] += count;
        }
        
        int getMax() {
            int max = 0;
            for(int j = 0; j < line.length; j++) {
                max = Math.max(max, this.line[j]);
            }
            return max;
        }
    }
    
    class Line {
        final Set<Integer> line;
        
        Line(final int M) {
            line = new HashSet<>(M);
        }
        
        void add(final int j) {
            this.line.add(j);
        }
        
        void clear() {
            this.line.clear();
        }
    }
}
