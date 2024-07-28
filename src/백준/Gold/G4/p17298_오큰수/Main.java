package 백준.Gold.G4.p17298_오큰수;

import java.io.*;
import java.util.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int[] numArr = new int[N];
        for (int i = 0; i < N; i++) {
            numArr[i] = nextInt();
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= numArr[i]) stack.pop();
            int value = stack.isEmpty() ? -1 : stack.peek();
            stack.push(numArr[i]);
            numArr[i] = value;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : numArr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
