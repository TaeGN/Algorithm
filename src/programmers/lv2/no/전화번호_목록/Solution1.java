package programmers.lv2.no.전화번호_목록;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution1 {
	public static void main(String[] args) {
//		String[] phone_book = {"119", "97674223", "1195524421", "113"};
		String[] phone_book = {"54843448","12","5123","21235","567","88"};
		
		boolean answer = Solution(phone_book);
		System.out.println(answer);
	}

	private static boolean Solution(String[] phone_book) {
		List<String> list = new ArrayList<>(Arrays.asList(phone_book));
		list.sort((a,b) -> a.length() - b.length());
		
		List<String> phoneList = new ArrayList<>();
		for(int i = 0; i < phone_book.length; i++) {
			phoneList.add("");
		}	
		
		int maxLen = list.get(list.size() - 1).length();
		for(int i = 1; i <= maxLen; i++) {
			for(int j = 0; j < list.size();) {
				String s = phoneList.get(j);
				System.out.println("i : " + i + " - " + phoneList);
				if(s.length() == list.get(j).length()) {
					list.remove(j);
					phoneList.remove(j);
					if(phoneList.contains(s)) {
						return false;
					}
				} else {
					phoneList.set(j, s + list.get(j).charAt(i - 1));
					j++;
				}
			}
			
		}
			
		return true;
	}

	// 효율성4 시간초과
//	private static boolean Solution(String[] phone_book) {
//		
//		Map<Integer, List<String>> map = new HashMap<>();
//		for(String s : phone_book) {
//			int sLen = s.length();
//			if(!map.containsKey(sLen)) {
//				map.put(sLen, new ArrayList<String>());
//			}
// 			map.get(sLen).add(s);
//		}
//		System.out.println(map);
//		List<Integer> keyList = new ArrayList<>(map.keySet());
//		keyList.sort((a,b) -> a - b);
//		System.out.println(keyList);
//		for(int i = 0; i < keyList.size() - 1; i++) {
//			for(int j = i + 1; j < keyList.size(); j++) {
//				for(String a : map.get(keyList.get(i))) {
//					for(String b : map.get(keyList.get(j))) {
//						System.out.println("a : " + a + " - b : " + b);
//						if(b.substring(0, keyList.get(i)).equals(a)) {
//							return false;
//						}
//					}
//				}
//			}
//		}
//		return true;
//	}
}
