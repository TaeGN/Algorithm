package programmers.lv2.ok.파일명_정렬;

import java.util.Arrays;

public class Solution2 {
	public static void main(String[] args) {
		String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
		System.out.println(Arrays.toString(Solution(files)));
	}

	private static String[] Solution(String[] files) {
		String[][] inputFiles = new String[files.length][4];
		String s;
		int i, j, len, k = 0;
		for(String file : files) {	// files를 head, number, tail부분으로 나누어서 저장
			i = 0;
			len = file.length();
			while(!Character.isDigit(file.charAt(++i)));
			inputFiles[k][0] = file.substring(0, i);
			
			j = i;
			while(j < i + 5 && j < len && Character.isDigit(file.charAt(++j)));
			inputFiles[k][1] = file.substring(i, j);
			
			inputFiles[k][2] = file.substring(j, file.length());
			inputFiles[k][3] = String.valueOf(k);
		}
		input
		return null;
	}
}
