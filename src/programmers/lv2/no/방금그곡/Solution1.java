package programmers.lv2.no.방금그곡;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution1 {
	public static void main(String[] args) {
		String m = "ABC";
		String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		String answer = Solution(m, musicinfos);
		System.out.println(answer);
	}

	private static String Solution(String m, String[] musicinfos) {
		String music = "(None)";
		int time = 0;
		
		for(String s : musicinfos) {
			
			// musicinfo의 각 원소 분리
			String[] musicinfo = s.split(",");
			
			// playTime 구하기
			int startNum = Integer.parseInt(musicinfo[0].substring(0, 2)) * 60 + Integer.parseInt(musicinfo[0].substring(3, 5));
			int endNum = Integer.parseInt(musicinfo[1].substring(0, 2)) * 60 + Integer.parseInt(musicinfo[1].substring(3, 5));
			int playTime = endNum - startNum;
			
			// playTime 동안 재생된 음 구하기 - str
			String str = "";
			for(int i = 0; i < (playTime / musicinfo[3].replace("#","").length()); i++) {
				str += musicinfo[3];
			}
			int a = playTime % musicinfo[3].replace("#","").length();
			int i = 0;
			String t = "";
			while(a > 0) {
				if(musicinfo[3].charAt(i) != '#') {
					str += t;
					t = "" + musicinfo[3].charAt(i);
					a--;
					
				} else {
					t += '#';
				}
				i++;
			}
			str += musicinfo[3].substring(0, (playTime % musicinfo[3].length()));
			
			for(int j = 0; j <= str.length() - m.length(); j++) {
				int mIndex = str.indexOf(m, j);
				int notMIndex = str.indexOf(m + "#", j);
				if(mIndex != 0) {
					if(mIndex != notMIndex && time < playTime) {
						music = musicinfo[2];
						time = playTime;
					}
					j = str.indexOf(m, j) + 1;
				} else {
					break;
				}
			}
//			if(time < playTime) {
//				music = musicinfo[2];
//				time = playTime;
//			}
			
			// match되는지 확인
			
//			String regex = ".*" + m + "\\w*";
//			if((str.matches(".*" + m + "\\w+") || str.matches(".*" + m + "$")) && time < playTime) {
//				music = musicinfo[2];
//				time = playTime;
//			}
		}
		return music;
	}
}
