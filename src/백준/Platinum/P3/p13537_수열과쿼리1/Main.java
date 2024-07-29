package 백준.Platinum.P3.p13537_수열과쿼리1;

import java.io.*;
import java.util.Arrays;

class SegmentTree {
    int[][] tree;
    int[] arr;

    SegmentTree(int[] arr) {
        this.arr = arr;
        int arrSize = arr.length;
        int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
        int treeSize = (int) Math.pow(2, h + 1);
        tree = new int[treeSize][];
        init();
    }

    private void init() {
        init(1, 0, arr.length - 1);
    }

    private int[] init(int node, int start, int end) {
        if (start == end) return tree[node] = new int[]{arr[start]};
        int mid = (start + end) / 2;
        int[] leftNode = init(node * 2, start, mid);
        int[] rightNode = init(node * 2 + 1, mid + 1, end);
        return tree[node] = mergedArr(leftNode, rightNode);
    }

    private int[] mergedArr(int[] left, int[] right) {
        int[] newArr = new int[left.length + right.length];
        for (int i = 0, leftIdx = 0, rightIdx = 0; i < newArr.length; i++) {
            if (leftIdx == left.length) newArr[i] = right[rightIdx++];
            else if (rightIdx == right.length) newArr[i] = left[leftIdx++];
            else if (left[leftIdx] <= right[rightIdx]) newArr[i] = left[leftIdx++];
            else newArr[i] = right[rightIdx++];
        }
        return newArr;
    }

    int countByGreaterThanK(int left, int right, int K) {
        return countByGreaterThanK(1, 0, arr.length - 1, left, right, K);
    }

    private int countByGreaterThanK(int node, int start, int end, int left, int right, int K) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) {
            int bsIdx = Arrays.binarySearch(tree[node], K + 1);
            if (bsIdx < 0) bsIdx = -bsIdx - 1;
            return tree[node].length - bsIdx;
        }
        int mid = (start + end) / 2;
        int leftCount = countByGreaterThanK(node * 2, start, mid, left, right, K);
        int rightCount = countByGreaterThanK(node * 2 + 1, mid + 1, end, left, right, K);
        return leftCount + rightCount;
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
            sb.append(tree.countByGreaterThanK(nextInt() - 1, nextInt() - 1, nextInt())).append("\n");
        }
        System.out.println(sb);
    }
}
