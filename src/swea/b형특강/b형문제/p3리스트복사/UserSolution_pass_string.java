package swea.b형특강.b형문제.p3리스트복사;

import java.util.HashMap;
import java.util.Map;

public class UserSolution_pass_string
{
	private final int MAX_M_LENGTH;
	private final int MAX_M_NAME_LEN;
	private final int MAX_MAKE_LIST_CALL_CNT;
	private final int MAX_COPY_LIST_CALL_CNT;
	private final int MAX_UPDATE_ELEMENT_CALL_CNT;
	private final Map<String, CopyList> copyMap;
	private final ListPool listPool;
	private final CopyListPool copyListPool;
	
	public UserSolution_pass_string() {
		this.MAX_M_LENGTH = 200_000;
		this.MAX_M_NAME_LEN = 20;
		this.MAX_MAKE_LIST_CALL_CNT = 10;
		this.MAX_COPY_LIST_CALL_CNT = 5000;
		this.MAX_UPDATE_ELEMENT_CALL_CNT = 100_000;
		this.copyMap = new HashMap<>(this.MAX_MAKE_LIST_CALL_CNT + this.MAX_COPY_LIST_CALL_CNT);
		this.listPool = new ListPool(this.MAX_MAKE_LIST_CALL_CNT);
		this.copyListPool = new CopyListPool(this.MAX_MAKE_LIST_CALL_CNT + this.MAX_COPY_LIST_CALL_CNT);
	}
	
	class Update {
		int idx, val;
		String name;
		public Update() {}
		public boolean check(String name, int from) {
			return this.name.equals(name) && this.idx == from;
		}
		public boolean check(String name) {
			return this.name.equals(name);
		}
		public void set(String name, int idx, int val) {
			this.name = name;
			this.idx = idx;
			this.val = val;
		}
	}
	
	class List {
		int[] values;
		int idx;
		Update[] updates;
		int updateIdx;
		
		public List() {
			this.values = new int[MAX_M_LENGTH];
			this.updates = new Update[MAX_UPDATE_ELEMENT_CALL_CNT];
			for (int i = 0; i < this.updates.length; i++) {
				this.updates[i] = new Update();
			}
			this.init();
		}
		
		public void init() {
			this.idx = -1;
			this.updateIdx = -1;
		}
		
		public void addAll(int len, int[] values) {
			for (int i = 0; i < len; i++) {
				this.values[i] = values[i];
			}
			this.idx = len - 1;
		}
		
		public void update(String name, int idx, int val) {
			Update update = this.updates[++this.updateIdx];
			update.set(name, idx, val);
		}
		
		public int get(int mIndex) {
			return this.values[mIndex];
		}
	}

	
	class ListPool {
		List[] listPool;
		int idx;
		
		public ListPool(int size) {
			this.listPool = new List[size];
			for (int i = 0; i < size; i++) {
				this.listPool[i] = new List();
			}
			this.init();
		}
		
		public void init() {
			this.idx = -1;
		}
		
		public List get() {
			List list = this.listPool[++idx];
			list.init();
			return list;
		}
	}
	
	class CopyList {
		List origin;
		CopyList parent;
		int parentLastUpdateIdx;
		String name;
		
		public CopyList() {}

		public void update(int mIndex, int mValue) {
			this.origin.update(this.name, mIndex, mValue);
		}
		
		public int get(int mIndex) {
			Update[] updates = this.origin.updates;
			int startIdx, lastIdx = this.origin.updateIdx;
			for (CopyList copyList = this; copyList != null; copyList = copyList.parent) {
				startIdx = copyList.parentLastUpdateIdx + 1;
				
				for (int i = lastIdx; i >= startIdx; i--) {
					if(updates[i].check(copyList.name, mIndex)) {
						return updates[i].val;
					}
				}
				
				lastIdx = startIdx - 1;
			}
			return this.origin.get(mIndex);
		}
		
		public void set(List origin, CopyList parent, int parentLastUpdateIdx, String name) {
			this.origin = origin;
			this.parent = parent;
			this.name = name;
			this.parentLastUpdateIdx = parentLastUpdateIdx;
		}
	}
	
	class CopyListPool {
		CopyList[] pool;
		int idx;
		
		public CopyListPool(int size) {
			this.pool = new CopyList[size];
			for (int i = 0; i < size; i++) {
				this.pool[i] = new CopyList();
			}
			this.init();
		}
		
		public void init() {
			this.idx = -1;
		}
		
		public CopyList get(CopyList parent, String name) {
			CopyList copyList = this.pool[++idx];
			copyList.set(parent.origin, parent, parent.origin.updateIdx, name);
			return copyList;
		}
		
		public CopyList get(List list, String name) {
			CopyList copyList = this.pool[++idx];
			copyList.set(list, null, -1, name);
			return copyList;
		}
	}
	
	
	public void init()
	{
		this.listPool.init();
		this.copyListPool.init();
		this.copyMap.clear();
	}

	public void makeList(char mName[], int mLength, int mListValue[])
	{
		List list = this.listPool.get();
		list.addAll(mLength, mListValue);
		String name = getName(mName);
		CopyList copyList = this.copyListPool.get(list, name);
		this.copyMap.put(name, copyList);
	}

	public void copyList(char mDest[], char mSrc[], boolean mCopy)
	{
		String src = getName(mSrc);
		String dest = getName(mDest);
		CopyList copyList = this.copyMap.get(src);
		if(mCopy) {
			copyList = this.copyListPool.get(copyList, dest);
		} 
		this.copyMap.put(dest, copyList);
	}

	public void updateElement(char mName[], int mIndex, int mValue)
	{
		String name = getName(mName);
		CopyList copyList = this.copyMap.get(name);
		copyList.update(mIndex, mValue);
	}

	public int element(char mName[], int mIndex)
	{
		String name = getName(mName);
		CopyList copyList = this.copyMap.get(name);
		return copyList.get(mIndex);
	}
	
	public String getName(char mName[]) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < MAX_M_NAME_LEN; i++) {
			if(!Character.isAlphabetic(mName[i])) break;
			sb.append(mName[i]);
		}
		return sb.toString();
	}
}