package programmers.lv2.ok.뒤에_있는_큰_수_찾기;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class Solution2 {
	public static void main(String[] args) {
		
	}
	
    public int[] solution(int[] numbers) {
    	TreeSet<Integer> set = new TreeSet<>();
    	for(int num : numbers) {
    		set.add(num);
    	}
    	
    	int i = 0;
    	Integer n;
    	for(int num : numbers) {
    		n = set.higher(num);
    		numbers[i++] = n == null ? -1 : n;
    	}
        return numbers;
    }
}
