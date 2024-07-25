package 백준.Gold.G4.p31235_올라올라;

import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int prevMax = 0;
        int prevMaxIdx = 0;
        int minK = 0;
        for (int i = 0; i < N; i++) {
            int num = nextInt();
            if (prevMax <= num) {
                minK = Math.max(minK, i - prevMaxIdx);
                prevMax = num;
                prevMaxIdx = i;
            }
        }
        minK = Math.max(minK, N - prevMaxIdx);
        System.out.println(minK);
    }
}
