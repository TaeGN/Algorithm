package 백준.Gold.G3.p16971_배열B의값;

import java.io.*;
import java.util.Arrays;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int M = nextInt();
        int[] rowSum = new int[N];
        int[] colSum = new int[M];
        int totalSum = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                int num = nextInt();
                rowSum[r] += (c == 0 || c == M - 1) ? num : 2 * num;
                colSum[c] += (r == 0 || r == N - 1) ? num : 2 * num;
            }
            totalSum += (r == 0 || r == N - 1) ? rowSum[r] : 2 * rowSum[r];
        }

        int maxTotalSum = totalSum;
        if (N > 2) {
            int rowDiff = Math.max(rowSum[0], rowSum[N - 1]) - Arrays.stream(rowSum).skip(1).limit(N - 2).min().orElseThrow();
            maxTotalSum = Math.max(maxTotalSum, totalSum + rowDiff);
        }
        if (M > 2) {
            int colDiff = Math.max(colSum[0], colSum[M - 1]) - Arrays.stream(colSum).skip(1).limit(M - 2).min().orElseThrow();
            maxTotalSum = Math.max(maxTotalSum, totalSum + colDiff);
        }
        System.out.println(maxTotalSum);
    }
}
