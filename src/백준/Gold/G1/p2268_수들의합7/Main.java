package 백준.Gold.G1.p2268_수들의합7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class SegmentTree {
    long[] tree;
    int[] arr;

    SegmentTree(int arrSize) {
        arr = new int[arrSize];
        int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
        int treeSize = (int) Math.pow(2, h + 1);
        tree = new long[treeSize];
    }

    long sum(int idx1, int idx2) {
        int left = Math.min(idx1, idx2);
        int right = Math.max(idx1, idx2);
        return sum(1, 0, arr.length - 1, left, right);
    }

    private long sum(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        long leftSum = sum(node * 2, start, mid, left, right);
        long rightSum = sum(node * 2 + 1, mid + 1, end, left, right);
        return leftSum + rightSum;
    }

    void modify(int idx, int value) {
        int diff = value - arr[idx];
        arr[idx] = value;
        modify(1, 0, arr.length - 1, idx, diff);
    }

    private void modify(int node, int start, int end, int idx, int diff) {
        if (idx < start || end < idx) return;
        tree[node] += diff;
        if (start == end) return;
        int mid = (start + end) / 2;
        modify(node * 2, start, mid, idx, diff);
        modify(node * 2 + 1, mid + 1, end, idx, diff);
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
            if (nextInt() == 0) sb.append(tree.sum(nextInt() - 1, nextInt() - 1)).append("\n");
            else tree.modify(nextInt() - 1, nextInt());
        }
        System.out.println(sb);
    }
}
