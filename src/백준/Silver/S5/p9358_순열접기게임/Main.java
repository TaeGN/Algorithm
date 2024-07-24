package 백준.Silver.S5.p9358_순열접기게임;

import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static final int MAX_N = 100;

    public static void main(String[] args) throws IOException {
        int T = nextInt();
        int[] numArr = new int[MAX_N];
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            int N = nextInt();
            for (int i = 0; i < N; i++) {
                numArr[i] = nextInt();
            }
            for (int length = N; length > 2; length = (length + 1) / 2) {
                int half = (length + 1) / 2;
                for (int i = 0; i < half; i++) {
                    numArr[i] += numArr[length - 1 - i];
                }
            }
            sb.append("Case #").append(tc).append(": ").append(numArr[0] > numArr[1] ? "Alice" : "Bob").append("\n");
        }
        System.out.println(sb);
    }
}
