package 백준.Platinum.P3.p1395_스위치;

import java.io.*;

class SegmentTree {
    int[] tree;
    boolean[] lazy;
    int[] arr;

    SegmentTree(int arrSize) {
        this.arr = new int[arrSize];
        int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
        int treeSize = 2 << h;
        tree = new int[treeSize];
        lazy = new boolean[treeSize];
    }

    void updateRange(int left, int right) {
        updateRange(1, 0, arr.length - 1, left, right);
    }

    private void updateRange(int node, int start, int end, int left, int right) {
        updateLazy(node, start, end);
        if (end < left || right < start) return;
        if (left <= start && end <= right) {
            tree[node] = end - start + 1 - tree[node];
            if (start != end) {
                lazy[node * 2] = !lazy[node * 2];
                lazy[node * 2 + 1] = !lazy[node * 2 + 1];
            }
            return;
        }
        int mid = (start + end) / 2;
        updateRange(node * 2, start, mid, left, right);
        updateRange(node * 2 + 1, mid + 1, end, left, right);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    void updateLazy(int node, int start, int end) {
        if (!lazy[node]) return;
        tree[node] = end - start + 1 - tree[node];
        if (start != end) {
            lazy[node * 2] = !lazy[node * 2];
            lazy[node * 2 + 1] = !lazy[node * 2 + 1];
        }
        lazy[node] = false;
    }

    int query(int left, int right) {
        return query(1, 0, arr.length - 1, left, right);
    }

    private int query(int node, int start, int end, int left, int right) {
        updateLazy(node, start, end);
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        int leftNode = query(node * 2, start, mid, left, right);
        int rightNode = query(node * 2 + 1, mid + 1, end, left, right);
        return leftNode + rightNode;
    }
}

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int M = nextInt();
        SegmentTree tree = new SegmentTree(N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            if (nextInt() == 0) tree.updateRange(nextInt() - 1, nextInt() - 1);
            else sb.append(tree.query(nextInt() - 1, nextInt() - 1)).append("\n");
        }
        System.out.println(sb);
    }
}
