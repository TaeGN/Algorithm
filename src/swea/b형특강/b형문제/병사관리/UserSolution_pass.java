package swea.b형특강.b형문제.p1병사관리2;

public class UserSolution_pass {
	
	private final int MAX_MID;
	private final int MAX_MTEAM;
	private final int MAX_MSCORE;
	private final Soldier[] soldiers;
	private final LinkedList[][] teams;

	public UserSolution_pass() {
		this.MAX_MID = 100_000;
		this.MAX_MTEAM = 5;
		this.MAX_MSCORE = 5;
		this.soldiers = new Soldier[MAX_MID + 1];
		this.teams = new LinkedList[MAX_MTEAM + 1][MAX_MSCORE + 1];
		for (int i = 1; i <= MAX_MTEAM; i++) {
			for (int j = 1; j <= MAX_MSCORE; j++) {
				this.teams[i][j] = new LinkedList();
			}
		}
	}
	
	public class LinkedList {
		Soldier head;
		Soldier tail;
		
		public LinkedList() {
			this.head = new Soldier();
			this.tail = new Soldier();
			this.init();
		}
		
		public void init() {
			this.head.next = this.tail;
			this.head.prev = this.tail;
			this.tail.next = this.head;
			this.tail.prev = this.head;
		}
		
		public boolean isEmpty() {
			return this.head.next == this.tail;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			for (Soldier cur = this.head.next; cur != this.tail; cur = cur.next) {
				builder.append(cur);
			}
			return builder.toString();
		}
		
	}
	
	public class Soldier {
		int mID;
		int mTeam;
		int mScore;
		Soldier prev;
		Soldier next;
		
		public Soldier() {}

		public Soldier(int mID, int mTeam, int mScore) {
			this.mID = mID;
			this.mTeam = mTeam;
			this.mScore = mScore;
		}

		@Override
		public String toString() {
			return this.mID + " ";
		}
		
	}
	
	public void init()
	{
		for (int i = 1; i <= MAX_MID; i++) {
			soldiers[i] = null;
		}
		for (int i = 1; i <= MAX_MTEAM; i++) {
			for (int j = 1; j <= MAX_MSCORE; j++) {
				teams[i][j].init();
			}
		}
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
		Soldier soldier = new Soldier(mID, mTeam, mScore);
		soldiers[soldier.mID] = soldier;
		this.hire(soldier);
	}
	
	private void hire(Soldier soldier) {
		LinkedList list = teams[soldier.mTeam][soldier.mScore];
		Soldier prev = list.tail.prev;
		connect(prev, soldier, list.tail);
	}
	
	public void fire(int mID)
	{
		Soldier cur = soldiers[mID];
		connect(cur.prev, cur.next);
		cur = null;
		soldiers[mID] = null;
	}

	public void updateSoldier(int mID, int mScore)
	{
		Soldier soldier = soldiers[mID];
		
		soldier.mScore = mScore;
		
		connect(soldier.prev, soldier.next);
		this.hire(soldier);
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		LinkedList[] team = teams[mTeam];
		LinkedList curList = null;
		LinkedList nextList = null;
		if(mChangeScore == 0) return;
		if(mChangeScore > 0) {
			for (int i = 4; i >= 1; i--) {
				curList = team[i];
				Soldier start = curList.head.next;
				Soldier end = curList.tail.prev;
				
				// 해당 list에 head, tail을 제외한 데이터가 없으면 continue;
				if(start == curList.tail) continue;
				
				int resultScore = i + mChangeScore;
				if(resultScore > 5) resultScore = 5;
				
				// curList 초기화
				connect(curList.head, curList.tail);
				
				// nextList에 curList의 데이터 연결
				nextList = team[resultScore];
				Soldier prev = nextList.tail.prev;
				connect(prev, start);
				connect(end, nextList.tail);
			}
		} else {
			for (int i = 2; i <= 5; i++) {
				curList = team[i];
				Soldier start = curList.head.next;
				Soldier end = curList.tail.prev;
				
				// 해당 list에 head, tail을 제외한 데이터가 없으면 continue;
				if(start == curList.tail) continue;
				
				int resultScore = i + mChangeScore;
				if(resultScore < 1) resultScore = 1;
				
				// curList 초기화
				connect(curList.head, curList.tail);
				
				// nextList에 curList의 데이터 연결
				nextList = team[resultScore];
				Soldier prev = nextList.tail.prev;
				connect(prev, start);
				connect(end, nextList.tail);
			}
		}
	}
	
	public int bestSoldier(int mTeam)
	{
		LinkedList[] team = teams[mTeam];
		LinkedList list = null;
		for (int i = 5; i >= 1; i--) {
			list = team[i];
			if(!list.isEmpty()) break;
		}
		Soldier res = list.head.next;
		for (Soldier cur = res.next; cur != list.tail; cur = cur.next) {
			res = getBestSoldier(res, cur);
		}
		return res.mID;
	}
	
	// 같은 score에서 mId 비교
	private Soldier getBestSoldier(Soldier a, Soldier b) {
		if(a.mID > b.mID) return a;
		else return b;
	}
	
	// a-b 연결
	private void connect(Soldier a, Soldier b) {
		a.next = b;
		b.prev = a;
	}
	
	// a-b-c 연결
	private void connect(Soldier a, Soldier b, Soldier c) {
		connect(a, b);
		connect(b, c);
	}
}
