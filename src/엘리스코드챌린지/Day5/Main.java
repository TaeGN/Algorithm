package 엘리스코드챌린지.Day5;

import java.io.*;
import java.util.*;

class Main {
    private static final int MAX_AI = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] countArr = new int[MAX_AI + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = 1 << n;
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num <= MAX_AI) countArr[num]++;
        }

        Map<Integer, Integer> selectedMap = new HashMap<>();
        selectedMap.put(0, 1);
        Map<Integer, Integer> addedMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        a: for (int num = 1; num <= MAX_AI; num++) {
            while (countArr[num] > 0) {
                sb.append(num).append(" ");
                for (Map.Entry<Integer, Integer> entry : selectedMap.entrySet()) {
                    int sum = num + entry.getKey();
                    if (sum <= MAX_AI) {
                        countArr[sum] -= entry.getValue();
                        addedMap.put(sum, entry.getValue());
                    }
                }
                addedMap.forEach((k1, v1) -> selectedMap.compute(k1, (k2, v2) -> v2 == null ? v1 : v1 + v2));
                addedMap.clear();
                if (++count == n) break a;
            }
        }

        System.out.println(sb);
        br.close();
    }
}