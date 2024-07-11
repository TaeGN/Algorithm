package 엘리스코드챌린지.떠먹여주는알고리즘.올바른괄호문자열;

import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
        System.out.println(isValidBrackets(s) ? "YES" : "NO");
    }

    private static boolean isValidBrackets(String s) {
        int bracketsCount = 0;
        for(int i = 0; i < s.length(); i++) {
            switch(s.charAt(i)) {
                case '(':
                    bracketsCount++;
                    break;
                case ')':
                    if(--bracketsCount < 0) return false;
                    break;
            }
        }
        return bracketsCount == 0;
    }
}
