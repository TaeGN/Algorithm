package 백준.Gold.G1.p14438_수열과쿼리17;

import java.io.*;

class SegmentTree {
    int[] tree;
    int[] arr;

    SegmentTree(int[] arr) {
        this.arr = arr;
        int arrSize = arr.length;
        int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
        int treeSize = (int) Math.pow(2, h + 1);
        tree = new int[treeSize];
        init();
    }

    private void init() {
        init(1, 0, arr.length - 1);
    }

    private int init(int node, int start, int end) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        int leftNode = init(node * 2, start, mid);
        int rightNode = init(node * 2 + 1, mid + 1, end);
        return tree[node] = Math.min(leftNode, rightNode);
    }

    void set(int idx, int value) {
        arr[idx] = value;
        set(1, 0, arr.length - 1, idx, value);
    }

    private int set(int node, int start, int end, int idx, int value) {
        if (idx < start || end < idx) return tree[node];
        if (start == end) return tree[node] = value;
        int mid = (start + end) / 2;
        int leftNode = set(node * 2, start, mid, idx, value);
        int rightNode = set(node * 2 + 1, mid + 1, end, idx, value);
        return tree[node] = Math.min(leftNode, rightNode);
    }

    int query(int left, int right) {
        return query(1, 0, arr.length - 1, left, right);
    }

    private int query(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return Integer.MAX_VALUE;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        int leftQuery = query(node * 2, start, mid, left, right);
        int rightQuery = query(node * 2 + 1, mid + 1, end, left, right);
        return Math.min(leftQuery, rightQuery);
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
        int[] numArr = new int[N];
        for (int i = 0; i < N; i++) {
            numArr[i] = nextInt();
        }
        SegmentTree tree = new SegmentTree(numArr);
        StringBuilder sb = new StringBuilder();
        int M = nextInt();
        for (int i = 0; i < M; i++) {
            if (nextInt() == 1) tree.set(nextInt() - 1, nextInt());
            else sb.append(tree.query(nextInt() - 1, nextInt() - 1)).append("\n");
        }
        System.out.println(sb);
    }
}
