package programmers.lv1.ok.카드_뭉치;

public class Solution1 {
	public static void main(String[] args) {
		
	}
	
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int i = 0, j = 0, len1 = cards1.length, len2 = cards2.length;
        for(String s : goal) {
    		if(i < len1 && s.equals(cards1[i])) i++;
    		else if(j < len2 && s.equals(cards2[j])) j++;
    		else return "No";
    	}
        return "Yes";
    }
}
