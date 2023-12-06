package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_문자열제곱 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = null;
		while(!(input = br.readLine()).equals(".")) {
			int len = input.length();
			List<Integer> factorList = getFactors(len);
			System.out.println(factorList);
			
			a:for (int factor : factorList) {
				String a = input.substring(0, factor);
				System.out.println(a);
				for (int i = factor; i < len; i += factor) {
					if(input.indexOf(a, i) != i) continue a;
//					for (int j = 0; j < factor; j++) {
//						System.out.println(i + "," + (i + j) + " " + a.charAt(j) + " : " + input.charAt(i + j));
//						if(a.charAt(j) != input.charAt(i + j)) continue a;
//					}
				}
				sb.append(len / factor).append("\n");
				break;
			}
		}
		br.close();
		System.out.println(sb);
	}
	
	// len의 약수들 구하기
	public static List<Integer> getFactors(int len) {
		List<Integer> list = new ArrayList<>();
		
		for (int i = 1, end = (int) Math.sqrt(len); i <= end; i++) {
			if(len % i == 0) {
				list.add(i);
				if(i * i != len) list.add(len / i);
			}
		}
		Collections.sort(list);
		return list;
	}
}
