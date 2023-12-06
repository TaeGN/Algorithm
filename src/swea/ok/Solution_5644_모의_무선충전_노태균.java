package swea.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_5644_모의_무선충전_노태균 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] input1, input2;
		String[][] map;
		int[] dx = {0, 0, 1, 0, -1};
		int[] dy = {0, -1, 0, 1, 0};
		int[] dcwx = {1, 1, -1, -1};
		int[] dcwy = {-1, 1, 1, -1};
		int[][] aPos, bPos, BC;
		int T = Integer.parseInt(br.readLine());
		int M, A, ax, ay, bx, by, a, b;
		for(int tc = 1; tc <= T; tc++) {
			input1 = br.readLine().split(" ");
			M = Integer.parseInt(input1[0]);
			A = Integer.parseInt(input1[1]);
			ax = ay = 1;
			bx = by = 10;
			aPos = new int[M + 1][2];
			bPos = new int[M + 1][2];
			aPos[0][0] = ax;
			aPos[0][1] = ay;
			bPos[0][0] = bx;
			bPos[0][1] = by;
			input1 = br.readLine().split(" ");
			input2 = br.readLine().split(" ");
			for(int i = 1; i <= M; i++) {	// A, B의 시간별 위치 저장
				a = Integer.parseInt(input1[i - 1]);
				b = Integer.parseInt(input2[i - 1]);
				aPos[i][0] = ax += dx[a];
				aPos[i][1] = ay += dy[a];
				bPos[i][0] = bx += dx[b];
				bPos[i][1] = by += dy[b];
			}
			BC = new int[A][4];	// BC 입력받음
			for(int i = 0; i < A; i++) {
				int j = 0;
				for(String s :br.readLine().split(" ")) {
					BC[i][j++] = Integer.parseInt(s);
				}
			}
			
			// BC의 성능 순으로 내림차순 정렬
			Arrays.sort(BC, (o1, o2) -> {
				if(o1[3] != o2[3]) return o2[3] - o1[3];
				else return o2[2] - o1[2];
			});
			
			// BC 채우기
			map = new String[11][11];
			for(int i = 1; i <= 10; i++) {
				Arrays.fill(map[i], "");
			}
			char c = '0';
			for(int[] arr : BC) {
				a = arr[0];
				b = arr[1];
				map[a][b] += c;
				for(int i = 1, size = arr[2]; i <= size; i++) {
					a--;
					for(int j = 0; j < 4; j++) {
						for(int k = 1; k <= i; k++) {
							a += dcwx[j];
							b += dcwy[j];
							if(a > 0 && b > 0 && a <= 10 && b <= 10) {
								map[a][b] += c;
							}
						}
					}
				}
				c++;
 			}
			
			// 충전량 합의 최대값 구하기
			int sum = 0, s1Len, s2Len;
			String s1, s2;
			for(int i = 0; i <= M; i++) {
				ax = aPos[i][0];
				ay = aPos[i][1];
				bx = bPos[i][0];
				by = bPos[i][1];
				s1 = map[ax][ay];
				s2 = map[bx][by];
				s1Len = s1.length();
				s2Len = s2.length();
				if(s1Len == 0) {
					if(s2Len != 0) sum += BC[s2.charAt(0) - '0'][3];	// B만 BC에 위치
				} else if(s2Len == 0) {
					sum += BC[s1.charAt(0) - '0'][3];	// A만 BC에 위치
				} else {	// A, B 모두 하나 이상의 BC구역 내에 위치
					a = s1.charAt(0) - '0';
					b = s2.charAt(0) - '0';
					if(a != b) sum += (BC[a][3] + BC[b][3]);	// 서로 다른 BC
					else {	// 같은 BC에 위치할 경우
						if(s1Len == 1) {
							if(s2Len == 1) sum += BC[a][3];	// 인접 BC의 수 : A=1, B=1 
							else sum += (BC[a][3] + BC[s2.charAt(1) - '0'][3]); // 인접 BC의 수 : A=1, B>1 
						} else {
							if(s2Len == 1) sum += (BC[b][3] + BC[s1.charAt(1) - '0'][3]); // 인접 BC의 수 : A>1, B=1
							else sum += (BC[a][3] + Math.max(BC[s1.charAt(1) - '0'][3], BC[s2.charAt(1) - '0'][3])); // 인접 BC의 수 : A>1, B>1
						}
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
}
