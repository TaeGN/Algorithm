package 엘리스코드챌린지.떠먹여주는알고리즘.탐색알고리즘과DP.배열의합1;

import java.io.*;

class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }


    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int M = nextInt();
        int[] sumArr = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            sumArr[i] = sumArr[i - 1] + nextInt();
        }

        int count = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = i; j <= N; j++) {
                if(sumArr[j] - sumArr[i - 1] == M) count++;
            }
        }

        System.out.println(count);
    }
}
