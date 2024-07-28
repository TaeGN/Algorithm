package 엘리스코드챌린지.Day7;

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        System.out.println(minDistance(N, K));
        br.close();
    }

    private static int minDistance(int N, int K) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        List<Integer> numList = new ArrayList<>(K);
        StringBuilder sb = new StringBuilder();
        String numStr = String.valueOf(N + 1);
        if(numStr.length() < K) {
            sb.append(10);
            for(int i = 2; i < K; i++) {
                sb.append(i);
            }
            return Integer.parseInt(sb.toString());
        }

        for(int i = 0; i < numStr.length(); i++) {
            numCountMap.compute(numStr.charAt(i) - '0', (k, v) -> v == null ? 1 : v + 1);
        }
        for(int i = numStr.length() - 1; i >= 0; i--) {
            numList.add(numStr.charAt(i) - '0');
        }


        while(numCountMap.size() != K) {
            for(int i = 0; i < numList.size(); i++) {
                int curNum = numList.get(i);
                int nextNum = (curNum + 1) % 10;
                numList.set(i, nextNum);
                if(numCountMap.get(curNum) == 1) numCountMap.remove(curNum);
                else numCountMap.put(curNum, numCountMap.get(curNum) - 1);
                numCountMap.compute(nextNum, (k, v) -> v == null ? 1 : v + 1);
                if(curNum < 9) break;
                if(i == numList.size() - 1) {
                    numList.add(0);
                    numCountMap.compute(0, (k, v) -> v == null ? 1 : v + 1);
                }
            }
        }

        for(int num: numList) {
            sb.append(num);
        }

        return Integer.parseInt(sb.reverse().toString());
    }
}
