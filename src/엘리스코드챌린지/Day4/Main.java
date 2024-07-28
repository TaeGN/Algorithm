package 엘리스코드챌린지.Day4;

import java.io.*;
import java.util.*;

class Main {
    private static final int EMPTY = Integer.MAX_VALUE;
    private static ArrayList<Integer>[] nodeLists;
    private static int[] maxScoreDiffArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        maxScoreDiffArr = new int[N + 1];
        nodeLists = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            maxScoreDiffArr[i] = EMPTY;
            nodeLists[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodeLists[u].add(v);
            nodeLists[v].add(u);
        }
        br.close();

        setMaxScoreDiffArr();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb.append(maxScoreDiffArr[i] >= 0 ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }

    private static void setMaxScoreDiffArr() {
        maxScoreDiff(1, new boolean[maxScoreDiffArr.length]);
    }

    private static int maxScoreDiff(int node, boolean[] visited) {
        if(maxScoreDiffArr[node] != EMPTY) return maxScoreDiffArr[node];
        visited[node] = true;
        int maxScoreDiff = Integer.MIN_VALUE;
        for(int nextNode: nodeLists[node]) {
            if(visited[nextNode]) continue;
            maxScoreDiff = Math.max(maxScoreDiff, -maxScoreDiff(nextNode, visited));
        }
        if(maxScoreDiff == Integer.MIN_VALUE) maxScoreDiff = 0;
        return maxScoreDiffArr[node] = node + maxScoreDiff;
    }
}
