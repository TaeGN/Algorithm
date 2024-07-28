package 백준.Gold.G4.p13032_PS테러리스트;

import java.io.*;
import java.util.*;

class BombArray {
    Bomb[] arr;

    BombArray(int n) {
        arr = new Bomb[n + 1];
        arr[0] = new Bomb(0, 0);
    }

    void set(int idx, Bomb value) {
        arr[idx] = value;
    }

    Bomb get(int idx) {
        return arr[idx];
    }

    void sort() {
        Arrays.sort(arr);
    }

    int binarySearch(int xi) {
        return binarySearch(0, arr.length - 1, xi);
    }

    private int binarySearch(int start, int end, int target) {
        int mid = (start + end) / 2;
        if (start == mid) return get(end).xi <= target ? end : start;
        if (get(mid).xi > target) return binarySearch(start, mid - 1, target);
        return binarySearch(mid, end, target);
    }
}

class Bomb implements Comparable<Bomb> {
    int xi, pi;

    Bomb(int xi, int pi) {
        this.xi = xi;
        this.pi = pi;
    }

    public int compareTo(Bomb other) {
        return Integer.compare(xi, other.xi);
    }
}

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        BombArray bombArray = new BombArray(N);
        for (int i = 1; i <= N; i++) {
            bombArray.set(i, new Bomb(nextInt(), nextInt()));
        }
        bombArray.sort();

        int[] bombCount = new int[N + 1];
        int result = Integer.MAX_VALUE;
        for (int curIdx = 1; curIdx <= N; curIdx++) {
            bombCount[curIdx] = 1;
            Bomb bomb = bombArray.get(curIdx);
            int prevIdx = bombArray.binarySearch(bomb.xi - bomb.pi - 1);
            bombCount[curIdx] += bombCount[prevIdx];
            result = Math.min(result, N - bombCount[curIdx]);
        }
        System.out.println(result);
    }
}
