package 백준.Gold.G5.p14728_벼락치기;

import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int T = nextInt();
        int[] maxScoreByTimeArr = new int[T + 1];
        for (int i = 0; i < N; i++) {
            int K = nextInt();
            int S = nextInt();
            for (int time = T; time >= K; time--) {
                maxScoreByTimeArr[time] = Math.max(maxScoreByTimeArr[time], maxScoreByTimeArr[time - K] + S);
            }
        }
        System.out.println(maxScoreByTimeArr[T]);
    }
}
