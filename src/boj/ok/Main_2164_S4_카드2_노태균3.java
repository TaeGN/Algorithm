package boj.ok;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_2164_S4_카드2_노태균3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		br.close();
		
		int cnt = 1;
		int num = 1;
		int selectEven = 1; // 1 true, -1 false
		boolean isOdd;
		while(N > 1) {
			if(selectEven == 1) {
				num += cnt;
			} 
			
			isOdd = (N % 2 == 1);
			N /= 2;
			if(isOdd) {
				if(selectEven == -1) {
					N += 1;
				}
				selectEven *= -1;
			}
			cnt *= 2;
		}
		bw.append(num + "");
		bw.close();
	}
}
