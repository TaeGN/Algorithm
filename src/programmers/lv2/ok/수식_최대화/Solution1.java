package programmers.lv2.ok.수식_최대화;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
	public static void main(String[] args) {
		String expression = "50*6-3*2";
		int answer = Solution(expression);
		System.out.println(answer);
	}

	private static int Solution(String expression) {
		
		List<Integer> numList = new ArrayList<>();
		List<Character> opList = new ArrayList<>();
		List<Character> operlator = List.of('-','+','*');
		String str = "";
		for(char s : expression.toCharArray()) {
			if(operlator.contains(s)) {
				numList.add(Integer.parseInt(str));
				System.out.println("numList : " + numList);
				opList.add(s);
				str = "";
			} else {
				str += s;
			}
		}
		numList.add(Integer.parseInt(str));
		int maxPrice = 0;
		
		List<List<Character>> list = List.of(
					List.of('-','+','*'),
					List.of('-','*','+'),
					List.of('+','-','*'),
					List.of('+','*','-'),
					List.of('*','+','-'),
					List.of('*','-','+')
				);
		System.out.println(list);
		for(List<Character> op : list) {
			maxPrice = Math.max(maxPrice, Price(numList, opList, op));
		}
		
		System.out.println(numList);
		System.out.println(opList);
		
		
		return maxPrice;
	}

	private static int Price(List<Integer> numList2, List<Character> opList2, List<Character> op) {
		List<Integer> numList = new ArrayList<>(numList2);
		List<Character> opList = new ArrayList<>(opList2);
		int price = 0;
		for(char s : op) {
			int i = 0;
			while(opList.size() > i) {
				if(s == opList.get(i)) {
					int num1 = numList.get(i);
					int num2 = numList.get(i + 1);
					int num = Calculator(num1, num2, s);
					numList.set(i, num);
					numList.remove(i + 1);
					opList.remove(i);
//					price = num;
					System.out.println("s-i : " + s + "-" + i + " numList : " + numList + " price : " + price);
				} else {
					i++;
				}
			}
		}
		System.out.println();
//		price = Math.abs(price);
		price = Math.abs(numList.get(0));
		return price;
	}

	private static int Calculator(int num1, int num2, char s) {
		int result = 0;
		switch(s) {
			case '-':
				result = num1 - num2;
				break;
			case '+':
				result = num1 + num2;
				break;
			case '*':
				result = num1 * num2;
				break;
		}
		return result;
	}

}
