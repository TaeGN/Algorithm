package util.print;

public class SegTreePrint {
	private final int MAX_LEN;
	private final String SPACE;
	private final String MESSAGE;
	
	public SegTreePrint(String msg, int[] data) {
		this.MESSAGE = msg;
		this.MAX_LEN = getMaxWordLen(data);
		this.SPACE = getSpace(this.MAX_LEN);
	}
	public SegTreePrint(String msg, long[] data) {
		this.MESSAGE = msg;
		this.MAX_LEN = getMaxWordLen(data);
		this.SPACE = getSpace(this.MAX_LEN);
	}
	
	public void print(int[] tree, int h) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n----" + this.MESSAGE + "----\n\n");
		for (int i = 0; i <= h; i++) {
			int start = (int) Math.pow(2, i);
			int end = (int) Math.pow(2, i + 1);
			for (int j = 1; j < (int) Math.pow(2, h - i); j++) {
				sb.append(this.SPACE);
			}
			for (int j = start; j < end; j++) {
				sb.append(getWord(tree[j], this.MAX_LEN));
				for (int k = 1; k < (int) Math.pow(2, h - i + 1); k++) {
					sb.append(this.SPACE);
				}
			}
			sb.append("\n\n");
		}
		System.out.println(sb);
	}
	
	public void print(long[] tree, int h) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n----" + this.MESSAGE + "----\n\n");
		for (int i = 0; i <= h; i++) {
			int start = (int) Math.pow(2, i);
			int end = (int) Math.pow(2, i + 1);
			for (int j = 1; j < (int) Math.pow(2, h - i); j++) {
				sb.append(this.SPACE);
			}
			for (int j = start; j < end; j++) {
				sb.append(getWord(tree[j], this.MAX_LEN));
				for (int k = 1; k < (int) Math.pow(2, h - i + 1); k++) {
					sb.append(this.SPACE);
				}
			}
			sb.append("\n\n");
		}
		System.out.println(sb);
	}
	
	private int getMaxWordLen(int[] data) {
		if(data == null) return 1;
		int max = 0, res = 0;
		for (int num : data) {
			if(num < 0) {
				res++;
				num = -num;
			} else if(num == 0) num = 1;
			max = Math.max(max, num);
		}
		return res += (int)( Math.log10(max)+1 );
	}
	
	private int getMaxWordLen(long[] data) {
		if(data == null) return 1;
		long max = 0;
		int res = 0;
		for (long num : data) {
			if(num < 0) {
				res++;
				num = -num;
			} else if(num == 0) num = 1;
			max = Math.max(max, num);
		}
		return res += (int)( Math.log10(max)+1 );
	}

	private String getWord(int num, int maxWordLen) {
		return getWord((long) num, maxWordLen);
	}
	
	private String getWord(long num, int maxWordLen) {
		StringBuilder sb = new StringBuilder();
		long newNum = num;
		if(newNum < 0) newNum = -newNum;
		else if(newNum == 0) newNum = 1;
		int len = maxWordLen - (int)( Math.log10(newNum)+1 );
		for (int i = 0; i < len / 2; i++) {
			sb.append(" ");
		}
		sb.append(num);
		for (int i = 0; i < len - len / 2; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

	private String getSpace(int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}
}
