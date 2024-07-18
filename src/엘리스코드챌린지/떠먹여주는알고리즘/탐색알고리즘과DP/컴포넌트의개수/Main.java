package 엘리스코드챌린지.떠먹여주는알고리즘.탐색알고리즘과DP.컴포넌트의개수;

import java.io.*;

class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static int[] parents;

    private static void initParents(int N) {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static void union(int id1, int id2) {
        parents[find(id2)] = find(id1);
    }

    private static int find(int id) {
        if (parents[id] == id) return id;
        return parents[id] = find(parents[id]);
    }

    private static int componentCount() {
        int count = 0;
        for (int i = 1; i < parents.length; i++) {
            if (parents[i] == i) count++;
        }
        return count;
    }


    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int M = nextInt();
        initParents(N);
        for (int i = 0; i < M; i++) {
            union(nextInt(), nextInt());
        }
        System.out.println(componentCount());
    }
}