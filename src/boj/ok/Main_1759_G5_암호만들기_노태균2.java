package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main_1759_G5_암호만들기_노태균2 {
	static Set<String> set = new HashSet<>();
	static List<Character> all = new ArrayList<>();
	static int L, C;
	static char[] password;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		List<Character> consonants = new ArrayList<>();
		List<Character> vowels = new ArrayList<>();
		String[] input = br.readLine().split(" ");
		L = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		char c;
		for(String s : br.readLine().split(" ")) {
			c = s.charAt(0);
			switch(c) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				vowels.add(c);
				break;
			default:
				consonants.add(c);
				break;
			}
			all.add(c);
		}
		br.close();
		password = new char[L];
		int cSize = consonants.size();
		int vSize = vowels.size();
		if(cSize > 1 && vSize > 0) {
			for(int i = 0; i < cSize - 1; i++) {
				password[0] = consonants.get(i);
				all.remove(consonants.get(i));
				for(int j = i + 1; j < cSize; j++) {
					password[1] = consonants.get(j);
					all.remove(consonants.get(j));
					for(int k = 0; k < vSize; k++) {
						password[2] = vowels.get(k);
						all.remove(vowels.get(k));
						getPassword(3, 0);
						all.add(vowels.get(k));
					}
					all.add(consonants.get(j));
				}
				all.add(consonants.get(i));
			}
			
		}
		List<String> list = new ArrayList<>(set);
		Collections.sort(list);
		for(String s : list) {
			sb.append(s).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void getPassword(int cnt, int idx) {
		if(cnt == L) {
			char[] newArr = password.clone();
			Arrays.sort(newArr);
			set.add(String.valueOf(newArr));
			return;
		}
		
		if(idx == C - 3) return;
		password[cnt] = all.get(idx);
		getPassword(cnt + 1, idx + 1);
		getPassword(cnt, idx + 1);
	}
}
