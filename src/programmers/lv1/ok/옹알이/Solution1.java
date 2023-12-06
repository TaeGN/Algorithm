package programmers.lv1.ok.옹알이;

public class Solution1 {
	public static void main(String[] args) {
		System.out.println(solution(new String[] {"aya", "yee", "u"}));
	}
    public static int solution(String[] babbling) {
        int cnt = 0;
        a:for(String s : babbling) {
        	char prev = '0';
        	for(char c : s.replace("aya", "1")
                	.replace("ye", "2")
                	.replace("woo", "3")
                	.replace("ma", "4")
                	.toCharArray()) {
        		if(Character.isAlphabetic(c) || prev == c) continue a;
        		prev = c;
        	}
        	cnt++;
        }
        return cnt;
    }
}
