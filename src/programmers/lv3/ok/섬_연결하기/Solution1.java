package programmers.lv3.ok.섬_연결하기;

import java.util.Arrays;

public class Solution1 {
	public static void main(String[] args) {
		int n = 4;
		int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		System.out.println(solution(n, costs));
	}
	
    static int solution(int n, int[][] costs) {
    	Arrays.sort(costs, (o1, o2) -> Integer.compare(o1[2], o2[2]));
    	makeSet(n);
    	int cnt = 0;
    	int result = 0;
    	for(int[] cost : costs) {
    		if(union(cost[0], cost[1])) {
    			result += cost[2];
    			if(++cnt == n - 1) break;
    		}
    	}
        return result;
    }
    static int[] islandSet;
    static void makeSet(int n) {
    	islandSet = new int[n];
    	for(int i = 0; i < n; i++) {
    		islandSet[i] = i;
    	}
    }
    
    static int findSet(int a) {
    	if(a == islandSet[a]) return a;
    	return islandSet[a] = findSet(islandSet[a]);
    }
    
    static boolean union(int a, int b) {
    	a = findSet(a);
    	b = findSet(b);
    	if(a == b) return false;
    	islandSet[a] = b;
    	return true;
    }
}
