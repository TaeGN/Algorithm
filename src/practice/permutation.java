package practice;

public class permutation {
	static int n;
	static int r;
	public static void main(String[] args) {
		n = 4;
		r = 3;
		int a = permutation(r);
		System.out.println(a);
	}
	private static void permutation(int cnt) {
		if(cnt == 0) return
	}
//	private static int permutation() {
//		if(r-- == 1) return n;
//		return n-- * permutation();
//	}
}
