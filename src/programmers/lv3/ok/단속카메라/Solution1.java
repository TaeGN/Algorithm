package programmers.lv3.ok.단속카메라;

import java.util.Arrays;

public class Solution1 {
	public static void main(String[] args) {
		int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
		System.out.println(solution(routes));
	}
	
    public static int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> {
        	if(o1[1] != o2[1]) return o1[1] - o2[1];
        	return o1[0] - o2[0];
        });
        
        int time, cnt = 0;
        for(int i = 0, len = routes.length; i < len;) {
        	cnt++;
        	time = routes[i][1];
        	while(++i < len && routes[i][0] <= time);
        }
    	
        return cnt;
    }
}
