package programmers.lv2.no.가장_큰_수;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static Solution solution = new Solution();
	
	public static void main(String[] args) {
		int[] numbers = {12, 121};
//		int[] numbers = {3, 33, 303, 34, 53, 39};
		
		System.out.println(solution.solution(numbers));
	}
}
