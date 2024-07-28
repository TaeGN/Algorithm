package 엘리스코드챌린지.Day6;

import java.io.*;
import java.util.*;

class Pair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Pair<K, V>> {
    K first;
    V second;

    Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        Pair<K, V> other = (Pair<K, V>) obj;
        if (first.equals(other.first) && second.equals(other.second)) return true;
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(Pair<K, V> o) {
        if (second.equals(o.second)) return o.first.compareTo(first);
        return second.compareTo(o.second);
    }
}

class Main {
    private static final String RED = "0";
    private static int[] groupSizeArr;
    private static int[] hashPowerArr;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] typeArr = br.readLine().split(" ");
        groupSizeArr = new int[N + 1];
        hashPowerArr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            hashPowerArr[i] = (N / i) + 1;
        }

        groupSizeArr[N] = 1;
        long goalHash = groupSizeArrToHash(groupSizeArr);
        groupSizeArr[N] = 0;
        groupSizeArr[1] = N;
        long startHash = groupSizeArrToHash(groupSizeArr);

        List<Integer> minCountList = new ArrayList<>();
        Map<Long, Integer> hashToMinCountListIdxMap = new HashMap<>();
        PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<>();
        minCountList.add(0);
        hashToMinCountListIdxMap.put(startHash, 0);
        pq.offer(new Pair<Long, Integer>(startHash, 0));

        while (!pq.isEmpty()) {
            Pair<Long, Integer> pair = pq.poll();
            if (pair.second > minCountList.get(hashToMinCountListIdxMap.get(pair.first))) continue;
            if (pair.first == goalHash) break;
            int[] groupSizeArr = hashToGroupSizeArr(pair.first);
            for (int i = 1; i <= N; i++) {
                if (groupSizeArr[i] == 0) continue;
                for (int j = i; j <= N - i; j++) {
                    if (groupSizeArr[j] == 0) continue;
                    if (i == j && groupSizeArr[i] < 2) continue;
                    union(i, j);
                    long newHash = groupSizeArrToHash(groupSizeArr);
                    int newMinCount = pair.second + (typeArr[curSequenceIdx(groupSizeArr)].equals(RED) ? i * j : 0);
                    if (!hashToMinCountListIdxMap.containsKey(newHash)) {
                        hashToMinCountListIdxMap.put(newHash, minCountList.size());
                        minCountList.add(newMinCount);
                        pq.offer(new Pair<Long, Integer>(newHash, newMinCount));
                    } else {
                        int idx = hashToMinCountListIdxMap.get(newHash);
                        if (newMinCount < minCountList.get(idx)) {
                            minCountList.set(idx, newMinCount);
                            pq.offer(new Pair<Long, Integer>(newHash, newMinCount));
                        }
                    }
                    rollbackUnion(i, j);
                }
            }
        }

        System.out.println(minCountList.get(hashToMinCountListIdxMap.get(goalHash)));
    }

    private static int[] hashToGroupSizeArr(long hash) {
        long curHash = hash;
        for (int i = 1; i <= N; i++) {
            groupSizeArr[i] = (int) (curHash % hashPowerArr[i]);
            curHash /= hashPowerArr[i];
        }
        return groupSizeArr;
    }

    private static long groupSizeArrToHash(int[] groupSizeArr) {
        long curHash = groupSizeArr[N];
        for (int i = N - 1; i >= 1; i--) {
            curHash *= hashPowerArr[i];
            curHash += groupSizeArr[i];
        }
        return curHash;
    }

    private static int curSequenceIdx(int[] groupSizeArr) {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += groupSizeArr[i];
        }
        return N - sum - 1;
    }

    private static void union(int i, int j) {
        groupSizeArr[i]--;
        groupSizeArr[j]--;
        groupSizeArr[i + j]++;
    }

    private static void rollbackUnion(int i, int j) {
        groupSizeArr[i]++;
        groupSizeArr[j]++;
        groupSizeArr[i + j]--;
    }
}