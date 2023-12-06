package programmers.lv2.ok.삼각달팽이;

import java.util.Arrays;

public class Solution1 {
	public static void main(String[] args) {
		int n = 4;
		System.out.println(Arrays.toString(Solution(n)));
	}
	
    public static int[] Solution(int n) {
        int[] result = new int[n];
        int num = 0, m;
    	for(int i = n, k = 0, l = n - 1; n > 0; n -= 3, k += 2, l--) {
    		int j = i;
    		while(j > 0) result[n + k + j--] += num++;
    		j = i - 1;
    		while(j-- > 0) result[l] += num++;
    		j = i - 2; m = n - 2;
    		while(j-- > 0) result[m++] = num++;
    	}
        return result;
    }
}
