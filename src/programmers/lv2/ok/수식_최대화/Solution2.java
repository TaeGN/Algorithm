package programmers.lv2.ok.수식_최대화;

import java.util.List;
import java.util.ArrayList;

public class Solution2 {
	public static void main(String[] args) {
		String expression = "100-200*300-500+20";
		long answer = Solution(expression);
		System.out.println(answer);
	}

	private static long Solution(String expression) {
		List<Character> symbol = List.of('+','-','*');
		List<Character> symbolList = new ArrayList<>();
		List<Long> numList = new ArrayList<>();
		
		String str = "";
		for(char c : expression.toCharArray()) {
			if(symbol.contains(c)) {
				symbolList.add(c);
				numList.add(Long.parseLong(str));
				str = "";
			} else {
				str += c;
			}
		}
		numList.add(Long.parseLong(str));
		
		List<List<Character>> list = List.of(
				List.of('-','+','*'),
				List.of('-','*','+'),
				List.of('+','-','*'),
				List.of('+','*','-'),
				List.of('*','+','-'),
				List.of('*','-','+')
			);
		
		long max = 0;
		for(List<Character> sList : list) {
			max = Math.max(max, Operator(symbolList, numList, sList));
		}
		
		return max;
	}

	private static long Operator(List<Character> symbolList2, List<Long> numList2, List<Character> sList) {
		List<Character> symbolList = new ArrayList<>(symbolList2);
		List<Long> numList = new ArrayList<>(numList2);
		
		for(char c : sList) {
			int i = 0;
			while(symbolList.size() > i) {
				if(symbolList.get(i) == c) {
					switch(c) {
						case '-':
							numList.set(i + 1, numList.get(i) - numList.get(i + 1));
							break;
	
						case '+':
							numList.set(i + 1, numList.get(i) + numList.get(i + 1));
							break;
	
						case '*':
							numList.set(i + 1, numList.get(i) * numList.get(i + 1));
							break;
					}
					numList.remove(i);
					symbolList.remove(i);
				} else {
					i++;
				}
			}
		}
		
		return (Long)Math.abs(numList.get(0));
	}
}
