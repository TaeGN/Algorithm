package programmers.lv3.no.보석_쇼핑;

public class Solution1 {
    public int[] solution(String[] gems) {
    	int len = gems.length;
    	Jewel jewel = new Jewel();
    	int[] result = new int[2];
    	int start = 0, idx = -1, min = Integer.MAX_VALUE;
    	while(String gem : gems) {
    		if(idx - start + 1 > min) jewel.delete(gems[start--]);
    		if(!jewel.isOk() && ++idx < len) jewel.add(gems[idx]);
    		else if(min > idx - start + 1) {
    			result[0] = start;
    			result[1] = idx;
    		}
    		
    		idx++;
    	}
        return result;
    }
    
    class Jewel {
    	int d, r, e, s;
    	
    	void add(String gem) {
    		switch(gem.charAt(0)) {
    		case 'D':
    			d++;
    			break;
    		case 'R':
    			r++;
    			break;
    		case 'E':
    			e++;
    			break;
    		case 'S':
    			s++;
    			break;
    		}
    	}
    	
    	void delete(String gem) {
    		switch(gem.charAt(0)) {
    		case 'D':
    			d--;
    			break;
    		case 'R':
    			r--;
    			break;
    		case 'E':
    			e--;
    			break;
    		case 'S':
    			s--;
    			break;
    		}
    	}
    	
    	boolean isOk() {
    		return d > 0 && r > 0 && e > 0 && s > 0;
    	}
    }
}
