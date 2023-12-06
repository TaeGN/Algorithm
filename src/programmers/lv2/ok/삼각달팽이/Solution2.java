package programmers.lv2.ok.삼각달팽이;

import java.util.Arrays;

public class Solution2 {
	public static void main(String[] args) {
		int n = 4;
		System.out.println(Arrays.toString(Solution(n)));
	}
	
    public static int[] Solution(int n) {
        int[] result = new int[n];
        int i = n, j = 0, num = 1;
        while(i > 0) {
        	for(int k = 0; k < i; k++) {
        		result[j] += num++;
        	}
        	
        	for(int k = j + 1; k < j + i; k++) {
        		result[k] += num++;
        	}
        	
        	for(int k = 0; k < i - 2; k++) {
        		result[n - 1 - j] += num++;
        	}
        	
        	i -= 3; j++;
        }
        
        return result;
    }
}
