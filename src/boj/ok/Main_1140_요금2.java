package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1140_요금2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		long T = Long.parseLong(input[0]);
		long K1 = Long.parseLong(input[1]);
		long P1 = Long.parseLong(input[2]);
		long K2 = Long.parseLong(input[3]);
		long P2 = Long.parseLong(input[4]);
		System.out.println(getResult(T, K1, P1, K2, P2));
	}

	private static long getResult(long T, long K1, long P1, long K2, long P2) {
		double avg1 = (double)P1 / K1;
		double avg2 = (double)P2 / K2;
		System.out.println(avg1);
		System.out.println(avg2);
		if(avg1 > 10) {
			if(avg2 > 10) return T * 10;
			if(T % K2 == 0) return (T / K2) * P2;
			return Math.min(((T / K2) + 1) * P2, (T / K2) * P2 + (T % K2) * 10);
		} else {
			if(avg2 > 10) {
				if(T % K1 == 0) return (T / K1) * P1;
				return Math.min(((T / K1) + 1) * P1, (T / K1) * P1 + (T % K1) * 10);
			} else {
//				System.out.println(Double.compare(avg1, avg2));
				PriorityQueue<Long> queue = new PriorityQueue<>();
				if(Double.compare(avg1, avg2) == 0) {
					if(T % K1 == 0) return (T / K1) * P1;
					if(T % K2 == 0) return (T / K2) * P2;
					queue.offer((T / K1 + 1) * P1);
					queue.offer((T / K2 + 1) * P2);
					if(K1 > K2) {
						long cnt = T / K1;
						long nT = T - K1 * cnt;
						if(nT % K1 == 0) queue.offer(P1 * cnt + (nT / K2) * P2);
						else queue.offer(P1 * cnt + Math.min(((nT / K2) + 1) * P2, (nT / K2) * P2 + (T % K2) * 10));
					} else {
						long cnt = T / K2;
						long nT = T - K2 * cnt;
						if(nT % K2 == 0) queue.offer(P2 * cnt + (nT / K1) * P1);
						else queue.offer(P2 * cnt + Math.min(((nT / K1) + 1) * P1, (nT / K1) * P1 + (T % K1) * 10));
					}
					System.out.println(queue);
					return queue.poll();
				}
				
				long mcf = getMaxCommonFactor(K1, K2);
				if(avg1 > avg2) {
					if(T % K2 == 0) return (T / K2) * P2;
					long cnt = T / K2;
					long len = K1 / mcf;
					queue.offer(P2 * (cnt + 1));
					for(int i = 0; i < len; i++) {
						queue.offer(P2 * cnt + Math.min((((T - K2 * cnt) / K1) + 1) * P1, ((T - K2 * cnt) / K1) * P1 + (T % K1) * 10));
						cnt--;
					}
				} 
				
				if(avg1 < avg2) {
					if(T % K1 == 0) return (T / K1) * P1;
					long cnt = T / K1;
					long len = K2 / mcf;
					queue.offer(P1 * (cnt + 1));
					for(int i = 0; i < len; i++) {
						queue.offer(P1 * cnt + Math.min((((T - K1 * cnt) / K2) + 1) * P2, ((T - K1 * cnt) / K2) * P2 + (T % K2) * 10));
						cnt--;
					}
				}
				System.out.println(queue);
				return queue.poll();
			}
		}
	}

	private static long getMaxCommonFactor(long k1, long k2) {
		if(k1 < k2) {
			long temp = k1;
			k1 = k2;
			k2 = temp;
		}
		
		while(k2 != 0) {
			long temp = k1 % k2;
			k1 = k2;
			k2 = temp;
		}
		
		return k1;
	}
}
