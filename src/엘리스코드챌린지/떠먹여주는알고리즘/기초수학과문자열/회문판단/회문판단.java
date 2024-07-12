package 엘리스코드챌린지.떠먹여주는알고리즘.기초수학과문자열.회문판단;

import java.io.*;

public class 회문판단 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(isPalindrome(br.readLine()) ? "YES" : "NO");
    }

    private static boolean isPalindrome(String s) {
        int length = s.length();
        for(int i = 0; i < length / 2; i++) {
            if(s.charAt(i) != s.charAt(length - 1 - i)) return false;
        }
        return true;
    }
}
