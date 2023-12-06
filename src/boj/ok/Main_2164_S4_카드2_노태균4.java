package boj.ok;

import java.util.Scanner;

public class Main_2164_S4_카드2_노태균4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		
		int cnt = 1;
		int num = 1;
		int selectEven = 1; // 1 true, -1 false
		while(N > 1) {
			if(selectEven == 1) {
				num += cnt;
			}
			
			if(N % 2 == 1) {
				if(selectEven == -1) {
					N = N / 2 + 1;
				} else {
					N /= 2;
				}
				selectEven *= -1;
			} else {
				N /= 2;
			}
			cnt *= 2;
		}
		
		System.out.println(num);
	}
}
