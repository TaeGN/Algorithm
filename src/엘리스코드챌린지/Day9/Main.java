package 엘리스코드챌린지.Day9;

import java.io.*;
import java.util.*;

class Pair<T, K extends Comparable<K>> implements Comparable<Pair<T, K>> {
    T first;
    K second;

    Pair(T first, K second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Pair<T, K> o) {
        return second.compareTo(o.second);
    }
}

class Point {
    int r, c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public boolean equals(Object obj) {
        Point other = (Point) obj;
        if (r == other.r && c == other.c) return true;
        return super.equals(obj);
    }
}

class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static final Long IMPOSSIBLE = Long.MAX_VALUE;
    private static final PriorityQueue<Pair<Point, Long>> pq = new PriorityQueue<>();
    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, -1, 0, 1};
    private static int N;
    private static int[][] weightMatrix;
    private static long[][] minTimeMatrix;


    public static void main(String[] args) throws IOException {
        N = nextInt();
        weightMatrix = new int[N][N];
        minTimeMatrix = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                weightMatrix[i][j] = nextInt();
            }
        }

        Point[] points = new Point[7];
        points[0] = new Point(0, 0);
        for (int i = 1; i <= 5; i++) {
            points[i] = new Point(nextInt() - 1, nextInt() - 1);
        }

        long case1 = minTime(points[0], points[1]) + minTime(points[1], points[2]) + minTime(points[4], points[5]);
        long case2 = minTime(points[0], points[5]) + minTime(points[5], points[2]) + minTime(points[4], points[1]);
        long result = Math.min(case1, case2) + minTime(points[2], points[3]) + minTime(points[3], points[4]);
        System.out.println(result);
    }

    private static long minTime(Point startPoint, Point endPoint) {
        initMinTimeMatrix();
        pq.clear();
        setMinTime(startPoint, getWeight(startPoint));
        pq.add(new Pair<Point, Long>(startPoint, (long) getWeight(startPoint)));

        while (!pq.isEmpty()) {
            Pair<Point, Long> pair = pq.poll();
            Point curPoint = pair.first;
            long curMinTime = pair.second;
            if (curMinTime > getMinTime(curPoint)) continue;
            if (curPoint.equals(endPoint)) return getMinTime(curPoint) - getWeight(curPoint);
            for (int d = 0; d < dr.length; d++) {
                int r = curPoint.r + dr[d];
                int c = curPoint.c + dc[d];
                if (isNotValid(r, c)) continue;
                Point nextPoint = new Point(r, c);
                long nextMinTime = curMinTime + (long) getWeight(nextPoint) * 2;
                if (getMinTime(nextPoint) > nextMinTime) {
                    setMinTime(nextPoint, nextMinTime);
                    pq.add(new Pair<Point, Long>(nextPoint, nextMinTime));
                }
            }
        }

        return IMPOSSIBLE;
    }

    private static void initMinTimeMatrix() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(minTimeMatrix[i], IMPOSSIBLE);
        }
    }

    private static int getWeight(Point point) {
        return weightMatrix[point.r][point.c];
    }

    private static long getMinTime(Point point) {
        return minTimeMatrix[point.r][point.c];
    }

    private static void setMinTime(Point point, long value) {
        minTimeMatrix[point.r][point.c] = value;
    }

    private static boolean isNotValid(int r, int c) {
        return !isValid(r, c);
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}