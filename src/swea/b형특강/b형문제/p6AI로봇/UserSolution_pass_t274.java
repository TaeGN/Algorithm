package swea.b형특강.b형문제.p6AI로봇;

import java.util.ArrayList;

/**
 * 
JAVA
언어
111,248kb
메모리
636ms
시간
4,062B
코드길이
Pass
결과
 * 
 */
import java.util.PriorityQueue;

class UserSolution_pass_t274
{
	
	final int OK = 0;
	final int BROKEN = -1;
	final int TRASH = -2;
	final int MAX_N = 50_000;
	int cTime, wID;
	int[] lastRenewalTimeArr;
	Robot[] robotArr;
	ArrayList<Integer>[] workArr;
	PriorityQueue<Robot> minPq;
	PriorityQueue<Robot> maxPq;
	
	public UserSolution_pass_t274() {
		this.lastRenewalTimeArr = new int[MAX_N + 1];
		this.robotArr = new Robot[MAX_N + 1];
		for (int i = 1; i <= MAX_N; i++) {
			this.robotArr[i] = new Robot(i);
		}
		
		this.workArr = new ArrayList[MAX_N + 1];
		for (int i = 1; i <= MAX_N; i++) {
			this.workArr[i] = new ArrayList<Integer>();
		}
		
		this.minPq = new PriorityQueue<>(MAX_N, (o1, o2) -> {
			int i1 = (cTime - o1.lastRenewalTime) + o1.intelligence;
			int i2 = (cTime - o2.lastRenewalTime) + o2.intelligence;
			if(i1 != i2) return Integer.compare(i1, i2);
			return Integer.compare(o1.idx, o2.idx);
		});
		
		this.maxPq = new PriorityQueue<>(MAX_N, (o1, o2) -> {
			int i1 = (cTime - o1.lastRenewalTime) + o1.intelligence;
			int i2 = (cTime - o2.lastRenewalTime) + o2.intelligence;
			if(i1 != i2) return Integer.compare(i2, i1);
			return Integer.compare(o1.idx, o2.idx);
		});
	}
	
	class Robot {
		final int idx;
		int lastRenewalTime, intelligence, status;
		public Robot(int idx) {
			this.idx = idx;
		}
		public Robot(int idx, int lastRenewalTime, int intelligence, int status) {
			this.idx = idx;
			this.lastRenewalTime = lastRenewalTime;
			this.intelligence = intelligence;
			this.status = status;
		}
		public void init() {
			this.lastRenewalTime = this.intelligence = this.status = 0;
		}
		public int getTime() {
			return lastRenewalTimeArr[this.idx] = cTime;
		}
		public int getIntelligence() {
			return this.intelligence + (cTime - this.lastRenewalTime); 
		}
		public void callJob(int wID) {
			Robot robot = new Robot(this.idx, this.getTime(), this.getIntelligence(), wID);
			robotArr[this.idx] = robot;
			workArr[wID].add(this.idx);
			this.status = TRASH;
		}
		public void returnJob() {
			this.status = OK;
			this.lastRenewalTime = getTime();
			addPq(this);
		}
		public void broken() {
			this.status = BROKEN;
		}
		public void repair() {
			Robot robot = new Robot(this.idx, this.getTime(), 0, OK);
			robotArr[this.idx] = robot;
			this.status = TRASH;
			addPq(robot);
		}
		public int check() {
			if(this.status == BROKEN) return 0;
			else if(this.status > OK) return this.status * (-1);
			return this.getIntelligence();
		}
		public void addPq(Robot robot) {
			minPq.offer(robot);
			maxPq.offer(robot);
		}
	}
	
	public void init(int N)
	{
		this.cTime = 0;
		this.minPq.clear();
		this.maxPq.clear();
		for (int i = 1; i <= N; i++) {
			this.lastRenewalTimeArr[i] = 0;
			this.robotArr[i].init();
			this.minPq.offer(this.robotArr[i]);
			this.maxPq.offer(this.robotArr[i]);
		}
		for (int i = 1; i <= this.wID; i++) {
			this.workArr[i].clear();
		}
		this.wID = 0;
	}
	
	public int callJob(PriorityQueue<Robot> pq, int mNum, int wID) {
		int res = 0;
		while(mNum > 0) {
			Robot robot = pq.poll();
			if(robot.lastRenewalTime != lastRenewalTimeArr[robot.idx] || robot.status != OK) continue;
			robot.callJob(wID);
			res += robot.idx;
			mNum--;
		}
		return res;
	}

	public int callJob(int cTime, int wID, int mNum, int mOpt)
	{
		this.wID++;
		this.cTime = cTime;
		if(mOpt == 0) {
			return callJob(maxPq, mNum, wID);
		} else {
			return callJob(minPq, mNum, wID);
		}
	}

	public void returnJob(int cTime, int wID)
	{
		this.cTime = cTime;
		for (int idx : workArr[wID]) {
			Robot robot = robotArr[idx];
			if(robot.status != wID) continue;
			robot.returnJob();
		}
	}

	public void broken(int cTime, int rID)
	{
		this.cTime = cTime;
		Robot robot = robotArr[rID];
		if(robot.status > OK) robot.broken();
	}

	public void repair(int cTime, int rID)
	{
		this.cTime = cTime;
		Robot robot = robotArr[rID];
		if(robot.status == BROKEN) robot.repair();
	}

	public int check(int cTime, int rID)
	{
		this.cTime = cTime;
		Robot robot = robotArr[rID];
		return robot.check();
	}
}