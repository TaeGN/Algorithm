package swea.b형특강.b형문제.병사관리;

public class UserSolution {
	
	final int MAX_M_ID = 100000;
	final int MAX_M_TEAM = 5;
	final int MAX_M_SCORE = 5;
	Soldier[] soldierArr;
	LinkedList[][] teamList;
	
	public UserSolution() {
		// soldierArr 초기화
		this.soldierArr = new Soldier[MAX_M_ID + 1];
		for (int i = 1; i <= MAX_M_ID; i++) {
			soldierArr[i] = new Soldier(i);
		}
		
		// teamList 초기화
		this.teamList = new LinkedList[MAX_M_TEAM + 1][MAX_M_SCORE + 1];
		for (int i = 1; i <= MAX_M_TEAM; i++) {
			for (int j = 1; j <= MAX_M_SCORE; j++) {
				teamList[i][j] = new LinkedList();
			}
		}
		
	}
	
	class LinkedList {
		Soldier head;
		Soldier tail;
		
		public LinkedList() {
			this.head = new Soldier(Integer.MIN_VALUE);
			this.tail = new Soldier(Integer.MAX_VALUE);
		}
		
		public void init() {
			connect(this.head, this.tail);
		}
		
		public void add(Soldier soldier) {
			Soldier prev = this.tail.prev;
			connect(prev, soldier);
			connect(soldier, this.tail);
		}
		
		public void remove(Soldier soldier) {
			Soldier prev = soldier.prev;
			Soldier next = soldier.next;
			connect(prev, next);
		}
		
		public boolean isEmpty() {
			return this.head.next == this.tail;
		}
	}

	
	class Soldier {
		Soldier prev;
		Soldier next;
		int mId;
		int mTeam;
		int mScore;
		public Soldier() {}
		public Soldier(int mId) {
			this.mId = mId;
		}
		public Soldier set(int mTeam, int mScore) {
			this.mTeam = mTeam;
			this.mScore = mScore;
			return this;
		}
		public Soldier set(int mScore) {
			return this.set(this.mTeam, mScore);
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Soldier [mId=");
			builder.append(mId);
			builder.append(", mTeam=");
			builder.append(mTeam);
			builder.append(", mScore=");
			builder.append(mScore);
			builder.append("]");
			return builder.toString();
		}
		
	}
	
	// a - b 연결
	public void connect(Soldier a, Soldier b) {
		a.next = b;
		b.prev = a;
	}
	
	public void init()
	{
		// teamList 초기화
		for (int i = 1; i <= MAX_M_TEAM; i++) {
			for (int j = 1; j <= MAX_M_SCORE; j++) {
				teamList[i][j].init();
			}
		}
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
		teamList[mTeam][mScore].add(soldierArr[mID].set(mTeam, mScore));
	}
	
	public void fire(int mID)
	{
		Soldier soldier = soldierArr[mID];
		teamList[soldier.mTeam][soldier.mScore].remove(soldier);
	}

	public void updateSoldier(int mID, int mScore)
	{
		soldierArr[mID].set(mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		if(mChangeScore > 0) {
			for (int mScore = 4; mScore >= 1; mScore--) {
				int newMScore = mScore + mChangeScore;
				if(newMScore > 5) newMScore = 5;
				changeScoreAll(mTeam, mScore, newMScore);
			}
		} else if(mChangeScore < 0) {
			for (int mScore = 2; mScore <= 5; mScore++) {
				int newMScore = mScore + mChangeScore;
				if(newMScore < 1) newMScore = 1;
				changeScoreAll(mTeam, mScore, newMScore);
			}
		}
	}
	
	public void changeScoreAll(int mTeam, int mScore, int newMScore) {
		if(teamList[mTeam][mScore].isEmpty()) return;
		
		// 이전 first, last
		Soldier prevFirst = teamList[mTeam][mScore].head.next;
		Soldier prevLast = teamList[mTeam][mScore].tail.prev;
		
		// 이동할 곳 last
		Soldier nextTail = teamList[mTeam][newMScore].tail;
		Soldier nextLast = nextTail.prev;
		
		connect(nextLast, prevFirst);
		connect(prevLast, nextTail);
	}
	
	public int bestSoldier(int mTeam)
	{
		int res = 0;
		for (int mScore = 5; mScore >= 1; mScore--) {
			if(teamList[mTeam][mScore].isEmpty()) continue;
			
			Soldier head = teamList[mTeam][mScore].head;
			Soldier tail = teamList[mTeam][mScore].tail;
			for (Soldier soldier = head.next; soldier.next != null; soldier = soldier.next) {
//				if(soldier == null) {
//					System.out.println(mTeam);
//					print();
//				}
//				System.out.println(soldier);
				res = Math.max(soldier.mId, res);
//				System.out.println(soldier + " : " + res);
			}
			if(res != 0) return res;
		}
		return 0;
	}
	
	public void print() {
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5; j++) {
				System.out.println("team : " + i + " score : " + j);
				for (Soldier a =  teamList[i][j].head.next; a != null; a = a.next) {
					System.out.print(a.mId + " ");
				}
				System.out.println();
			}
		}
	}
}
