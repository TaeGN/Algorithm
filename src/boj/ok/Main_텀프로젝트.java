package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_텀프로젝트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int MAX_STUDENT = 100000;
		final int VISIT = -1;
		int T = Integer.parseInt(br.readLine());
		int[] select = new int[MAX_STUDENT + 1];
		
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] input = br.readLine().split(" ");
			// select 배열 채우기
			for (int i = 1; i <= N; i++) {
				int num = Integer.parseInt(input[i - 1]);
				select[i] = num == i ? -1 : num;
			}
			
			List<Integer> list = new ArrayList<>();
			int res = 0;
			for (int i = 1; i <= N; i++) {
				// 이미 방문한 곳 continue
				if(select[i] == VISIT) continue;
				
				int cur = i;
				int next = select[cur];
				while(next != -1) {
					// 팀 구성 list에 저장
					list.add(cur);
					// 방문 체크
					select[cur] = VISIT;
					// 다음 팀원 순차 탐색
					cur = next;
					next = select[cur];
				}
				
				// 팀의 시작 idx 구하기 -1 => 팀 구성 실패
				int idx = list.indexOf(Integer.valueOf(cur));
				if(idx == -1) res += list.size();
				else res += idx;
				list.clear();
			}
			sb.append(res).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
}
