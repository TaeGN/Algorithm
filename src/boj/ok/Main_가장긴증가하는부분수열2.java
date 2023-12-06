package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_가장긴증가하는부분수열2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		List<Integer> LIS = new ArrayList<>();
		LIS.add(0);
		int size = 0;
		
		for (String str : br.readLine().split(" ")) {
			int num = Integer.parseInt(str);
			
			// 증가하는 값 나오면 LIS에 추가 + size++
			if(LIS.get(size) < num) {
				LIS.add(num);
				size++;
			} else {
				int idx = Collections.binarySearch(LIS, num);
				if(idx < 0) {
					idx = -idx - 1;
					LIS.set(idx, num);
				}
			}
		}
		br.close();
		System.out.println(size);
	}
}
