package swea.b형특강.b형문제.p7끝말잇기2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * 
 * @author TaeG
 *JAVA
언어
109,816kb
메모리
473ms
시간
2,742B
코드길이
Pass
결과
 */

class UserSolution_pass_t242 {
	
	final int MAX_N = 50_000;
	final int MAX_M = 50_000;
	final int MAX_WORD_LEN = 10;
	final int MOD = 27;
	final long[] power;
	final PriorityQueue<Long>[] wordPq;
	final Map<Long, Long> reverse;
	final Set<Long> selectedAll;
	final long[] selected;
	final boolean[] fail;
	int N, selectedIdx;
	
	public UserSolution_pass_t242() {
		this.power = new long[MAX_WORD_LEN];
		this.power[MAX_WORD_LEN - 1] = 1;
		for (int i = MAX_WORD_LEN - 2; i >= 0; i--) {
			this.power[i] = this.power[i + 1] * MOD;
		}
		
		this.wordPq = new PriorityQueue[this.MOD + 1];
		for (int i = 1; i <= this.MOD; i++) {
			wordPq[i] = new PriorityQueue<Long>();
		}
		
		this.reverse = new HashMap<>(MAX_M * 2);
		this.selectedAll = new HashSet<>(MAX_M * 2);
		this.selected = new long[MAX_M];
		this.fail = new boolean[MAX_N + 1];
	}
	
    private void addWordPq(char c, char[] words) {
    	long forwardHash = 0;
    	long backwardHash = 0;
    	int i = 0;
		for (i = 0; i < MAX_WORD_LEN; i++) {
			if(!Character.isLowerCase(words[i])) break;
			forwardHash += (getInt(words[i])) * power[i];
		}
		for (int j = 0; j < i; j++) {
			backwardHash += (getInt(words[i - j - 1])) * power[j];
		}
		this.reverse.put(forwardHash, backwardHash);
		this.reverse.put(backwardHash, forwardHash);
		this.wordPq[getInt(c)].offer(forwardHash);
		this.selectedAll.add(forwardHash);
	}
    
    private int getInt(char c) {
    	return c - 'a' + 1;
    }
    
    private int getLastIdx(long word) {
    	int a = 0;
    	while(a == 0) {
    		a = (int) (word % MOD);
    		word /= MOD;
    	}
    	return a;
    }
    private int getFirstIdx(long word) {
    	return (int) (word / power[0]);
    }
	
    public void init(int N, int M, char[][] mWords)
    {
    	this.reverse.clear();
    	this.selectedAll.clear();
		for (int i = 1; i <= this.MOD; i++) {
			wordPq[i].clear();
		}
    	this.N = N;
    	this.selectedIdx = -1;
    	Arrays.fill(fail, 1, N + 1, false);
    	for (int i = 0; i < M; i++) {
    		this.addWordPq(mWords[i][0], mWords[i]);
		}
    }

	public int playRound(int mID, char mCh)
    {
		for (int i = 0; i <= this.selectedIdx; i++) {
			long reverse = this.reverse.get(this.selected[i]);
			if(this.selectedAll.contains(reverse)) continue;
			this.wordPq[getFirstIdx(reverse)].offer(reverse);
		}
		this.selectedIdx = -1;
		
		int idx = getInt(mCh);
		while(!this.wordPq[idx].isEmpty()) {
			long word = wordPq[idx].poll();
			this.selected[++this.selectedIdx] = word;
			idx = getLastIdx(word);
			while(fail[mID = mID % this.N + 1]);
		}
		
		this.fail[mID] = true;
        return mID;
    }
}