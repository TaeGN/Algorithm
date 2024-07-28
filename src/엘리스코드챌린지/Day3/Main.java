package 엘리스코드챌린지.Day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(unzipStrLength(br.readLine()));
    }

    private static int unzipStrLength(String s) {
        int totalLength = 0;
        for (int i = 0, bracketCount = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                totalLength--;
                bracketCount++;
                int repeatCount = s.charAt(i - 1) - '0';
                int fromIndex = i + 1;
                while (bracketCount != 0) {
                    switch (s.charAt(++i)) {
                        case '(':
                            bracketCount++;
                            break;
                        case ')':
                            bracketCount--;
                            break;
                    }
                }
                int toIndex = i;
                totalLength += repeatCount * unzipStrLength(s.substring(fromIndex, toIndex));
            } else {
                totalLength++;
            }
        }
        return totalLength;
    }
}
