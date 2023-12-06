package swea.b형특강.b형문제.p3리스트복사;

import java.util.HashMap;
import java.util.Map;

class UserSolution
{
	final int MAX_MAKE_LIST_CALL = 10;
	final int MAX_COPY = 5010;
	final int MAX_UPDATE = 105100;
	final int MAX_M_LENGTH = 200000;
	final int MAX_M_NAME = 20;
	final Map<String, Integer> listMap;
	
	public UserSolution() {
		this.listMap = new HashMap<>(MAX_COPY);
	}
	
	class Update {
		int idx, val;

		public void set(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}
	
	class Array {
		int[] arr;
		int arrIdx;
		public Array(int size) {
			this.arr = new int[size];
			this.init();
		}
		public void init() {
			this.arrIdx = -1;
		}
		public void add(int val) {
			this.arr[++this.arrIdx] = val;
		}
	}
	
	class CopyList {
		final Map<String, Integer> listMap;
		int listIdx;
		Array[] originArr;	// 원본 배열 리스트
		int originIdx;
		Array[] updateLog;		// 업데이트 로그
		
		public CopyList() {
			this.listMap = new HashMap<>(MAX_COPY);
			this.originArr = new Array[MAX_MAKE_LIST_CALL];
			for (int i = 0; i < MAX_MAKE_LIST_CALL; i++) {
				this.originArr[i] = new Array(MAX_M_LENGTH);
			}
			this.updateLog = new Array[2];
			for (int i = 0; i < 2; i++) {
				this.updateLog[i] = new Array(MAX_UPDATE);
			}
			this.init();
		}
		public void init() {
			for (int i = 0; i < MAX_MAKE_LIST_CALL; i++) {
				this.originArr[i].init();
			}
			for (int i = 0; i < 2; i++) {
				this.updateLog[i].init();
			}
			this.listIdx = -1;
			this.originIdx = -1;
		}
		
		public void makeList(char mName[], int mLength, int mListValue[]) {
			String name = this.getName(mName);
			this.listMap.put(name, ++this.listIdx);
			this.updateLog[0].add(-1);
			this.updateLog[1].add(-1);
			this.originArr[++originIdx]
		}
		
		private String getName(char[] mName) {
			int i = 0;
			for (; i < MAX_M_NAME; i++) {
				if(!Character.isAlphabetic(mName[i])) break;
			}
			return String.valueOf(mName, 0, i);
		}
	}
	
	public void init()
	{
		this.listMap.clear();
	}

	public void makeList(char mName[], int mLength, int mListValue[])
	{
	}

	public void copyList(char mDest[], char mSrc[], boolean mCopy)
	{
	}

	public void updateElement(char mName[], int mIndex, int mValue)
	{
	}

	public int element(char mName[], int mIndex)
	{
		return 0;
	}
}