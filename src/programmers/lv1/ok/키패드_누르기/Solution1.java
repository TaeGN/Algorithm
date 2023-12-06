package programmers.lv1.ok.키패드_누르기;

public class Solution1 {
    public String solution(int[] numbers, String hand) {
        Point[] points = new Point[10];
        int i = 1;
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                points[i++] = new Point(r, c);
            }
        }
        points[0] = new Point(3,1);
        
        boolean isLeft = false;
        if(hand.equals("left")) isLeft = true;
        
        StringBuilder sb = new StringBuilder();
        int lLen, rLen;
        Point cur;
        Point LPoint = new Point(3,0);
        Point RPoint = new Point(3,2);
        for(int num : numbers) {
        	cur = points[num];
            switch(num) {
                case 1: case 4: case 7:
                    sb.append('L');
                    LPoint.move(cur);
                    break;
                case 3: case 6: case 9:
                    sb.append('R');
                    RPoint.move(cur);
                    break;
                default:
                    lLen = LPoint.getLen(cur);
                    rLen = RPoint.getLen(cur);
                    if(lLen == rLen) {
                        if(isLeft) {
                        	LPoint.move(cur);
                            sb.append('L');
                        } else {
                        	RPoint.move(cur);
                            sb.append('R');
                        }
                    } else if(lLen > rLen) {
                    	RPoint.move(cur);
                        sb.append('R');
                    } else {
                    	LPoint.move(cur);
                        sb.append('L');
                    }
            }
        }
        
        return sb.toString();
    }
    
    class Point {
        int r, c;
        
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        private int getLen(Point n) {
            return Math.abs(this.r - n.r) + Math.abs(this.c - n.c);
        }
        
        private void move(Point n) {
            this.r = n.r;
            this.c = n.c;
        }
    }
}
