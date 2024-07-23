package 백준.Gold.G4.p19600_이진삼진탐색놀이2;

import java.io.*;
import java.util.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static final int MAX_N = 5000;
    private static final int[][] numArr = new int[MAX_N + 1][];

    public static void main(String[] args) throws IOException {
        int Q = nextInt();
        for (int i = 1; i <= MAX_N; i++) {
            numArr[i] = new int[i];
            Arrays.fill(numArr[i], Integer.MAX_VALUE);
            setTernaryCount(numArr[i]);
            setBinaryCount(numArr[i]);
            for (int j = 1; j < i; j++) {
                numArr[i][j] += numArr[i][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int N = nextInt();
            int S = nextInt();
            int E = nextInt();
            sb.append(numArr[N][E] - (S > 0 ? numArr[N][S - 1] : 0)).append("\n");
        }
        System.out.println(sb);
    }

    private static void setTernaryCount(int[] arr) {
        setTernaryCount(arr, 0, arr.length - 1, 0);
    }

    private static void setTernaryCount(int[] arr, int left, int right, int count) {
        if (left > right) return;
        int left_third = left + (right - left) / 3;
        int right_third = right - (right - left) / 3;
        arr[left_third] = Math.min(arr[left_third], count);
        arr[right_third] = Math.min(arr[right_third], count + 1);
        setTernaryCount(arr, left, left_third - 1, count + 2);
        setTernaryCount(arr, left_third + 1, right_third - 1, count + 2);
        setTernaryCount(arr, right_third + 1, right, count + 2);
    }

    private static void setBinaryCount(int[] arr) {
        setBinaryCount(arr, 0, arr.length - 1, 0);
    }

    private static void setBinaryCount(int[] arr, int left, int right, int count) {
        if (left > right) return;
        int mid = (left + right) / 2;
        arr[mid] -= count;
        setBinaryCount(arr, left, mid - 1, count + 1);
        setBinaryCount(arr, mid + 1, right, count + 1);
    }
}
