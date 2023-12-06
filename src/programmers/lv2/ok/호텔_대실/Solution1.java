package programmers.lv2.ok.호텔_대실;

import java.util.Arrays;

public class Solution1 {
    public int solution(String[][] book_time) {
    	int len = book_time.length;
    	Time[] timeArr = new Time[len];
    	boolean[] isSelected = new boolean[len];
    	
    	int i = 0;
    	for(String[] sArr : book_time) {
    		timeArr[i++] = new Time(sArr);
    	}
    	Arrays.sort(timeArr);
    	
    	int cnt = 0, minCnt = 0, curTime;
    	while(true) {
    		curTime = 0;
    		minCnt++;
    		for(i = 0; i < len; i++) {
    			if(isSelected[i] || timeArr[i].start < curTime) continue;
    			isSelected[i] = true;
    			curTime = timeArr[i].end + 10;
    			if(++cnt == len) return minCnt;
    		}
    	}
    }
    
    class Time implements Comparable<Time> {
    	int start, end;

		public Time(String[] sArr) {
			super();
			String[] input = sArr[0].split(":");
			this.start = Integer.parseInt(input[0]) * 60 + Integer.parseInt(input[1]);
			input = sArr[1].split(":");
			this.end = Integer.parseInt(input[0]) * 60 + Integer.parseInt(input[1]);
		}

		@Override
		public int compareTo(Time o) {
			if(this.start != o.start) return Integer.compare(this.start, o.start);
			return Integer.compare(this.end, o.end);
		}
    }
}
