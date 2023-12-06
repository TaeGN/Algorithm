package swea.b형특강.b형문제.p4계산게임;

class UserSolution_t2307 {
	
	final Card card;
	final int MAX_PUT_CARDS_CALL;
	final int MAX_CARD_NUM;
	final int MOD;
	final int JOKER;
	int mJoker;
			
	public UserSolution_t2307() {
		this.MAX_PUT_CARDS_CALL = 10000;
		this.MAX_CARD_NUM = 5;
		this.JOKER = 120;
		this.MOD = 20;
		this.card = new Card();
	}
	
	class Card {
		Array arr, hashArr;
		Array[] hashArrList; 
		int leftHash, rightHash;
		public Card() {
			this.arr = new Array(MAX_PUT_CARDS_CALL * MAX_CARD_NUM);
			this.hashArr = new Array(MAX_PUT_CARDS_CALL * MAX_CARD_NUM);
			this.hashArrList = new Array[MOD];
			for (int i = 0; i < MOD; i++) {
				hashArrList[i] = new Array(MAX_PUT_CARDS_CALL * MAX_CARD_NUM);
			}
		}
		public void init() {
			this.arr.init();
			this.hashArr.init();
			this.leftHash = 0;
			this.rightHash = 0;
		}
		public void init(int[] mNumbers) {
			this.init();
			for (int i = 0; i <= 3; i++) {
				this.arr.addLast(mNumbers[i]);
				this.rightHash += this.getHash(mNumbers[i]);
			}
			this.leftHash = this.rightHash;
			this.hashArr.addLast(this.rightHash);
			this.arr.addLast(mNumbers[4]);
			this.rightHash = this.rightHash - this.getHash(this.arr.get(this.arr.size() - 5)) + this.getHash(mNumbers[4]);
			this.hashArr.addLast(this.rightHash);
		}
		public void putCards(int mDir, int[] mNumbers) {
			// left
			if(mDir == 0) {
				for (int i = 4; i >= 0; i--) {
					this.arr.addFirst(mNumbers[i]);
					this.leftHash = this.leftHash - this.getHash(this.arr.get(4)) + this.getHash(mNumbers[i]);
					int idx = this.hashArr.addFirst(this.leftHash);
					this.hashArrList[this.getNum(this.leftHash)].addFirst(idx);
				}
			}
			// right
			else {
				for (int i = 0; i <= 4; i++) {
					this.arr.addLast(mNumbers[i]);
					this.rightHash = this.rightHash - this.getHash(this.arr.get(this.arr.size() - 5)) + this.getHash(mNumbers[i]);
					int idx = this.hashArr.addLast(this.rightHash);
					this.hashArrList[this.getNum(this.rightHash)].addLast(idx);
				}
			}
		}
		public int findNumber(int mNum, int mNth, int[] ret) {
			if(hashArrList[mNum].size() < mNth) return 0;
			
			int i = hashArrList[mNum].get(mNth - 1);
			for (int j = i, k = 0; j <= i + 3; j++, k++) {
				ret[k] = this.arr.getByIdx(j % this.arr.mod);
			}
			
			return 1;
		}
		
	    public void changeJoker(int mValue) {
	    	mJoker = mValue;
	    	
	    	for (int i = 0; i < MOD; i++) {
	    		this.hashArrList[i].init();
			}
	    	
			for (int i = this.hashArr.getFirstIdx(); i < this.hashArr.mod; i++) {
				this.hashArrList[this.getNum(this.hashArr.getByIdx(i))].addLast(i);
			}
			for (int i = 0; i <= this.hashArr.getLastIdx(); i++) {
				this.hashArrList[this.getNum(this.hashArr.getByIdx(i))].addLast(i);
			}
	    }
		
		private int getHash(int val) {
			return (val == -1 ? JOKER : val);
		}
		private int getNum(int val) {
			return  (val + (val / JOKER) * mJoker) % MOD;
		}
	}
	
	class Array {
		int[] arr;
		int firstIdx, lastIdx;
		final int mod;
		public Array(int size) {
			this.arr = new int[size + 100];
			this.mod = this.arr.length;
			this.init();
		}
		public void init() {
			this.firstIdx = this.arr.length;
			this.lastIdx = -1;
		}
		public int addLast(int val) {
			this.arr[++this.lastIdx] = val;
			return this.lastIdx;
		}
		public int addFirst(int val) {
			this.arr[--this.firstIdx] = val;
			return this.firstIdx;
		}
		public int get(int idx) {
			return this.arr[(this.firstIdx + idx + mod) % mod];
		}
		public int getByIdx(int idx) {
			return this.arr[idx];
		}
		public int getFirstIdx() {
			return this.firstIdx;
		}
		public int getLastIdx() {
			return this.lastIdx;
		}
		public int size() {
			return (this.mod - this.firstIdx) + this.lastIdx + 1;
		}
	}

    void init(int mJoker, int mNumbers[]) {
    	this.mJoker = mJoker % MOD;
    	this.card.init(mNumbers);
    	this.changeJoker(mJoker);
    }

    void putCards(int mDir, int mNumbers[]) {
    	this.card.putCards(mDir, mNumbers);
    }

    int findNumber(int mNum, int mNth, int ret[]) {
        return this.card.findNumber(mNum, mNth, ret);
    }

    void changeJoker(int mValue) {
    	this.card.changeJoker(mValue % MOD);
    }
}