package 백준.Bronze.B3.p10707_수도요금;

import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int A = nextInt();
        int B = nextInt();
        int C = nextInt();
        int D = nextInt();
        int P = nextInt();
        System.out.println(Math.min(A * P, B + Math.max(P - C, 0) * D));
    }
}
