package swea.b형특강.b형문제.p5긴사다리게임;

import java.util.TreeSet;

class UserSolution_treeSet_t3715
{
	final long MOD;
	final TreeSet<Long> lineSet;
	
	public UserSolution_treeSet_t3715() {
		this.MOD = 100;
		this.lineSet = new TreeSet<>();
	}
	
	public long getLine(int mX, int mY) {
		return mY * MOD + mX;
	}
	
	public int getMX(long line) {
		return (int) (line % MOD);
	}
	
	public void init()
	{
		this.lineSet.clear();
	}

	public void add(int mX, int mY)
	{
		this.lineSet.add(getLine(mX, mY));
	}

	public void remove(int mX, int mY)
	{
		this.lineSet.remove(getLine(mX, mY));
	}

	public int numberOfCross(int mID)
	{
		int mX, cnt = 0;
		for (long line : lineSet) {
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
		int mID = mX;
		Long line = lineSet.lower(getLine((int) this.MOD, mY));
		while(line != null) {
			mX = getMX(line);
			if(mID != 1 && mX == mID - 1) mID--;
			else if(mID != 100 && mX == mID) mID++;
			line = lineSet.lower(line);
		}
		
		return mID;
	}
}