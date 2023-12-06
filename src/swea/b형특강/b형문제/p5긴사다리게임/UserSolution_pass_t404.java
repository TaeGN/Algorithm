package swea.b형특강.b형문제.p5긴사다리게임;

import java.util.TreeSet;

/**
 * 
 * @author TaeG
 *JAVA
108,912kb
메모리
712ms
시간
1,710B
코드길이
Pass
결과
 */
		
class UserSolution_pass_t404
{
	final long INF;
	final int MAX_MX;
	final TreeSet<Long>[] lineSet;
	
	public UserSolution_pass_t404() {
		this.MAX_MX = 99;
		this.INF = Long.MAX_VALUE;
		this.lineSet = new TreeSet[this.MAX_MX + 1];
		for (int i = 1; i <= this.MAX_MX; i++) {
			this.lineSet[i] = new TreeSet();
		}
	}
	
	public long getLine(int mX, int mY) {
		return (long) mY * MAX_MX + mX;
	}
	
	public void init()
	{
		for (int i = 1; i <= this.MAX_MX; i++) {
			this.lineSet[i].clear();
		}
	}

	public void add(int mX, int mY)
	{
		this.lineSet[mX].add(getLine(mX, mY));
		
	}

	public void remove(int mX, int mY)
	{
		this.lineSet[mX].remove(getLine(mX, mY));
	}

	public int numberOfCross(int mID)
	{
		int cnt = -1;
		Long l = null;
		long line = -1, line1, line2;
		while(line != INF) {
			line1 = line2 = INF;
			cnt++;
			if(mID != 1) {
				l = lineSet[mID - 1].higher(line);
				if(l != null) line1 = l;
			}
			if(mID != 100) {
				l = lineSet[mID].higher(line);
				if(l != null) line2 = l;
			}
			if(line1 < line2) {
				mID--;
				line = line1;
			} else {
				mID++;
				line = line2;
			}
		}
		
		return cnt;
	}

	public int participant(int mX, int mY)
	{
		int mID = mX;
		Long l = null;
		long line = getLine(mX + 1, mY), line1, line2;
		while(line != -1) {
			line1 = line2 = -1;
			if(mID != 1) {
				l = lineSet[mID - 1].lower(line);
				if(l != null) line1 = l;
			}
			if(mID != 100) {
				l = lineSet[mID].lower(line);
				if(l != null) line2 = l;
			}
			if(line1 > line2) {
				line = line1;
				if(line != -1) mID--;
			} else {
				line = line2;
				if(line != -1) mID++;
			}
		}
		
		return mID;
	}
}