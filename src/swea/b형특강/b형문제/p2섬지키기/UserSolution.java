package swea.b형특강.b형문제.p2섬지키기;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class UserSolution
{
	private final int INF;
	private final int MAX_N;
	private final int MAX_M_STRUCTURE;
	private final int[] mStructureHashPower;
	private final int[] mStructurePower;
	private final int[] mStructure;
	private final int[] dr, dc;
	private final Queue<Integer> queue;
	private final Set<Integer> mStructureSet;
	private final Map<Integer, CandidateList> candidateMap;
	private final CandidatePool candidatePool;
	private final CandidateListPool candidateListPool;
	private int candidateKey;
	private int N;
	private int[][] mMap, copyMMap;
	
	public UserSolution() {
		this.INF = Integer.MAX_VALUE;
		this.MAX_N = 20;
		this.MAX_M_STRUCTURE = 5;
		this.dr = new int[] {0,0,1,-1};
		this.dc = new int[] {1,-1,0,0};
		this.mStructure = new int[MAX_M_STRUCTURE];
		
		// 10, 100, 1000 ...
		this.mStructurePower = new int[MAX_M_STRUCTURE + 1];
		// 1, 11, 111, 1111 ...
		this.mStructureHashPower = new int[MAX_M_STRUCTURE + 1];
		for (int i = 1; i <= MAX_M_STRUCTURE; i++) {
			this.mStructurePower[i] = (int) Math.pow(10, i);
			this.mStructureHashPower[i] = this.mStructureHashPower[i - 1] * 10 + 1;
		}
		
		// 1, 11 ... 2, 22 ... 9, 99 ... 99999
		this.mStructureSet = new HashSet<>();
		for (int i = 1; i <= MAX_M_STRUCTURE; i++) {
			int power = mStructureHashPower[i];
			for (int j = 1; j < 10; j++) {
				mStructureSet.add(power * j);
			}
		}
		
		this.candidateMap = new HashMap<>();
		this.queue = new ArrayDeque<>(MAX_N * 4);
		this.candidateListPool = new CandidateListPool(6*6*6*6*6);
		this.candidatePool = new CandidatePool();
		this.copyMMap = new int[MAX_N][MAX_N];
	}
	
	class Candidate {
		int r, c, h;
		boolean isRow;
		
		public Candidate(int r, int c, int h, boolean isRow) {
			super();
			this.r = r;
			this.c = c;
			this.h = h;
			this.isRow = isRow;
		}
	}
	
	class CandidateList {
		List<Candidate> list;
		int maxArea;
		public CandidateList() {
			this.list = new ArrayList<>();
			this.maxArea = -1;
		}
		public void add(Candidate candidate) {
			this.list.add(candidate);
		}
		public Candidate get(int idx) {
			return this.list.get(idx);
		}
		public int size() {
			return this.list.size();
		}
	}
	
	class CandidateListPool {
		CandidateList[] pool;
		int idx;
		public CandidateListPool(int size) {
			this.pool = new CandidateList[size];
			for (int i = 0; i < size; i++) {
				this.pool[i] = new CandidateList();
			}
		}
		public CandidateList get() {
			return this.pool[++idx];
		}
	}
	
	class CandidatePool {
		Candidate[][][] candidates;
		
		public CandidatePool() {
			candidates = new Candidate[MAX_N * MAX_N][9][2];
			for (int i = 0; i < MAX_N * MAX_N; i++) {
				for (int j = 2; j <= 10; j++) {
					for (int k = 0; k < 2; k++) {
						candidates[i][j - 2][k] = new Candidate(i / MAX_N, i % MAX_N, j, k == 0);
					}
				}
			}
		}
		
		public Candidate get(int r, int c, int h, boolean isRow) {
			return candidates[r * MAX_N + c][h - 2][isRow ? 0 : 1];
		}
	}
	
	public void init(int N, int mMap[][])
	{
		this.N = N;
		this.mMap = mMap;
		this.copyMMap = copyMap();
		this.candidateMap.clear();
	}
	
	/**
	 * @param 1 <= M <= 5
	 * @param 1<= mStructure[] <= 5
	 * @return
	 */
	public int numberOfCandidate(int M, int mStructure[])
	{
		int forwardHash = 0;
		int reverseHash = 0;
		for (int i = 0; i < M; i++) {
			forwardHash = forwardHash * 10 + mStructure[i];
			reverseHash = reverseHash * 10 + mStructure[M - i - 1];
		}
		
		// 이미 구한 적이 있으면 다시 실행 x
		if(candidateMap.containsKey(forwardHash)) {
			candidateKey = forwardHash;
			return candidateMap.get(forwardHash).size();
		}
		
		int power = mStructurePower[M];
		CandidateList list = candidateListPool.get();
		
		// row, col방향 각각 hash값 구하기
		for (int i = 0; i < N; i++) {
			int rowHash = 0;
			int colHash = 0;
			for (int j = 0; j < N; j++) {
				rowHash = (rowHash * 10 + mMap[i][j] - 1) % power;
				colHash = (colHash * 10 + mMap[j][i] - 1) % power;
				
				if(j < M - 1) continue;
				// hash값이 존재하면 구조물 설치 가능;
				if(mStructureSet.contains(forwardHash + rowHash)) {
					list.add(candidatePool.get(i, j, mStructure[M - 1] + mMap[i][j], true));
				} else if(mStructureSet.contains(reverseHash + rowHash)) {
					list.add(candidatePool.get(i, j, mStructure[0] + mMap[i][j], true));
				}
				
				// 크기가 1인 구조물에서 row, col 중복 제거
				if(M != 1) {
					if(mStructureSet.contains(forwardHash + colHash)) {
						list.add(candidatePool.get(j, i, mStructure[M - 1] + mMap[j][i], false));
					} else if(mStructureSet.contains(reverseHash + colHash)) {
						list.add(candidatePool.get(j, i, mStructure[0] + mMap[j][i], false));
					}
				}
			}
		}
		
		candidateMap.put(forwardHash, list);
		candidateMap.put(reverseHash, list);
		candidateKey = forwardHash;
		return list.size();
	}
	
	/**
	 * @param 1 <= M <= 5
	 * @param 1 <= mStructure[] <= 5
	 * @param 1 <= mSeaLevel <= 10
	 * @return
	 */
	public int maxArea(int M, int mStructure[], int mSeaLevel)
	{
		int len = this.numberOfCandidate(M, mStructure);
		if(len == 0) return -1;
		
		CandidateList candidateList = this.candidateMap.get(this.candidateKey);
		if(candidateList.maxArea != -1) return candidateList.maxArea;
		
		int[][] mMap;
		int r, c, rr, cc, cnt, maxCnt = 0;
		Queue<Integer> queue = this.queue;
		for (int i = 0; i < len; i++) {
			mMap = copyMap();
			queue.clear();
			build(mMap, candidateList.get(i), M);
			cnt = N * N;
			
			// 가장자리 부분의 높이가 mSeaLevel보다 낮은 경우 queue에 넣기
			for (int j = 0; j < N - 1; j++) {
				if(pushQueue(0, j, mMap, mSeaLevel, queue)) cnt--;
				if(pushQueue(N - 1, j + 1, mMap, mSeaLevel, queue)) cnt--;
				if(pushQueue(j + 1, 0, mMap, mSeaLevel, queue)) cnt--;
				if(pushQueue(j, N - 1, mMap, mSeaLevel, queue)) cnt--;
			}
			
			int rc;
			while(!queue.isEmpty()) {
				rc = queue.poll();
				r = rc / N;
				c = rc % N;
				for (int d = 0; d < 4; d++) {
					rr = r + dr[d];
					cc = c + dc[d];
					if(!isValid(rr, cc)) continue;
					if(pushQueue(rr, cc, mMap, mSeaLevel, queue)) cnt--;
				}
				if(cnt <= maxCnt) break;
			}
			
			maxCnt = Math.max(maxCnt, cnt);
		}
		return maxCnt;
	}

	// mMap에 구조물 설치
	private void build(int[][] mMap, Candidate candidate, int M) {
		int idx = 0;
		if(candidate.isRow) {
			for (int j = 0; j < M; j++) {
				mStructure[idx++] = mMap[candidate.r][candidate.c - j];
				mMap[candidate.r][candidate.c - j] = candidate.h;
			}
		} else {
			for (int j = 0; j < M; j++) {
				mStructure[idx++] = mMap[candidate.r - j][candidate.c];
				mMap[candidate.r - j][candidate.c] = candidate.h;
			}
		}
	}

	private boolean isValid(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}
	
	// queue에 넣을 수 있으면 true , 아니면 false
	private boolean pushQueue(int r, int c, int[][] mMap, int mSeaLevel, Queue<Integer> queue) {
		if(mMap[r][c] < mSeaLevel) {
			queue.offer(r * N + c);
			mMap[r][c] = INF;
			return true;
		} else return false;
	}
	
	// mMap 복사
	private int[][] copyMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.copyMMap[i][j] = this.mMap[i][j];
			}
		}
		return this.copyMMap;
	}
}