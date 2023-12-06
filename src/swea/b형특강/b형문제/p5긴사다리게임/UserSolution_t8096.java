package swea.b형특강.b형문제.p5긴사다리게임;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class UserSolution_t8096
{
	final long MOD;
	final int MAX_ADD_CALL;
	final int MAX_REMOVE_CALL;
	final long[] lineArr;
	int lineArrIdx;
	final PriorityQueue<Long> pq;
	final Set<Long> removeSet;
	boolean lineArrIsValid;
	
	public UserSolution_t8096() {
		this.MOD = 100;
		this.MAX_ADD_CALL = 200000;
		this.MAX_REMOVE_CALL = 5000;
		this.lineArr = new long[MAX_ADD_CALL];
		this.pq = new PriorityQueue<>(MAX_ADD_CALL);
		this.removeSet = new HashSet<>(MAX_REMOVE_CALL);
	}
	
	public void setLineArr() {
		this.lineArrIdx = -1;
		while(!pq.isEmpty()) {
			long line = pq.poll();
			if(removeSet.contains(line)) continue;
			this.lineArr[++this.lineArrIdx] = line;
		}
		this.lineArrIsValid = true;
//		System.out.println(" -- setLineArr -- ");
//		print();
	}
	
	public void setPq() {
		for (int i = 0; i <= lineArrIdx; i++) {
			pq.offer(this.lineArr[i]);
		}
		this.lineArrIsValid = false;
	}
	
	public long getLine(int mX, int mY) {
		return mY * MOD + mX;
	}
	
	public int getMX(long line) {
		return (int) (line % MOD);
	}
	
	public int getMid(int start, int end) {
		return (start + end) / 2;
	}
	
	public int binarySearch(long line) {
		int start = 0, end = this.lineArrIdx, mid;
		
		while(start <= end) {
			mid = getMid(start, end);
			if(this.lineArr[mid] > line) end = mid - 1;
			else start = mid + 1;
		}
		
		return end;
	}
	
	public void init()
	{
		this.pq.clear();
		this.removeSet.clear();
		this.lineArrIsValid = false;
		this.lineArrIdx = -1;
	}

	public void add(int mX, int mY)
	{
		if(this.lineArrIsValid) setPq();
		this.pq.add(getLine(mX, mY));
	}

	public void remove(int mX, int mY)
	{
		this.removeSet.add(getLine(mX, mY));
	}

	public int numberOfCross(int mID)
	{
		if(!this.lineArrIsValid) setLineArr();
		
		int mX, cnt = 0;
		long line;
		for (int i = 0; i <= lineArrIdx; i++) {
			line = this.lineArr[i];
			if(this.removeSet.contains(line)) continue;
			mX = getMX(line);
			if(mID != 1 && mX == mID - 1) mID--;
			else if(mID != 100 && mX == mID) mID++;
			else continue;
			cnt++;
		}
		return cnt;
	}

	public int participant(int mX, int mY)
	{
		if(!this.lineArrIsValid) setLineArr();
		
		int idx = binarySearch(getLine((int) this.MOD, mY));
//		System.out.print("idx : " + idx + " - ");
//		print();
		int mID = mX;
		long line;
		for (int i = idx; i >= 0; i--) {
			line = this.lineArr[i];
			if(this.removeSet.contains(line)) continue;
			mX = getMX(line);
			if(mID != 1 && mX == mID - 1) mID--;
			else if(mID != 100 && mX == mID) mID++;
		}
		
		return mID;
	}
	
	public void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= lineArrIdx; i++) {
			sb.append(this.lineArr[i]).append(" ");
		}
		System.out.println(sb);
	}
}