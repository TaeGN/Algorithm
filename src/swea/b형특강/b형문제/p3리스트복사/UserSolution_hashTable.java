package swea.b형특강.b형문제.p3리스트복사;

import java.util.HashMap;
import java.util.Map;

public class UserSolution_hashTable
{
	private final int MOD;
	private final int POWER;
	private final int MAX_M_LENGTH;
	private final int MAX_M_NAME_LEN;
	private final int MAX_MAKE_LIST_CALL_CNT;
	private final int MAX_COPY_LIST_CALL_CNT;
	private final int MAX_UPDATE_ELEMENT_CALL_CNT;
	private final Map<String, Integer> shallowCopyMap;
	private final HashTable copyMap;
	private final ListPool listPool;
	private final NodePool nodePool;
	private final CopyListPool copyListPool;
	
	public UserSolution_hashTable() {
		this.MAX_M_LENGTH = 200_000;
		this.MAX_M_NAME_LEN = 20;
		this.MAX_MAKE_LIST_CALL_CNT = 10;
		this.MAX_COPY_LIST_CALL_CNT = 5000;
		this.MAX_UPDATE_ELEMENT_CALL_CNT = 100_000;
		this.POWER = 26;
		this.MOD = this.MAX_MAKE_LIST_CALL_CNT + this.MAX_COPY_LIST_CALL_CNT;
		this.shallowCopyMap = new HashMap<>(this.MAX_MAKE_LIST_CALL_CNT + this.MAX_COPY_LIST_CALL_CNT);
		this.copyMap = new HashTable(this.MAX_MAKE_LIST_CALL_CNT + this.MAX_COPY_LIST_CALL_CNT);
		this.listPool = new ListPool(this.MAX_MAKE_LIST_CALL_CNT);
		this.nodePool = new NodePool(this.MAX_COPY_LIST_CALL_CNT);
		this.copyListPool = new CopyListPool(this.MAX_MAKE_LIST_CALL_CNT + this.MAX_COPY_LIST_CALL_CNT);
	}
	
	class HashTable {
		Node[] hashTable;
		int size;
		public HashTable(int size) {
			this.hashTable = new Node[size];
			this.size = size;
		}
		
		public void init() {
			for (int i = 0; i < this.size; i++) {
				hashTable[i] = null;
			}
		}
		
		private int getHash(String name) {
			int hash = 0;
			for (int i = 0, len = name.length(); i < len; i++) {
				hash = ((hash * POWER) + name.charAt(i)) % MOD;
			}
			return hash;
		}
		
		private String getName(char[] mName) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < MAX_M_NAME_LEN; i++) {
				if(!Character.isAlphabetic(mName[i])) break;
				sb.append(mName[i]);
			}
			return sb.toString();
		}
		
		public int put(char[] mName, CopyList value) {
			String name = getName(mName);
			int nameHash = getHash(name);
			Node node = nodePool.get(name, value);
			while(hashTable[nameHash] != null) nameHash = ++nameHash % MOD;
			value.name = nameHash;
			hashTable[nameHash] = node;
			return nameHash;
		}
		
		public int put(char[] mName, List list) {
			String name = getName(mName);
			int nameHash = getHash(name);
			while(hashTable[nameHash] != null) nameHash = ++nameHash % MOD;
			CopyList value = copyListPool.get(list);
			value.name = nameHash;
			Node node = nodePool.get(name, value);
			hashTable[nameHash] = node;
			return nameHash;
		}
		
		public CopyList get(char[] mName) {
			return hashTable[this.getNameHash(mName)].value;
		}
		public CopyList get(int nameHash) {
			return hashTable[nameHash].value;
		}
		public int getNameHash(char[] mName) {
			String name = getName(mName);
			int nameHash = 0;
			if(shallowCopyMap.containsKey(name)) {
				nameHash = shallowCopyMap.get(name);
			} else {
				nameHash = getHash(name);
				while(!hashTable[nameHash].key.equals(name)) nameHash = ++nameHash % MOD;
			}
			return nameHash;
		}
		
	}
	
	class Node {
		private String key;
		private CopyList value;
		
		public Node() {}
		public void set(String key, CopyList value) {
			this.key = key;
			this.value = value;
		}
	}
	
	class NodePool {
		Node[] pool;
		int idx;
		public NodePool(int size) {
			this.pool = new Node[size];
			for (int i = 0; i < size; i++) {
				pool[i] = new Node();
			}
			this.init();
		}
		public void init() {
			this.idx = -1;
		}
		public Node get(String key, CopyList value) {
			Node node = this.pool[++idx];
			node.set(key, value);
			return node;
		}
	}
	
	class Update {
		int nameHash, idx, val;
		public Update() {}
		public boolean check(int nameHash, int from) {
			return this.nameHash == nameHash && this.idx == from;
		}
		public boolean check(int nameHash) {
			return this.nameHash == nameHash;
		}
		public void set(int nameHash, int idx, int val) {
			this.nameHash = nameHash;
			this.idx = idx;
			this.val = val;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Update [nameHash=").append(nameHash).append(", idx=").append(idx).append(", val=")
					.append(val).append("]");
			return builder.toString();
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
		
		public int add(int val) {
			this.values[++idx] = val;
			return idx;
		}
		
		public void addAll(int len, int[] values) {
			for (int i = 0; i < len; i++) {
				this.values[i] = values[i];
			}
			this.idx = len - 1;
		}
		
		public void update(int nameHash, int idx, int val) {
			Update update = this.updates[++this.updateIdx];
			update.set(nameHash, idx, val);
//			System.out.println(update);
		}
		
		public int get(int nameHash, int idx) {
			for (int i = updateIdx; i >= 0; i--) {
				if(updates[i].check(nameHash, idx)) {
					return updates[i].val;
				}
			}
			return this.values[idx];
		}
		
		public int get(int mIndex) {
			return this.values[mIndex];
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();

			builder.append("List : " + (idx + 1) + "\n");
			for (int i = 0; i <= idx; i++) {
				builder.append(this.values[i]).append(" ");
			}
			return builder.toString();
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
		int parentLastUpdateIdx, name;
		
		public CopyList() {}

		public void update(int mIndex, int mValue) {
			this.origin.update(this.name, mIndex, mValue);
		}

		public int get(int mIndex) {
			Update[] updates = this.origin.updates;
			for (int i = this.origin.updateIdx; i > this.parentLastUpdateIdx; i--) {
				if(updates[i].check(this.name, mIndex)) {
					return updates[i].val;
				}
			}
			
			int startIdx, lastIdx = this.parentLastUpdateIdx;
			for (CopyList copyList = this.parent; copyList != null; copyList = copyList.parent) {
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

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("name : " + name).append("\n parentLastUpdateIdx : ").append(parentLastUpdateIdx);
			return sb.toString();
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
		
		public CopyList get(CopyList parent) {
			CopyList copyList = this.pool[++idx];
			copyList.origin = parent.origin;
			copyList.parent = parent;
			copyList.parentLastUpdateIdx = parent.origin.updateIdx;
			return copyList;
		}
		
		public CopyList get(List list) {
			CopyList copyList = this.pool[++idx];
			copyList.origin = list;
			copyList.parent = null;
			copyList.parentLastUpdateIdx = -1;
			return copyList;
		}
	}
	
	
	public void init()
	{
		this.listPool.init();
		this.copyListPool.init();
		this.nodePool.init();
		this.copyMap.init();
		this.shallowCopyMap.clear();
	}

	public void makeList(char mName[], int mLength, int mListValue[])
	{
		List list = this.listPool.get();
		list.addAll(mLength, mListValue);
		this.copyMap.put(mName, list);
	}

	public void copyList(char mDest[], char mSrc[], boolean mCopy)
	{
		int nameHash = this.copyMap.getNameHash(mSrc);
		CopyList value = this.copyMap.get(nameHash);
		if(mCopy) {
			value = this.copyListPool.get(value);
			this.copyMap.put(mDest, value);
		} else {
			shallowCopy(mDest, nameHash);
		}
	}

	private void shallowCopy(char[] mName, int nameHash) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < MAX_M_NAME_LEN; i++) {
			if(!Character.isAlphabetic(mName[i])) break;
			sb.append(mName[i]);
		}
		shallowCopyMap.put(sb.toString(), nameHash);
	}

	public void updateElement(char mName[], int mIndex, int mValue)
	{
		CopyList copyList = this.copyMap.get(mName);
		copyList.update(mIndex, mValue);
	}

	public int element(char mName[], int mIndex)
	{
		CopyList copyList = this.copyMap.get(mName);
//		System.out.println(copyList);
		return copyList.get(mIndex);
	}
}