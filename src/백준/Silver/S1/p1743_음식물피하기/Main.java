package 백준.Silver.S1.p1743_음식물피하기;

import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, -1, 0, 1};
    private static boolean[][] hasTrashMatrix;

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int M = nextInt();
        int K = nextInt();
        hasTrashMatrix = new boolean[N + 2][M + 2];
        int maxTrashSize = 0;
        for (int i = 0; i < K; i++) {
            hasTrashMatrix[nextInt()][nextInt()] = true;
        }

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                if (hasTrashMatrix[r][c]) {
                    maxTrashSize = Math.max(maxTrashSize, trashSize(r, c));
                }
            }
        }
        System.out.println(maxTrashSize);
    }

    private static int trashSize(int r, int c) {
        hasTrashMatrix[r][c] = false;
        int size = 1;
        for (int d = 0; d < dr.length; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (hasTrashMatrix[nr][nc]) size += trashSize(nr, nc);
        }
        return size;
    }

}
