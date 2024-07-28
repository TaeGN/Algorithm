package 엘리스코드챌린지.Day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] numArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int fromIndex = Integer.parseInt(st.nextToken()) - 1;
            int toIndex = Integer.parseInt(st.nextToken());
            int targetIndex = Integer.parseInt(st.nextToken()) - 1;
            int targetNum = Arrays.stream(numArr, fromIndex, toIndex).sorted().skip(targetIndex).findFirst().orElseThrow();
            sb.append(targetNum).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
