package 백준.Silver.S4.p10828_스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Stack<T> {
    ArrayDeque<T> stack = new ArrayDeque<>();

    void push(T value) {
        stack.push(value);
    }

    T pop() {
        return stack.pop();
    }

    int size() {
        return stack.size();
    }

    int empty() {
        return stack.isEmpty() ? 1 : 0;
    }

    T top() {
        return stack.peek();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push":
                    stack.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(stack.empty() == 1 ? -1 : stack.pop()).append("\n");
                    break;
                case "size":
                    sb.append(stack.size()).append("\n");
                    break;
                case "empty":
                    sb.append(stack.empty()).append("\n");
                    break;
                case "top":
                    sb.append(stack.empty() == 1 ? -1 : stack.top()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
