package 백준.Gold.G4.p20166_문자열지옥에빠진호석;

import java.io.*;
import java.util.*;

public class Main {
    private static final int MAX_WORD_LENGTH = 5;
    private static final int[] dr = {1, 0, -1, 0, 1, 1, -1, -1};
    private static final int[] dc = {0, 1, 0, -1, 1, -1, 1, -1};
    private static int N, M;
    private static char[][] map;
    private static Map<String, Integer>[][][] wordMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        wordMap = new HashMap[N][M][MAX_WORD_LENGTH + 1];
        Map<String, Integer> totalWordMap = new HashMap<>();
        for (int r = 0; r < N; r++) {
            String row = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = row.charAt(c);
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                for (int length = 1; length <= MAX_WORD_LENGTH; length++) {
                    for (Map.Entry<String, Integer> entry : getMap(r, c, length).entrySet()) {
                        totalWordMap.compute(entry.getKey(), (k, v) -> v == null ? entry.getValue() : v + entry.getValue());
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            sb.append(totalWordMap.getOrDefault(br.readLine(), 0)).append("\n");
        }
        System.out.println(sb);
    }

    private static Map<String, Integer> getMap(int r, int c, int length) {
        if (wordMap[r][c][length] != null) return wordMap[r][c][length];
        Map<String, Integer> curMap = (wordMap[r][c][length] = new HashMap<>());
        if (length == 1) wordMap[r][c][length].put("" + map[r][c], 1);
        else {
            for (int d = 0; d < dr.length; d++) {
                int nr = (r + dr[d] + N) % N;
                int nc = (c + dc[d] + M) % M;
                for (Map.Entry<String, Integer> entry : getMap(nr, nc, length - 1).entrySet()) {
                    String word = map[r][c] + entry.getKey();
                    int count = entry.getValue();
                    curMap.compute(word, (k, v) -> v == null ? count : v + count);
                }
            }
        }
        return curMap;
    }
}
