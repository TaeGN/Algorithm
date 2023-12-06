package programmers.lv3.ok.불량_사용자;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution1 {
	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "abc1**"};
		System.out.println(solution(user_id, banned_id));
	}
	static int cnt, N;
	static int[] numbers;
	static boolean[] isSelected;
	static Set<Ban> set = new HashSet<>();
    public static int solution(String[] user_id, String[] banned_id) {
       ArrayList<Integer>[] banList = new ArrayList[banned_id.length];
       int banLen, idx = 0;
       String user;
        for(String ban : banned_id) {
        	banList[idx] = new ArrayList<>();
        	banLen = ban.length();
        	a:for(int i = 0, size = user_id.length; i < size; i++) {
        		user = user_id[i];
        		if(banLen != user.length()) continue;
        		for(int j = 0; j < banLen; j++) {
        			if(ban.charAt(j) == '*') continue;
        			if(user.charAt(j) != ban.charAt(j)) continue a;
        		}
        		banList[idx].add(i);
        	}
        	idx++;
        }
        System.out.println(Arrays.toString(banList));
        N = banned_id.length;
        numbers = new int[N];
        isSelected = new boolean[user_id.length];
        combi(0, banList);
        return set.size();
    }
	
	private static void combi(int idx, ArrayList<Integer>[] banList) {
		if(idx == N) {
			System.out.println(Arrays.toString(numbers));
			set.add(new Ban(numbers));
			return;
		}
		
		ArrayList<Integer> cur = banList[idx];
		for(int i = 0, len = cur.size(); i < len; i++) {
			int num = cur.get(i);
			if(isSelected[num]) continue;
			numbers[idx] = num;
			isSelected[num] = true;
			combi(idx + 1, banList);
			isSelected[num] = false;
		}
	}
	
	static class Ban {
		int len;
		int[] banList;
		
		public Ban(int[] banList) {
			super();
			this.len = banList.length;
			this.banList = banList.clone();
			Arrays.sort(this.banList);
		}
		
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(banList);
			result = prime * result + len;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Ban other = (Ban) obj;
			if (!Arrays.equals(banList, other.banList))
				return false;
			if (len != other.len)
				return false;
			for(int i = 0; i < len; i++) {
				if(this.banList[i] != other.banList[i]) return false;
			}
			return true;
		}
	}
}
