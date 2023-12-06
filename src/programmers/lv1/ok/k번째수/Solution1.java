package programmers.lv1.ok.k번째수;

import java.util.Arrays;

public class Solution1 {
	public static void main(String[] args) {
		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
		System.out.println(Solution(array, commands));
	}
	
    public static int[] Solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int[] curArr;
        int i = 0;
        for(int[] command : commands) {
            curArr = array.clone();
            Arrays.sort(curArr, command[0] - 1, command[1]);
            System.out.println(Arrays.toString(curArr));
            answer[i++] = curArr[command[0] + command[2] - 2];
        }
        return answer;
    }
}
