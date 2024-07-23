package 백준.Gold.G4.p21924_도시건설;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static final int A = 0;
    private static final int B = 1;
    private static final int PRICE = 2;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int M = nextInt();
        setParents(N);
        PriorityQueue<int[]> pq = new PriorityQueue<>(M, Comparator.comparingInt(o -> o[PRICE]));
        long totalPrice = 0;
        for (int i = 0; i < M; i++) {
            int[] road = new int[]{nextInt(), nextInt(), nextInt()};
            totalPrice += road[PRICE];
            pq.add(road);
        }

        int count = 1;
        long minTotalPrice = 0;
        while (count < N && !pq.isEmpty()) {
            int[] road = pq.poll();
            if (!union(road[A], road[B])) continue;
            minTotalPrice += road[PRICE];
            count++;
        }

        System.out.println(count == N ? totalPrice - minTotalPrice : -1);
    }

    private static void setParents(int N) {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static boolean union(int id1, int id2) {
        int groupId1 = find(id1);
        int groupId2 = find(id2);
        if (groupId1 == groupId2) return false;
        parents[groupId2] = groupId1;
        return true;
    }

    private static int find(int id) {
        if (parents[id] == id) return id;
        return parents[id] = find(parents[id]);
    }
}
