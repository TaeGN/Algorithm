package programmers.lv2.no.유사_칸토어_비트열;

public class Solution1 {
	public static void main(String[] args) {
		int n = 2;
		long l = 4;
		long r = 17;
		System.out.println(Solution(n, l, r));
	}
	
    public static int Solution(int n, long l, long r) {
    	result = r - l + 1;
        long num = (long) Math.pow(5, n - 1);
        dfs(num, l - 1, r, 1);
        return (int)result;
    }
    
    static long result, L, R;
	private static void dfs(long num, long l, long r, int a) {
		if(num == 1) return;
		int i = (int)(l / num);
		int j = (int)(r / num);
		switch(i) {
		case 0:
			switch(j) {
			case 0:
				dfs(num / 5, l % num, r % num, a);
				break;
			case 1:
				dfs(num / 5, l % num, num / 5, a);
				dfs(num / 5, 0, r % num, a);
				break;
			case 2:
				dfs(num / 5, l % num, num / 5, a);
				dfs(num / 5, 0, num / 5, a);
				result -= (r % 5) * (num / 5) * a;
				break;
			case 3:
				dfs(num / 5, l % num, num / 5, a);
				dfs(num / 5, 0, num / 5, a);
				result -= num * a;
				dfs(num / 5, 0, r % num, a);
				break;
			case 4:
				dfs(num / 5, l % num, num / 5, a);
				dfs(num / 5, 0, num / 5, a * 2);
				result -= num * a;
				dfs(num / 5, 0, r % num, a);
				break;
			}
		case 1:
			switch(j) {
			case 1:
				dfs(num / 5, l % num, r % num, a);
				break;
			case 2:
				dfs(num / 5, l % num, num / 5, a);
				result -= (r % 5) * (num / 5) * a;
				break;
			case 3:
				dfs(num / 5, l % num, num / 5, a);
				dfs(num / 5, 0, r % num, a);
				result -= num * a;
				break;
			case 4:
				dfs(num / 5, l % num, num / 5, a);
				dfs(num / 5, 0, num / 5, a);
				result -= num * a;
				dfs(num / 5, 0, r % num, a);
				break;
			}
		case 2:
			switch(j) {
			case 2:
				result -= (r % 5 - l % 5) * (num / 5) * a;
				break;
			case 3:
				dfs(num / 5, 0, r % num, a);
				result -= (5 - l % 5) * (num / 5) * a;
				break;
			case 4:
				dfs(num / 5, 0, num / 5, a);
				dfs(num / 5, 0, r % num, a);
				result -= (5 - l % 5) * (num / 5) * a;
				break;
			}
		case 3:
			switch(j) {
			case 3:
				dfs(num / 5, l % num, r % num, a);
				break;
			case 4:
				dfs(num / 5, l % num, num / 5, a);
				dfs(num / 5, 0, r % num, a);
				break;
			}
		case 4:
			switch(j) {
			case 4:
				dfs(num / 5, l % num, r % num, a);
				break;
			}
		}
	}
}  
    
