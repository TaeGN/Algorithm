package 백준.Gold.G4.p1915_가장큰정사각형;

import java.util.*;
import java.io.*;

public class Main {
    private static int n, m;
    private static int[][] sumMatrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sumMatrix = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String row = br.readLine();
            for (int j = 1; j <= m; j++) {
                sumMatrix[i][j] = Character.digit(row.charAt(j - 1), 10) + sumMatrix[i - 1][j] + sumMatrix[i][j - 1] - sumMatrix[i - 1][j - 1];
            }
        }
        int maxSquareLength = maxSquareLength(0, Math.min(n, m));
        System.out.println(maxSquareLength * maxSquareLength);
    }

    private static int maxSquareLength(int start, int end) {
        int mid = (start + end) / 2;
        if (start == mid) return isPossible(end) ? end : start;
        if (isPossible(mid)) return maxSquareLength(mid, end);
        return maxSquareLength(start, mid - 1);
    }

    private static boolean isPossible(int length) {
        int sum = length * length;
        for (int r = 0; r < n - length + 1; r++) {
            for (int c = 0; c < m - length + 1; c++) {
                int curSum = sumMatrix[r + length][c + length] - sumMatrix[r][c + length] - sumMatrix[r + length][c] + sumMatrix[r][c];
                if (curSum == sum) return true;
            }
        }
        return false;
    }
}
