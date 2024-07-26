package 백준.Gold.G2.p15804_저거못타면지각이야;

import java.io.*;
import java.util.ArrayDeque;

class Station {
    int stationId, time;

    Station(int stationId, int time) {
        this.stationId = stationId;
        this.time = time;
    }
}

class Bus {
    int arrivalTime, stopTime;

    Bus(int arrivalTime, int stopTime) {
        this.arrivalTime = arrivalTime;
        this.stopTime = stopTime;
    }
}

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();
        ArrayDeque<Station> stationQueue = new ArrayDeque<>(n);
        ArrayDeque<Bus> queue = new ArrayDeque<>(m);
        for (int i = 0; i < m; i++) {
            queue.add(new Bus(nextInt(), nextInt()));
        }
        int curTime = 0;
        while (!queue.isEmpty()) {
            Station lastStation = stationQueue.peekLast();
            if (lastStation != null && lastStation.stationId == n) {
                while (!stationQueue.isEmpty()) curTime = Math.max(curTime, stationQueue.poll().time);
            }
            Bus bus = queue.removeFirst();
            curTime = Math.max(curTime, bus.arrivalTime);
            while (!stationQueue.isEmpty() && stationQueue.peek().time <= curTime) stationQueue.poll();
            lastStation = stationQueue.peekLast();
            stationQueue.add(new Station(lastStation == null ? 1 : lastStation.stationId + 1, curTime + bus.stopTime));
        }

        System.out.println(stationQueue.getLast().stationId);
    }
}
