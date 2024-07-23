package 백준.Gold.G2.p23743_방탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;

class Structure implements Comparable<Structure> {
    int id1, id2, time;

    Structure(int id1, int id2, int time) {
        this.id1 = id1;
        this.id2 = id2;
        this.time = time;
    }

    @Override
    public int compareTo(Structure o) {
        return Integer.compare(time, o.time);
    }
}

class Warp extends Structure {
    Warp(int id1, int id2, int time) {
        super(id1, id2, time);
    }
}

class Exit extends Structure {
    Exit(int id, int time) {
        super(0, id, time);
    }
}

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int M = nextInt();
        setParents(N);
        PriorityQueue<Structure> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            pq.add(new Warp(nextInt(), nextInt(), nextInt()));
        }
        for (int i = 1; i <= N; i++) {
            pq.add(new Exit(i, nextInt()));
        }
        int minTotalTime = 0;
        int warpCount = 0;
        int count = 0;
        while (count < N && !pq.isEmpty()) {
            Structure structure = pq.poll();
            boolean isWarp = structure instanceof Warp;
            if ((isWarp && warpCount >= M) || !union(structure.id1, structure.id2)) continue;
            minTotalTime += structure.time;
            if (isWarp) warpCount++;
            count++;
        }
        System.out.println(minTotalTime);
    }

    private static void setParents(int N) {
        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
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
