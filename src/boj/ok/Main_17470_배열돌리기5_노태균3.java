package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17470_배열돌리기5_노태균3 {
	static int N, M, R;
	static Point arr[][];
	static int reversalUD = -1;
	static int reversalLR = -1;
	static int rotate = 0;
	static int position = 0;
	static int isRotate = -1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		R = Integer.parseInt(input[2]);
		arr = new Point[N][M];
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = new Point(i, j, Integer.parseInt(input[j]));
			}
		}
		
		String[] cal = br.readLine().split(" ");
		for (int i = 0; i < R; i++) {
			switch(Integer.parseInt(cal[i])) {
			case 1:
				reversalUD *= -1;
				break;
			case 2:
				reversalLR *= -1;
				break;
			case 3:
				rotate = (rotate + 1) % 4;
				break;
			case 4:
				rotate = (rotate + 3) % 4;
				break;
			case 5:
				position = (rotate + 1) % 4;
				break;
			case 6:
				position = (rotate + 3) % 4;
				break;
			}
		}
		
		if(reversalUD == 1) rotate(1);
		if(reversalLR == 1) rotate(2);
		for (int i = 0, end = rotate; i < end; i++) {
			rotate(3);
		}
		for (int i = 0, end = position; i < end; i++) {
			rotate(5);
		}
		
		int[][] result = null;
		int n = N, m = M;
		if(rotate == 1) {
			n = M;
			m = N;
		}
		result = new int[n][m];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result[arr[i][j].r][arr[i][j].c] = arr[i][j].v;
			}
		}
		
		for (int i = 0; i <n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
//	static String calculation(String prev, String next) {
//		switch(prev + next) {
//		case "11":case "22":case "34":case "43":case "56":case "65":
//			return null;
//		case "12":case "21":case "33":case "44":
//			rotate(33);
//			return null;
//		case "13":case "24":case "32":case "41":
//			rotate(4);
//			return "1";
//		case "14":case "23":case "42":case "31":
//			rotate(3);
//			return "1";
//		case "55":case "66":
//			return "1";
//		case "16":case "61":
//			return "5";
//		case "15":case "51":
//			return "6";
//		default:
//			rotate(Integer.parseInt(prev));
//			return next;
//		}
//	}
	
	static void rotate(int num) {
		int n = N, m = M;
		if(isRotate == 1) {
			n = M;
			m = N;
		}
		switch(num) {
		case 1:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					arr[i][j].r = n - 1 - arr[i][j].r;
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					arr[i][j].c = m - 1 - arr[i][j].c;
				}
			}
			break;
		case 3:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int temp = arr[i][j].r;
					arr[i][j].r = arr[i][j].c;
					arr[i][j].c = n - 1 - temp;
				}
			}
			isRotate *= -1;
			break;
		case 4:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int temp = arr[i][j].r;
					arr[i][j].r = m - 1 - arr[i][j].c;
					arr[i][j].c = temp;
				}
			}
			isRotate *= -1;
			break;
		case 5:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int c = arr[i][j].c;
					if(c / (m / 2) == 0) arr[i][j].c += m / 2;
					else {
						arr[i][j].r = (arr[i][j].r + n / 2) % n;
						arr[i][j].c -= m / 2;
					}
				}
			}
			break;
		case 6:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int c = arr[i][j].c;
					if(c / (m / 2) == 0) {
						arr[i][j].r = (arr[i][j].r + n / 2) % n;
						arr[i][j].c += m / 2;
					}
					else arr[i][j].c -= m / 2;
				}
			}
			break;
		case 33:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					arr[i][j].r = n - 1 - arr[i][j].r;
					arr[i][j].c = m - 1 - arr[i][j].c;
				}
			}
			break;
		}
		
	}
	
	static class Point {
		int r, c, v;

		public Point(int r, int c, int v) {
			super();
			this.r = r;
			this.c = c;
			this.v = v;
		}
	}
}
