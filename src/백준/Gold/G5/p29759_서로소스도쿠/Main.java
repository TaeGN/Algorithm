package 백준.Gold.G5.p29759_서로소스도쿠;

import java.io.*;
import java.util.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static final int MAX_NUMBER = 1_000_000;
    private static final int EMPTY = 0;

    public static void main(String[] args) throws IOException {
        boolean[] isNotPrime = new boolean[MAX_NUMBER + 1];
        for (int primeNum = 2; primeNum * primeNum < isNotPrime.length; primeNum++) {
            if (isNotPrime[primeNum]) continue;
            for (int notPrimeNum = primeNum * primeNum; notPrimeNum < isNotPrime.length; notPrimeNum += primeNum) {
                isNotPrime[notPrimeNum] = true;
            }
        }

        int N = nextInt();
        Set<Integer>[] rowSet = new HashSet[N * N];
        Set<Integer>[] colSet = new HashSet[N * N];
        Set<Integer>[][] blockSet = new HashSet[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rowSet[i * N + j] = new HashSet<>();
                colSet[i * N + j] = new HashSet<>();
                blockSet[i][j] = new HashSet<>();
            }
        }

        int[][] map = new int[N * N][N * N];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map.length; c++) {
                int num = nextInt();
                map[r][c] = num;
                if (num != EMPTY) {
                    rowSet[r].add(num);
                    colSet[c].add(num);
                    blockSet[r / N][c / N].add(num);
                }
            }
        }

        int primeNum = 1;
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map.length; c++) {
                if (map[r][c] != EMPTY) {
                    sb.append(map[r][c]).append(" ");
                    continue;
                }
                a: while (true) {
                    if(isNotPrime[++primeNum]) continue;
                    for (int num : rowSet[r]) {
                        if (gcdIsNotOne(primeNum, num)) continue a;
                    }
                    for (int num : colSet[c]) {
                        if (gcdIsNotOne(primeNum, num)) continue a;
                    }
                    for (int num : blockSet[r / N][c / N]) {
                        if (gcdIsNotOne(primeNum, num)) continue a;
                    }
                    sb.append(primeNum).append(" ");
                    break;
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static boolean gcdIsNotOne(int a, int b) {
        return gcd(a, b) != 1;
    }

    private static int gcd(int a, int b) {
        if (a < b) return gcd(b, a);
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
