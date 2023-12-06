package programmers.lv1.no.개인정보_수집_유효기간;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution1 {
	public static void main(String[] args) {
		String today = "2022.05.19";
		String[] terms = {"A 6", "B 12", "C 3"};
		String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
		System.out.println(Arrays.toString(solution(today, terms, privacies)));
	}
	
	    public static int[] solution(String today, String[] terms, String[] privacies) {
		termMap = new HashMap<>(terms.length);
		String[] input;
		for (String s : terms) {
			input = s.split(" ");
			termMap.put(input[0], Integer.parseInt(input[1]));
		}
		curDate = getDate(today);
		Info[] info = new Info[privacies.length];
		int i = 0;
		for (String s : privacies) {
			info[i] = new Info(++i, s);
		}
		System.out.println(Arrays.toString(info));
		Arrays.sort(info);
		int[] answer = new int[privacies.length];
		i = 0;
		while (info[i].end) {
			answer[i] = info[i++].idx;
		}
		System.out.println(Arrays.toString(info));
		System.out.println(Arrays.toString(answer));
		return Arrays.copyOfRange(answer, 0, i);

	}

	static Map<String, Integer> termMap;
	static int curDate;

	static class Info implements Comparable<Info> {
		int idx;
		boolean end;

		public Info(int idx, String s) {
			this.idx = idx;
			if (curDate > getDate(s))
				end = true;
		}

		@Override
		public int compareTo(Info o) {
			if (this.end != o.end)
				return Boolean.compare(o.end, this.end);
			return this.idx - o.idx;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Info [idx=").append(idx).append(", end=").append(end).append("]");
			return builder.toString();
		}

	}

	static int getDate(String s) {
		int date = 0;
		String[] sArr = s.split(" ");
		if (sArr.length == 2)
			date += termMap.get(sArr[1]) * 28;
		sArr = sArr[0].split("\\.");
		date += (Integer.parseInt(sArr[0]) * 12 * 28 + Integer.parseInt(sArr[1]) * 28 + Integer.parseInt(sArr[2]));
		return date;
	}

}

