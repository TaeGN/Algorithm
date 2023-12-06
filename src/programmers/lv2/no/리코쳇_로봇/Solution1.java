package programmers.lv2.no.리코쳇_로봇;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution1 {
	public static void main(String[] args) {
		System.out.println(solution(new String[] {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."}));
	}
	
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
    public static int solution(String[] board) {
    	int rLen = board.length, cLen = board[0].length(), er = 0, ec = 0;
    	boolean[][] map = new boolean[rLen + 2][cLen + 2];
    	Point start = new Point();
    	String cur;
    	for(int r = 1; r <= rLen; r++) {
    		cur = board[r - 1];
    		for(int c = 1; c <= cLen; c++) {
    			switch(cur.charAt(c - 1)) {
    			case '.':
    				map[r][c] = true;
    				break;
    			case 'R': 
    				start.r = r;
    				start.c = c;
    				map[r][c] = true;
    				break;
    			case 'G':
    				er = r;
    				ec = c;
    				map[r][c] = true;
    				break;
    			}
    		}
    	}
    	
    	Set<Point> visited = new HashSet<>();
    	Queue<Point> queue = new ArrayDeque<>();
    	queue.offer(start);
    	visited.add(start);
    	int cnt = 0, r, c;
    	Point point, newPoint;
    	while(!queue.isEmpty()) {
    		cnt++;
    		for(int i = 0, size = queue.size(); i < size; i++) {
    			point = queue.poll();
    			for(int j = 0; j < 4; j++) {
    				r = point.r;
    				c = point.c;
    				while(map[r + dr[j]][c + dc[j]]) {
    					r += dr[j];
    					c += dc[j];
    				}
    				if(r == er && c == ec) return cnt;
    				newPoint = new Point(r, c);
    				if(visited.add(newPoint)) queue.offer(newPoint);
    			}
    		}
    	}
        return -1;
    }
    
    static class Point {
    	int r, c;
    	
    	public Point() {}
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}
    }
}
