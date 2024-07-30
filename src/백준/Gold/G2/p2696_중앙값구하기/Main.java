package 백준.Gold.G2.p2696_중앙값구하기;

import java.io.*;
import java.util.*;

class Median {
    PriorityQueue<Integer> lowerQueue, upperQueue;

    Median(int size) {
        lowerQueue = new PriorityQueue<>(size / 2 + 1, Comparator.reverseOrder());
        upperQueue = new PriorityQueue<>(size / 2 + 1, Comparator.naturalOrder());
    }

    void add(int num) {
        if (!lowerQueue.isEmpty() && lowerQueue.peek() < num) upperQueue.add(num);
        else lowerQueue.add(num);
        if (lowerQueue.size() - upperQueue.size() > 1) upperQueue.add(lowerQueue.poll());
        else if (lowerQueue.size() < upperQueue.size()) lowerQueue.add(upperQueue.poll());
    }

    int getMedian() {
        return lowerQueue.isEmpty() ? -1 : lowerQueue.peek();
    }
}

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int T = nextInt();
        for (int t = 0; t < T; t++) {
            int M = nextInt();
            Median median = new Median(M);
            sb.append((M + 1) / 2);
            for (int i = 0; i < M; i++) {
                if (i % 20 == 0) sb.append("\n");
                median.add(nextInt());
                if (i % 2 == 0) sb.append(median.getMedian()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
