package swea.b형특강.b형문제.p2섬지키기;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class UserSolution_pass2
{
	private final int INF;
	private final int MAX_N;
	private final int MAX_M_STRUCTURE;
	private final int[] M_STRUCTURE_HASH_POWER;
	private final int[] M_STRUCTURE_POWER;
	private final int[] M_STRUCTURE;
	private final int[] dr, dc;
	private final Queue<Integer> QUEUE;
	private final Set<Integer> M_STRUCTURE_HASH_SET;
	private final Map<Integer, CandidateList> CANDIDATE_MAP;
	private final CandidatePool CANDIDATE_POOL;
	private int N;
	private int[][] mMap, copyMMap;
	
	public UserSolution_pass2() {
		this.INF = Integer.MAX_VALUE;
		this.MAX_N = 20;
		this.MAX_M_STRUCTURE = 5;
		dr = new int[] {0,0,1,-1};
		dc = new int[] {1,-1,0,0};
		this.M_STRUCTURE = new int[MAX_M_STRUCTURE];
		
		// POWER 초기화
		this.M_STRUCTURE_POWER = new int[MAX_M_STRUCTURE + 1];
		this.M_STRUCTURE_HASH_POWER = new int[MAX_M_STRUCTURE + 1];
		for (int i = 0; i <= MAX_M_STRUCTURE; i++) {
			this.M_STRUCTURE_POWER[i] = (int) Math.pow(10, i);
			if(i != 0 ) this.M_STRUCTURE_HASH_POWER[i] = this.M_STRUCTURE_HASH_POWER[i - 1] * 10 + 1;
		}
		
		// M_STRUCTURE_HASH_SET 초기화
		this.M_STRUCTURE_HASH_SET = new HashSet<>();
		for (int i = 1; i <= MAX_M_STRUCTURE; i++) {
			int power = M_STRUCTURE_HASH_POWER[i];
			for (int j = 1; j < 10; j++) {
				M_STRUCTURE_HASH_SET.add(power * j);
			}
		}
		// CANDIDATE_MAP 초기화
		this.CANDIDATE_MAP = new HashMap<>();
		candidateMapInit(0, 0);
		
		this.QUEUE = new ArrayDeque<>(MAX_N * 4);
		this.CANDIDATE_POOL = new CandidatePool();
		this.copyMMap = new int[MAX_N][MAX_N];
	}
	
	// CANDIDATE_MAP 초기화
	private void candidateMapInit(int cnt, int forward) {
		if(cnt == 5) {
			if(!this.CANDIDATE_MAP.containsKey(forward)) {
				CandidateList list = new CandidateList();
				this.CANDIDATE_MAP.put(forward, list);
				int reverse = 0;
				while(forward > 0) {
					reverse = reverse * 10 + forward % 10;
					forward /= 10;
				}
				this.CANDIDATE_MAP.put(reverse, list);
			};
			return;
		}
		
		for (int i = 1; i <= 5; i++) {
			candidateMapInit(cnt + 1, forward * 10 + i);
		}
		candidateMapInit(cnt + 1, forward);
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
		List<Candidate> candidates;
		int maxArea;
		boolean exist;
		
		public CandidateList() {
			this.candidates = new ArrayList<>(MAX_N);
			this.clear();
		}
		
		public void clear() {
			this.candidates.clear();
			this.maxArea = -INF;
			this.exist = false;
		}
		
		public void add(Candidate candidate) {
			this.candidates.add(candidate);
		}
		
		public Candidate get(int idx) {
			return this.candidates.get(idx);
		}
		
		public int size() {
			return this.candidates.size();
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
		for (CandidateList candidateList : CANDIDATE_MAP.values()) {
			candidateList.clear();
		}
	}
	
	/**
	 * @param 1 <= M <= 5
	 * @param 1<= mStructure[] <= 5
	 * @return
	 */
	public int numberOfCandidate(int M, int mStructure[])
	{
		int forwardHash = getHash(M, mStructure, true);
		CandidateList list = CANDIDATE_MAP.get(forwardHash);

		// 이미 구한 적이 있으면 다시 실행 x
		if(list.exist) return list.size();
		list.exist = true;

		int reverseHash = getHash(M, mStructure, false);
		int mStructurePower = M_STRUCTURE_POWER[M];
		
		// row, col방향 각각 hash값 구하기
		for (int i = 0; i < N; i++) {
			int rowHash = 0;
			int colHash = 0;
			for (int j = 0; j < N; j++) {
				rowHash = (rowHash * 10 + mMap[i][j] - 1) % mStructurePower;
				colHash = (colHash * 10 + mMap[j][i] - 1) % mStructurePower;
				
				if(j < M - 1) continue;
				// hash값이 존재하면 구조물 설치 가능;
				if(M_STRUCTURE_HASH_SET.contains(forwardHash + rowHash)) {
					list.add(CANDIDATE_POOL.get(i, j, mStructure[M - 1] + mMap[i][j], true));
				} else if(M_STRUCTURE_HASH_SET.contains(reverseHash + rowHash)) {
					list.add(CANDIDATE_POOL.get(i, j, mStructure[0] + mMap[i][j], true));
				}
				
				// 크기가 1인 구조물에서 row, col 중복 제거
				if(M != 1) {
					if(M_STRUCTURE_HASH_SET.contains(forwardHash + colHash)) {
						list.add(CANDIDATE_POOL.get(j, i, mStructure[M - 1] + mMap[j][i], false));
					} else if(M_STRUCTURE_HASH_SET.contains(reverseHash + colHash)) {
						list.add(CANDIDATE_POOL.get(j, i, mStructure[0] + mMap[j][i], false));
					}
				}
			}
		}
		
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
		int forwardHash = getHash(M, mStructure, true);
		CandidateList candidateList = CANDIDATE_MAP.get(forwardHash);
		int maxArea = candidateList.maxArea;
		// 이미 구한적이 있으면 바로 리턴
		if(maxArea >= -1) {
			return maxArea;
		} 
		
		int len = this.numberOfCandidate(M, mStructure);
		if(len == 0) return -1;
		
		int[][] mMap;
		int r, c, rr, cc, cnt, maxCnt = 0;
		Queue<Integer> queue = this.QUEUE;
		
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
				M_STRUCTURE[idx++] = mMap[candidate.r][candidate.c - j];
				mMap[candidate.r][candidate.c - j] = candidate.h;
			}
		} else {
			for (int j = 0; j < M; j++) {
				M_STRUCTURE[idx++] = mMap[candidate.r - j][candidate.c];
				mMap[candidate.r - j][candidate.c] = candidate.h;
			}
		}
	}
	
	// mStructure의 hash값 구하기
	public int getHash(int M, int mStructure[], boolean isForward) {
		int start, end, diff;
		if(isForward) {
			start = 0;
			end = M;
			diff = 1;
		} else {
			start = M - 1;
			end = -1;
			diff = -1;
		}
		
		int hash = 0;
		for (int i = start; i != end; i += diff) {
			hash = hash * 10 + mStructure[i];
		}
		return hash;
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