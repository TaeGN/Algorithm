package 엘리스코드챌린지.Day10;

import java.util.*;
import java.io.*;

class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static final int MAX_N = 50_000;
    private static final int[] sumCardCounts = new int[MAX_N + 1];
    private static int N;
    private static int[] cards;

    public static void main(String[] args) throws IOException {
        N = nextInt();
        cards = new int[N];
        for(int i = 0; i < N; i++) {
            cards[i] = nextInt();
        }
        System.out.println(maxK(1, N));
    }

    private static int maxK(int start, int end) {
        int mid = (start + end) / 2;
        if(start == mid) return isPossible(end) ? end : start;
        if(isPossible(mid)) return maxK(mid, end);
        return maxK(start, mid - 1);
    }

    private static boolean isPossible(int K) {
        Arrays.fill(sumCardCounts, 1, K + 1, 0);
        for(int i = 0; i < N; i++) {
            sumCardCounts[cards[i]]++;
            if(i >= K) sumCardCounts[cards[i - K]]--;
            if(i >= K - 1 && isPossibleInUnitArray(K)) return true;
        }
        return false;
    }

    private static boolean isPossibleInUnitArray(int K) {
        int sum = 0;
        for(int i = 1; i <= K; i++) {
            if((sum += sumCardCounts[i]) > i) return false;
        }
        return true;
    }
}
