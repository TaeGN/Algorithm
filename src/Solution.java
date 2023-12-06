import java.util.Comparator;
import java.util.TreeSet;

public class Solution {
	
	final int MAX_N = 0;
	final int MAX_L = 0;
	
	final int UP = 0;		// movePlayers[UP][숫자]
	final int DOWN = 1;		// movePlayers[DOWN][숫자]
	
	final int MOVE = -1;	// leaguesToMovePlayers(MOVE)
	final int TRADE = -2;	// leaguesToMovePlayers(TRADE)
	
	final Player[] players;
	final League[] leagues;
	final Player[][] movePlayers;
	final Comparator<Player> playerComparator;
	
	// 현재 테스트 케이스의 N, L의 값
	int globalN;
	int globalL;
	
	public Solution() {
		players = new Player[MAX_N];
		for (int i = 0; i < MAX_N; i++) {
			players[i] = new Player(i);
		}
		
		leagues = new League[MAX_L];
		for (int i = 0; i < MAX_L; i++) {
			leagues[i] = new League();
		}
		
		movePlayers = new Player[2][MAX_L];
		
		playerComparator = new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				// 우선 순위 정하는 코드
				return 0;
			}
		};
	}
	
	class Player {
		int mId;
		int 필요한값들;
		
		public Player(int mId) {
			this.mId = mId;
		}
		
		public void init() {
			// 필요에 따라 init 구현
		}
	}
	
	class League {
		/**
		 * 리그에 소속된 인원 = 2n + 1
		 * topSet = n + 1
		 * bottomSet = n
		 */
		TreeSet<Player> topSet;   
		TreeSet<Player> bottomSet;
		
		public League() {
			this.topSet = new TreeSet<>(playerComparator);
			this.bottomSet = new TreeSet<>(playerComparator);
		}
		
		public void init() {
			// 필요에 따라 init 구현
		}
		
		public void addPlayer(Player player) {
			if(this.topSet.isEmpty() || this.compare(player, this.topSet.last())) {
				this.topSet.add(player);
			} else {
				this.bottomSet.add(player);
			}
			this.refresh();
		}
		
		public Player pollFirst() {
			return this.topSet.pollFirst();
		}
		
		public Player pollMid() {
			return this.topSet.pollLast();
		}
		
		public Player pollLast() {
			return this.bottomSet.pollLast();
		}
		
		public void refresh() {
			while(topSet.size() < bottomSet.size()) {
				topSet.add(bottomSet.pollFirst());
			}
			
			while(topSet.size() > bottomSet.size() + 1) {
				bottomSet.add(topSet.pollLast());
			}
		}
		
		// player 비교 p1 > p2
		public boolean compare(Player p1, Player p2) {
			return playerComparator.compare(p1, p2) < 0 ? true : false;
		}
	}
	
	void init() {
		// 필요한 부분들 init
	}

	int move() {
		// leagues -> movePlayers로 이동
		leaguesToMovePlayers(MOVE);
		
		// movePlayers -> leagues로 이동
		movePlayersToLeagues();
		
		// result
		return getResult();
	}
	
	int trade() {
		// leagues -> movePlayers로 이동
		leaguesToMovePlayers(TRADE);
		
		// movePlayers -> leagues로 이동
		movePlayersToLeagues();
		
		// result
		return getResult();
	}

	/**
	 * move & trade
	 * 
	 * leagues -> movePlayers로 이동
	 * movePlayers -> leagues로 이동
	 * result
	 */
	void leaguesToMovePlayers(int status) {}
	
	void movePlayersToLeagues() {}
	
	int getResult() {return 0;}
}
