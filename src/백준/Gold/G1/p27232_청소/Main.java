package 백준.Gold.G1.p27232_청소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.TreeMap;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static final TreeMap<Integer, Integer> positionMap = new TreeMap<>();
    private static long curDistance;

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int K = nextInt();
        int[] arr = new int[N - K];
        long minDistance = Long.MAX_VALUE;
        for (int position = 0; position < N; position++) {
            int id = nextInt();
            if (position < N - K) arr[position] = id;
            if (position >= K) remove(arr[position - K]);
            add(id, position);
            if (position >= K - 1) minDistance = Math.min(minDistance, curDistance);
        }
        System.out.println(minDistance);
    }

    private static void remove(int id) {
        int position = positionMap.get(id);
        Integer prevId = positionMap.lowerKey(id);
        Integer nextId = positionMap.higherKey(id);
        positionMap.remove(id);
        if (prevId != null) curDistance -= Math.abs(positionMap.get(prevId) - position);
        if (nextId != null) curDistance -= Math.abs(positionMap.get(nextId) - position);
        if (prevId != null && nextId != null)
            curDistance += Math.abs(positionMap.get(prevId) - positionMap.get(nextId));
    }

    private static void add(int id, int position) {
        positionMap.put(id, position);
        Integer prevId = positionMap.lowerKey(id);
        Integer nextId = positionMap.higherKey(id);
        if (prevId != null) curDistance += Math.abs(positionMap.get(prevId) - position);
        if (nextId != null) curDistance += Math.abs(positionMap.get(nextId) - position);
        if (prevId != null && nextId != null)
            curDistance -= Math.abs(positionMap.get(prevId) - positionMap.get(nextId));
    }
}
