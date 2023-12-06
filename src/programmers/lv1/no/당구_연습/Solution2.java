package programmers.lv1.no.당구_연습;

import java.util.Arrays;

public class Solution2 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(10,10,3,7,new int[][] {{7,7},{2,7},{7,3}})));
	}
	static int min, M, N;
	static int[] dy = {-1,1,-1,1};
	public static int[] solution(int m, int n, int startX, int startY, int[][] balls) {
    	int endX, endY, len = balls.length;
    	M = m; N = n;
    	int[] result = new int[len];
    	for(int i = 0; i < len; i++) {
    		min = Integer.MAX_VALUE;
    		endX = balls[i][0];
    		endY = balls[i][1];

    		getDistance(startX, endX, startY, endY, 0);
    		getDistance(startX, endX, n - startY, n - endY, 1);
    		getDistance(startY, endY, startX, endX, 2);
    		getDistance(startY, endY, m - startX, m - endX, 3);

    		result[i] = min;
    	}
        return result;
    }
    
    private static void getDistance(int sx, int ex, int sy, int ey, int d) {
    	int a, b;
    	double x;
		a = sx * ey + sy * ex;
		b = sy + ey;
		x = a / b;
		if(!check(sx, ex, sy, ey, d)) return;
		min = Math.min(min, square(distance(x - sx, sy), distance(x - ex, ey)));
		System.out.println(x + ":" + min);
    }
    
    private static boolean check(int sx, int ex, int sy, int ey, int d) {
//    	if(dx[d] != 0 && (ex - sx) / dx[d] > 0) return false;
    	if(dy[d] != 0 && (ey - sy) / dy[d] > 0) return false;
    	return true;
	}

	private static int square(double a1, double a2) {
    	return (int) ((a1 + a2) * (a1 + a2));
    }
//    private static int square(double a1, double a2) {
//    	return (int) Math.pow(a1 + a2, 2);
//    }

	private static double distance(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}
//	private static double distance(double x, double y) {
//		return Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
//	}
    
}
