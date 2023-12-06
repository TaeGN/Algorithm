package programmers.lv2.ok.파일명_정렬;

import java.util.Arrays;

public class Solution3 {
	public static void main(String[] args) {
		String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
		System.out.println(Arrays.toString(Solution(files)));
	}
	
    public static String[] Solution(String[] files) {
    	StringBuilder sb = new StringBuilder();
    	int len = files.length, sLen;
    	String[][] sortedFiles = new String[len][4];
    	String s;
        for(int i = 0; i < len; i++) {
        	String[] sArr = new String[4];
        	s = files[i];
        	sLen = s.length();
        	int j = 0;
        	while(!Character.isDigit(s.charAt(j))) {
        		sb.append(s.charAt(j++));
        	}
        	sArr[0] = sb.toString().toLowerCase();
        	sb.setLength(0);
        	for(int k = j + 5 >= sLen ? sLen : j + 5; j < k; j++) {
        		if(!Character.isDigit(s.charAt(j))) break;
        		sb.append(s.charAt(j));
        	}
        	sArr[1] = sb.toString();
        	sArr[2] = s.substring(j, sLen);
        	sArr[3] = String.valueOf(i);
        	sortedFiles[i] = sArr;
        	sb.setLength(0);
        }
        
        Arrays.sort(sortedFiles, (o1, o2) -> {
        	if(!o1[0].equals(o2[0])) return o1[0].compareTo(o2[0]);
        	else if(Integer.parseInt(o1[1]) != Integer.parseInt(o2[1])) return Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]);
        	else return Integer.valueOf(o1[3]) - Integer.valueOf(o2[3]);
        });
        
        String[] result = new String[len];
        for(int i = 0; i < len; i++) {
        	result[i] = files[Integer.parseInt(sortedFiles[i][3])];
        }
        	
        return result;
    }
}
