package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1104_패턴 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		long C = Long.parseLong(br.readLine());
		System.out.println(getResult(s1, s2, C));
	}
	
	private static long getResult(String s1, String s2, long C) {
		int start, end, s, e;
		final int s1Len = s1.length(), s2Len = s2.length();
		final long s1Len1000000 = s1Len * 1000000L;
		final long MaxLen = (long) Math.pow(10, 16) - 1;
		boolean s1AllZero = false;
		// s1 setting
		int i = -1;
		while(++i < s1Len && s1.charAt(i) == '0');
		if(i >= C) return 0;	// s1 처음에서 답 찾음
		Info s1FirstInfo = new Info(0, i, i);
		start = i;
		i = s1Len;
		while(--i >= 0 && s1.charAt(i) == '0');
		Info s1LastInfo = new Info(i + 1, s1Len, s1Len - i - 1);
		end = i;
		if(start <= end) {	//시작 0문자열 끝 0문자열 분리되어 있을 경우
			a:for(i = start + 1; i <= end;) {
				while(s1.charAt(i) != '0') if(++i > end) break a;
				s = i;
				while(++i <= end && s1.charAt(i) == '0');
				e = i;
				if(e - s >= C) return s;	// s1의 가운데에서 길이가 C이상인 0문자열 찾음
			}
			if(s1LastInfo.len + s1FirstInfo.len >= C) return s1LastInfo.start;	// s1 + s1에서 답 찾음
		} else {	// s1 시작부터 끝까지 0
			s1AllZero = true;
			if(s1Len1000000 >= C) return 0;	// s1 백만개에서 답을 찾음
		}
		
		// s2 setting
		i = -1;
		while(++i < s2Len && s2.charAt(i) == '0');
		if(i + s1LastInfo.len >= C) return 999999L * s1Len + s1LastInfo.start;	// s1 + s2처음에서 답 찾음
		Info s2FirstInfo = new Info(0, i, i);
		if(s1AllZero && s1Len1000000 + s2FirstInfo.len >= C) return 0;	// s1 백만개 + s2 시작에서 답을 찾음
		
		start = i;
		i = s2Len;
		while(--i >= 0 && s2.charAt(i) == '0');
		Info s2LastInfo = new Info(i + 1, s2Len, s2Len - i - 1);
		
		end = i;
		if(start <= end) {
			a:for(i = start + 1; i <= end;) {
				while(s2.charAt(i) != '0') if(++i > end) break a;
				s = i;
				while(++i <= end && s2.charAt(i) == '0');
				e = i;
				if(e - s >= C) return s1Len1000000 + s;		// s2 가운데에서 답 찾음
			}
			if(s2LastInfo.len + s1FirstInfo.len >= C) return s1Len1000000 + s2LastInfo.start;		// s2 끝에서 답 찾음
			if(s2LastInfo.len + s2FirstInfo.len >= C) return 2 * s1Len1000000 + s2Len + s2LastInfo.start;	// s2 + s2에서 답 찾음
			if(s1AllZero && s1Len1000000 + s2FirstInfo.len + s2LastInfo.len >= C) return s1Len1000000 + s2LastInfo.start;	// s2 끝 + s1 백만개 + s2 시작에서 답을 찾음
		} else {	// s2 시작부터 끝까지 0
			if(s1AllZero) return 0;	// s1, s2 모두 0
			long a = 0L;
			long b = 0L;
			a = ((C - s1LastInfo.len - s1FirstInfo.len - 1) / s2Len) + 1;
			if(a > Math.pow(10, 9)) return -1;
			b = MaxLen - a * s1Len1000000 - (a * (a + 1) / 2) * s2Len + s1LastInfo.len;
			if(b < 0) return -1;
			return MaxLen - b - a * s2Len;
		}
		
		return -1;
	}

	static class Info {
		int start, end, len;
		
		public Info() {}
		public Info(int start, int end, int len) {
			super();
			this.start = start;
			this.end = end;
			this.len = len;
		}
	}
}
