package 백준.Gold.G3.p14890_경사로;

import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static int N, L;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        N = nextInt();
        L = nextInt();
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                map[r][c] = nextInt();
            }
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (isPossible(i, i, 0, N - 1)) count++;
            if (isPossible(0, N - 1, i, i)) count++;
        }
        System.out.println(count);
    }

    private static boolean isPossible(int sr, int er, int sc, int ec) {
        int prevNum = map[sr][sc];
        int count = 0;
        for (int r = sr; r <= er; r++) {
            for (int c = sc; c <= ec; c++) {
                if (Math.abs(map[r][c] - prevNum) > 1) return false;
                if (map[r][c] == prevNum) count++;
                else {
                    if (map[r][c] == prevNum + 1) {
                        if (count < L) return false;
                        count = 1;
                    } else if (map[r][c] == prevNum - 1) {
                        if (count < 0) return false;
                        count = -L + 1;
                    }
                    prevNum = map[r][c];
                }
            }
        }
        return count >= 0;
    }
}
