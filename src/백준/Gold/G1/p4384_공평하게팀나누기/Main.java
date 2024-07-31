package 백준.Gold.G1.p4384_공평하게팀나누기;

import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int[] numArr = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += (numArr[i] = nextInt());
        }

        int maxSize = N / 2;
        boolean[][] dp = new boolean[maxSize + 1][sum + 1];
        dp[0][0] = true;
        for (int i = 0; i < N; i++) {
            for (int j = maxSize; j >= 1; j--) {
                for (int w = sum; w >= numArr[i]; w--) {
                    dp[j][w] |= dp[j - 1][w - numArr[i]];
                }
            }
        }

        for (int w = sum % 2 == 0 ? 0 : 1; w <= sum; w += 2) {
            int a = sum / 2 - w / 2;
            int b = (sum - sum / 2) + w / 2;
            if (dp[maxSize][a] || dp[maxSize][b]) {
                System.out.println(a + " " + b);
                break;
            }
        }
    }
}
