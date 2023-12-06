package programmers.lv3.ok.디스크_컨트롤러;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution1 {
	public static void main(String[] args) {
		int[][] jobs = {{0,3},{1,9},{2,6}};
		System.out.println(Solution1(jobs));
	}
    public static int Solution1(int[][] jobs) {
    	// 작업 수행시간 오름차순 정렬
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
       
        // jobs = 작업 요청되는 시점 오름차순 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        // allTime = 전체 대기 시간, curTime = 현재 시간, idx = jobs의 인덱스
        int allTime = 0, curTime = 0, idx = 0, len = jobs.length, size;	
        int[] cur;
        while(idx < len || !queue.isEmpty()) {				// jobs or queue에 작업이 남아있으면 계속 실행
        	while(idx < len && jobs[idx][0] <= curTime) {	// 현재 시간보다 작업 요청 시점이 낮은 것을 queue에 offer
        		allTime += curTime - jobs[idx][0];			// queue에 들어가기 전 까지 대기한 시간을 allTime에 더해준다.
        		queue.offer(jobs[idx++]);
        	}
        	
        	if(queue.isEmpty()) curTime++;	// queue에 대기하는 작업이 없으면 time + 1
        	else {
        		size = queue.size();
        		cur = queue.poll();
        		curTime += cur[1];			// queue에 대기하는 작업이 있으면 time + 작업 시간
        		allTime += size * cur[1];	// 지나간 시간 * queue에 있던 작업 수 => 대기 시간
        	}
        }
        
        return allTime / len;
    }
}
