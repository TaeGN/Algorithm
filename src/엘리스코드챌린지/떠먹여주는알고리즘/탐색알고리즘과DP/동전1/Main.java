package 엘리스코드챌린지.떠먹여주는알고리즘.탐색알고리즘과DP.동전1;


import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        System.out.println(N / 100 + (N % 100 > 0 ? (N % 100 - 1) / 10 + 1 : 0));
    }
}
