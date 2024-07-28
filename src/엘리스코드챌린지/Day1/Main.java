package 엘리스코드챌린지.Day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        String num = new BufferedReader(new InputStreamReader(System.in)).readLine();
        System.out.println(nextNumber(num));
    }

    private static String nextNumber(String num) {
        StringBuilder sb = new StringBuilder();
        for (int i = num.length() - 2; i >= 0; i--) {
            for (int j = num.length() - 1; j > i; j--) {
                if (num.charAt(i) < num.charAt(j)) {
                    sb.append(num, 0, i);
                    sb.append(num.charAt(j));
                    char[] cArr = (num.substring(i + 1, j) + num.charAt(i) + num.substring(j + 1)).toCharArray();
                    Arrays.sort(cArr);
                    sb.append(String.valueOf(cArr));
                    return sb.toString();
                }
            }
        }
        return "-1";
    }
}
