package programmers.lv2.no.메뉴리뉴얼;

public class Main {
	static Solution solution = new Solution();
	
	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2,3,4};
		System.out.println(solution.solution(orders, course));
	}
}
