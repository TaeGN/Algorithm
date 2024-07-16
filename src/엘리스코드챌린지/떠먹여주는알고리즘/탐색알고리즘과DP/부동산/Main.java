package 엘리스코드챌린지.떠먹여주는알고리즘.탐색알고리즘과DP.부동산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int M = nextInt();
        int K = nextInt();
        int[][] totalPriceMatrix = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                totalPriceMatrix[i][j] = totalPriceMatrix[i][j - 1] + totalPriceMatrix[i - 1][j] - totalPriceMatrix[i - 1][j - 1] + nextInt();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();
            int d = nextInt();
            int price = totalPriceMatrix[c][d] - totalPriceMatrix[a - 1][d] - totalPriceMatrix[c][b - 1] + totalPriceMatrix[a - 1][b - 1];
            sb.append(price).append("\n");
        }
        System.out.println(sb);
    }
}
