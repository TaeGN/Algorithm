package programmers.lv1.no.당구_연습;

import java.util.Arrays;

public class Solution3 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(10,10,3,7,new int[][] {{7,7},{2,7},{7,3}})));
	}
	static int min, M, N;
	static int[] dxy = {-1,1,-1,1};
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
		if(check(sx, ex, sy, ey, d)) return;
		min = Math.min(min, distance(square(x - sx, sy), square(x - ex, ey)));
		System.out.println(x + ":" + min);
    }
    
    private static boolean check(int sx, int ex, int sy, int ey, int d) {
    	return sx == ex && (ey - sy) < 0;
	}

	private static int distance(double a1, double a2) {
    	return (int) (a1 + a2 + 2 * Math.sqrt(a1 * a2));
    }

	private static double square(double x, double y) {
		return Math.pow(x, 2) + Math.pow(y, 2);
	}
}
