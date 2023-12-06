package programmers.lv1.ok.숫자_짝꿍;

public class Solution1 {
    public String solution(String X, String Y) {
    	int[] x = new int[10];
    	int[] y = new int[10];
    	
    	for(char c : X.toCharArray()) {
    		x[c - '0']++;
    	}
    	
    	for(char c : Y.toCharArray()) {
    		y[c - '0']++;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i = 9; i >= 0; i--) {
    		for(int j = 0, len = Math.min(x[i], y[i]); j < len; j++) {
    			sb.append(i);
    		}
    	}
    	if(sb.length() == 0) return "-1";
        return sb.toString().charAt(0) == '0' ? "0" : sb.toString();
    }
}
