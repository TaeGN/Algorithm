package 백준.Gold.G4.p17452_Christmalowin;

import java.io.*;
import java.util.Arrays;

public class Main {
    private static final int IMPOSSIBLE = Integer.MAX_VALUE >> 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] forwardArr = new int[26];
        int[] backwardArr = new int[26];
        Arrays.fill(forwardArr, IMPOSSIBLE);
        Arrays.fill(backwardArr, IMPOSSIBLE);
        int result = IMPOSSIBLE;
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < 26; j++) {
                int forward = word.indexOf('a' + j);
                if (forward == -1) continue;
                int backward = word.length() - 1 - word.lastIndexOf('a' + j);
                result = Math.min(result, Math.min(forward + backwardArr[j], backward + forwardArr[j]));
                forwardArr[j] = Math.min(forwardArr[j], forward);
                backwardArr[j] = Math.min(backwardArr[j], backward);
            }
        }

        System.out.println(result == IMPOSSIBLE ? -1 : result);
    }
}
