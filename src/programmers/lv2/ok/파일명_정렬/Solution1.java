package programmers.lv2.ok.파일명_정렬;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {
	public static void main(String[] args) {
		String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
		System.out.println(Arrays.toString(Solution(files)));
//		System.out.println(("-".toLowerCase()));
//		System.out.println("abcd".equalsIgnoreCase("ABCD"));
//		System.out.println("abcd".equals("ABCD"));
	}

	private static String[] Solution(String[] files) {
		List<String> a = new ArrayList<>();
		for(String file : files) {
			a.add(file);
		}
		
		// 정렬
		Arrays.sort(files, (o1, o2) -> {
			String s1, s2;
			int n1, n2;
			int i1 = 0;
			int i2 = 0;
			while(!Character.isDigit(o1.charAt(++i1)));
			while(!Character.isDigit(o2.charAt(++i2)));
//			while(Character.isAlphabetic(++i1));
			s1 = o1.substring(0, i1);
//			while(Character.isAlphabetic(++i2));
			s2 = o2.substring(0, i2);
			if(!s1.equalsIgnoreCase(s2)) {	// head부분 사전 순
				return s1.compareTo(s2);
			} 
			int len1 = o1.length();
			int len2 = o2.length();
			int j1 = i1;
			while(j1 < i1 + 5 && j1 < len1 && Character.isDigit(o1.charAt(++j1)));
			int j2 = i2;
			while(j2 < i2 + 5 && j2 < len2 && Character.isDigit(o2.charAt(++j2)));
			n1 = Integer.parseInt(o1.substring(i1, j1));
			n2 = Integer.parseInt(o2.substring(i2, j2));
			if(n1 != n2) {		// number부분 숫자 순
				return n1 - n2;
			} 
			
			return a.indexOf(o1) - a.indexOf(o2);	// 나머지는 초기 입력 순
		});
		return files;
	}
}
