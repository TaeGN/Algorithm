package programmers.lv1.no.당구_연습;

public class Solution1 {
	
	int min;
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
    	int endX, endY, len = balls.length;
    	int[] result = new int[len];
    	for(int i = 0; i < len; i++) {
    		min = Integer.MAX_VALUE;
    		endX = balls[i][0];
    		endY = balls[i][1];

    		getDistance(startX, endX, startY, endY);
    		getDistance(startX, endX, n - startY, n - endY);
    		getDistance(startY, endY, startX, endX);
    		getDistance(startY, endY, m - startX, m - endX);

    		result[i] = min;
    	}
        return result;
    }
    
    private void getDistance(int sx, int ex, int sy, int ey) {
    	int a, b, x;
		a = sx * ey + sy * ex;
		b = sy + ey;
		if(a % b == 0) {
			x = a / b;
			System.out.println(x);
			min = Math.min(min, square(distance(x - sx, sy), distance(x - ex, ey)));
		}
    }
    
    private int square(double a1, double a2) {
    	return (int) Math.pow(a1 + a2, 2);
    }

	private double distance(int x, int y) {
		return Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
	}
    
}
