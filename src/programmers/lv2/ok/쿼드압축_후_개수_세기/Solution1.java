package programmers.lv2.ok.쿼드압축_후_개수_세기;

import java.util.Arrays;

public class Solution1 {
	public static void main(String[] args) {
		int[][] arr = {
				{1,1,1,1,1,1,1,1},
				{0,1,1,1,1,1,1,1},
				{0,0,0,0,1,1,1,1},
				{0,1,0,0,1,1,1,1},
				{0,0,0,0,0,0,1,1},
				{0,0,0,0,0,0,0,1},
				{0,0,0,0,1,0,0,1},
				{0,0,0,0,1,1,1,1}
		};
		int[] answer = Solution(arr);
		System.out.println(Arrays.toString(answer));
	}
	
	static int cnt0 = 0;
	static int cnt1 = 0;
	private static int[] Solution(int[][] arr) {
		
		Compression(arr);
			
		int[] result = {cnt0, cnt1};
		return result;
	}

	private static void Compression(int[][] arr) {
		int len = arr.length;
		
		
		// 1x1일때 종료
		if(len == 1) { 	
			if(arr[0][0] == 0) {
				cnt0++;
			} else {
				cnt1++;
			}
			return;
		}
		
		boolean tf = true;
		for(int[] a : arr) {
			for(int b : a) {
				if(b != arr[0][0]) {
					int halfLen = arr.length / 2;
					int[][] arr1 = new int[halfLen][halfLen];
					int[][] arr2 = new int[halfLen][halfLen];
					int[][] arr3 = new int[halfLen][halfLen];
					int[][] arr4 = new int[halfLen][halfLen];
					for(int i = 0; i < halfLen; i++) {
						for(int j = 0; j < halfLen; j++) {
							arr1[i][j] = arr[i][j];
							arr2[i][j] = arr[i + halfLen][j];
							arr3[i][j] = arr[i][j + halfLen];
							arr4[i][j] = arr[i + halfLen][j + halfLen];
						}
					}
					Compression(arr1);
					Compression(arr2);
					Compression(arr3);
					Compression(arr4);
					tf = false;
					break;
				}
			}
			if(!tf) {
				break;
			}
		}
		if(tf) {
			if(arr[0][0] == 0) {
				cnt0++;
			} else {
				cnt1++;
			}
			return;
		}
	}
}
