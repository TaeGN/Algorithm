package 엘리스코드챌린지.Day8;

import java.io.*;
import java.util.*;

class Main {
    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int M = nextInt();
        int K = nextInt();
        int T = nextInt();
        int[] sumCountArr = new int[N + 2];
        for(int i = 0; i < M; i++) {
            int fromIndex = nextInt();
            int toIndex = nextInt();
            sumCountArr[fromIndex]++;
            sumCountArr[toIndex]--;
        }

        List<TreeMap<Integer, Integer>> mapList = new ArrayList<>();
        int groupId = 0;
        int maxTime = 0;
        int curCount = 0;
        for (int i = 1; i <= N; i++) {
            curCount += sumCountArr[i];
            if (curCount < T) {
                if (mapList.size() == groupId) mapList.add(new TreeMap<>());
                mapList.get(groupId).compute(T - curCount, (k, v) -> v == null ? 1 : v + 1);
            } else {
                if (groupId < mapList.size()) groupId++;
                maxTime++;
            }
        }
        System.out.println(maxTime + maxTime(0, K, mapList));
    }

    private static int maxTime(int idx, int K, List<TreeMap<Integer, Integer>> mapList) {
        if (idx == mapList.size()) return 0;
        int maxTime = maxTime(idx + 1, K, mapList);
        int plueTimeByFriends = 0;
        Map<Integer, Integer> curMap = mapList.get(idx);
        for (int curCount : curMap.keySet()) {
            if (curCount > K) break;
            plueTimeByFriends += curMap.get(curCount);
            maxTime = Math.max(maxTime, plueTimeByFriends + maxTime(idx + 1, K - curCount, mapList));
        }
        return maxTime;
    }
}
