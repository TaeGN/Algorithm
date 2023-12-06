package programmers.lv1.ok.푸드_파이트_대회;

import java.util.Arrays;

public class Solution1 {
    public static void main(String[] args) {
        int[] food = {1, 7, 1, 2};
        String answer = Solution(food);
        System.out.println("answer = " + answer);
    }

    private static String Solution(int[] food) {
        String result = "0";
        for(int i = food.length - 1; i > 0; i--) {
            for(int j = 0; j < food[i] / 2; j++) {
                result = i + result + i;
            }
        }
        return result;
    }
}
