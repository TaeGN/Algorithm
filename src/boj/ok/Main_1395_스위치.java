package boj.ok;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_1395_스위치 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] sArr = br.readLine().split(" ");
		int N = Integer.parseInt(sArr[0]);
		int M = Integer.parseInt(sArr[1]);
		int j, k, cnt;
		boolean[] onOff = new boolean[N + 1];
		for(int i = 0; i < M; i++) {
			sArr = br.readLine().split(" ");
			j = Integer.parseInt(sArr[1]);
			k = Integer.parseInt(sArr[2]);
			if(sArr[0].equals("0")) {
				for(; j <= k; j++) {
					if(onOff[j]) onOff[j] = false;
					else onOff[j] = true;
				}
			} else {
				for(cnt = 0; j <= k; j++) {
					if(onOff[j]) cnt++;
				}
				bw.append(cnt + "\n");
			}
		}
		br.close();
		bw.close();
	}
}
