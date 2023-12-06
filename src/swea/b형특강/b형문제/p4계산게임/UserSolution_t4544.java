package swea.b형특강.b형문제.p4계산게임;

class UserSolution_t4544 {
	
	final Card card;
	final int MAX_PUT_CARDS_CALL;
	final int MAX_CARD_NUM;
	final int MOD;
	final int JOKER;
	final Array[] hashArr; 
	int mJoker;
			
	public UserSolution_t4544() {
		this.MAX_PUT_CARDS_CALL = 10000;
		this.MAX_CARD_NUM = 5;
		this.JOKER = 120;
		this.MOD = 20;
		this.card = new Card();
		this.hashArr = new Array[this.MOD];
		for (int i = 0; i < this.MOD; i++) {
			hashArr[i] = new Array(MAX_PUT_CARDS_CALL * MAX_CARD_NUM);
		}
	}
	
	class Card {
		Array leftArr, rightArr, leftHashArr, rightHashArr;
		int leftHash, rightHash, leftLastIdx, rightLastIdx;
		public Card() {
			this.leftArr = new Array(MAX_PUT_CARDS_CALL * MAX_CARD_NUM);
			this.rightArr = new Array(MAX_PUT_CARDS_CALL * MAX_CARD_NUM);
			this.leftHashArr = new Array(MAX_PUT_CARDS_CALL * MAX_CARD_NUM);
			this.rightHashArr = new Array(MAX_PUT_CARDS_CALL * MAX_CARD_NUM);
		}
		public void init() {
			this.leftArr.init();
			this.rightArr.init();
			this.leftHashArr.init();
			this.rightHashArr.init();
			this.leftHash = 0;
			this.rightHash = 0;
			this.leftLastIdx = 0;
			this.rightLastIdx = 0;
		}
		public void init(int[] mNumbers) {
			this.init();
			for (int i = 0; i <= 3; i++) {
				this.rightArr.add(mNumbers[i]);
				this.leftArr.add(mNumbers[3 - i]);
				this.rightHash += this.getHash(mNumbers[i]);
			}
			this.leftHash = this.rightHash;
			this.rightHashArr.add(this.rightHash);
			this.rightArr.add(mNumbers[4]);
			this.rightHash = this.rightHash - this.getHash(this.rightArr.get(this.rightLastIdx++)) + this.getHash(mNumbers[4]);
			this.rightHashArr.add(this.rightHash);
		}
		public void putCards(int mDir, int[] mNumbers) {
			// left
			if(mDir == 0) {
				for (int i = 4; i >= 0; i--) {
					this.leftArr.add(mNumbers[i]);
					this.leftHash = this.leftHash - this.getHash(this.leftArr.get(this.leftLastIdx++)) + this.getHash(mNumbers[i]);
					this.leftHashArr.add(this.leftHash);
				}
			}
			// right
			else {
				for (int i = 0; i <= 4; i++) {
					this.rightArr.add(mNumbers[i]);
					this.rightHash = this.rightHash - this.getHash(this.rightArr.get(this.rightLastIdx++)) + this.getHash(mNumbers[i]);
					this.rightHashArr.add(this.rightHash);
				}
			}
		}
		public int findNumber(int mNum, int mNth, int[] ret) {
			boolean ok = false;
			int i = this.leftHashArr.getLastIdx(); 
			for (; i >= 0; i--) {
				if(this.getNum(this.leftHashArr.get(i)) == mNum) {
					if(--mNth == 0) {
						ok = true;
						break;
					}
				}
			}
			
			if(ok) {
				for (int j = i + 4, k = 0; j >= i + 1; j--, k++) {
					ret[k] = this.leftArr.get(j);
				}
			} else {
				for (i = 0; i <= this.rightHashArr.getLastIdx(); i++) {
					if(this.getNum(this.rightHashArr.get(i)) == mNum) {
						if(--mNth == 0) {
							ok = true;
							break;
						}
					}
				}
				if(ok) {
					for (int j = i, k = 0; j <= i + 3; j++, k++) {
						ret[k] = this.rightArr.get(j);
					}
				}
			}
			
			return ok ? 1 : 0;
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
		int idx;
		public Array(int size) {
			this.arr = new int[size + 100];
			this.init();
		}
		public void init() {
			this.idx = -1;
		}
		public void add(int val) {
			this.arr[++this.idx] = val;
		}
		public int get(int idx) {
			return this.arr[idx];
		}
		public int getLastIdx() {
			return this.idx;
		}
		public void print() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i <= this.idx; i++) {
				sb.append(this.arr[i]).append(" ");
			}
			System.out.println(sb);
		}
		public void printMod() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i <= this.idx; i++) {
				sb.append((this.arr[i] + (this.arr[i] / JOKER) * mJoker) % MOD).append(" ");
			}
			System.out.println(sb);
		}
	}

    void init(int mJoker, int mNumbers[]) {
    	this.mJoker = mJoker;
    	this.card.init(mNumbers);
    }

    void putCards(int mDir, int mNumbers[]) {
    	this.card.putCards(mDir, mNumbers);
    }

    int findNumber(int mNum, int mNth, int ret[]) {
//    	System.out.println("---- left ----");
//    	this.card.leftArr.print();
//    	this.card.leftHashArr.printMod();
//    	System.out.println("---- right ----");
//    	this.card.rightArr.print();
//    	this.card.rightHashArr.printMod();
        return this.card.findNumber(mNum, mNth, ret);
    }

    void changeJoker(int mValue) {
    	this.mJoker = mValue;
    	for (int i = 0; i < this.MOD; i++) {
    		this.hashArr[i].init();
		}
    	
    }
}