package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main_RPGExtreme {
	
	static final char START = '@';
	static final char GROUND = '.';
	static final char WALL = '#';
	static final char ITEM_BOX = 'B';
	static final char TRAP = '^';
	static final char MONSTER = '&';
	static final char BOSS = 'M';
	static int[] dr = {0,0,-1,1};
	static int[] dc = {-1,1,0,0};
	static int N, M;
	static char[][] map;
	static Map<Integer, Monster> monsterMap;
	static Map<Integer, String> itemMap;
	static Hero hero;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = readInt(input[0]);
		M = readInt(input[1]);
		
		map = new char[N][M];
		hero = new Hero();
		
		int monsterCnt = 0;
		int itemCnt = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				switch(map[i][j]) {
				case START:
					hero.init(i, j);
					map[i][j] = GROUND;
					break;
				case MONSTER: case BOSS:
					monsterCnt++;
					break;
				case ITEM_BOX:
					itemCnt++;
					break;
				} 
			}
		}
		monsterMap = new HashMap<>(monsterCnt);
		itemMap = new HashMap<>(itemCnt);
		
		String query = br.readLine();
		
		for (int i = 0; i < monsterCnt; i++) {
			input = br.readLine().split(" ");
			monsterMap.put((readInt(input[0]) - 1) * M + readInt(input[1]) - 1, 
					new Monster(input[2],readInt(input[3]),readInt(input[4]),readInt(input[5]),readInt(input[6])));
		}
		
		for (int i = 0; i < itemCnt; i++) {
			input = br.readLine().split(" ");
			itemMap.put((readInt(input[0]) - 1) * M + readInt(input[1]) - 1, input[2] + input[3]);
		}
		
		int turns = 0;
		int len = query.length();
		while(turns < len) {
//			map[hero.r][hero.c] = GROUND;
			hero.move(query.charAt(turns++));
			if(hero.theEnd) break;
//			map[hero.r][hero.c] = START;
//			print(turns);
		}
		
		if(!hero.theEnd) {
			map[hero.r][hero.c] = START;
		}
		br.close();
		print(turns);
	}
	
	static void print(int turns) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			sb.append(String.valueOf(map[i])).append("\n");
		}
		sb.append("Passed Turns : ").append(turns).append("\n");
		sb.append("LV : ").append(hero.level).append("\n");
		sb.append("HP : ").append(hero.hp).append("/").append(hero.maxHp).append("\n");
		sb.append("ATT : ").append(hero.att).append("+").append(hero.weapon).append("\n");
		sb.append("DEF : ").append(hero.def).append("+").append(hero.armor).append("\n");
		sb.append("EXP : ").append(hero.exp).append("/").append(hero.maxExp).append("\n");
		sb.append(hero.message);
		
		System.out.println(sb);
	}
	
	static int readInt(String s) {
		return Integer.parseInt(s);
	}
	
	static class Monster {
		String name;
		int att, def, hp, exp;
		public Monster(String name, int att, int def, int hp, int exp) {
			this.name = name;
			this.att = att;
			this.def = def;
			this.hp = hp;
			this.exp = exp;
		}
	}
	
	static class Hero {
		int sr, sc, r, c;
		int maxHp, hp, att, def, level, exp, maxExp, weapon, armor;
		boolean theEnd;
		String message;
		Set<String> accessories;
		Map<Character, Integer> moveMap;
		public Hero() {
			this.maxHp = this.hp = 20;
			this.att = 2;
			this.def = 2;
			this.maxExp = ++this.level * 5;
			this.accessories = new HashSet<>();
			this.moveMap = new HashMap<>();
			this.moveMap.put('L', 0);
			this.moveMap.put('R', 1);
			this.moveMap.put('U', 2);
			this.moveMap.put('D', 3);
			this.message = "Press any key to continue.";
		}
		
		public void init(int r, int c) {
			this.sr = this.r = r;
			this.sc = this.c = c;
		}
		
		public int getTotalAtk() {
			return this.att + this.weapon;
		}
		
		public int getTotalDef() {
			return this.def + this.armor;
		}
		
		public void getWeapon(int weapon) {
			this.weapon = weapon;
		}
		
		public void getArmor(int armor) {
			this.armor = armor;
		}
		
		public boolean getAccessory(String accessory) {
			if(accessories.size() >= 4) return false;
			return accessories.add(accessory);
		}
		
		public void move(char c) {
			int d = this.moveMap.get(c);
			if(isValid(this.r + dr[d], this.c + dc[d]) && map[this.r + dr[d]][this.c + dc[d]] != WALL) {
				this.r += dr[d];
				this.c += dc[d];
			}
			
			switch(map[this.r][this.c]) {
			case ITEM_BOX:
				this.item();
				break;
			case TRAP:
				this.trap();
				break;
			case MONSTER:
				this.fight(false);
				break;
			case BOSS:
				this.fight(true);
				break;
				
			}
		}
		
		public void fight(boolean isBoss) {
			Monster monster = monsterMap.get(this.r * M + this.c);
			int monsterHp = monster.hp;
			// 첫번째 전투
			
			// 주인공 공격
			int firstAtt = this.getTotalAtk();
			// CO 효과 : 첫번째 공격 2배
			if(accessories.contains("CO")) {
				firstAtt += this.getTotalAtk();
				// CO & DX : 첫번째 공격 3배
				if(accessories.contains("DX")) {
					firstAtt += this.getTotalAtk();
				}
			}
			
			// 보스 공격
			int monsterAtt = this.getDamage(monster.att, this.getTotalDef());
			if(isBoss && accessories.contains("HU")) {
				this.hp = this.maxHp;
				monsterAtt = 0;
			}
			
			monsterHp -= this.getDamage(firstAtt, monster.def);
			if(isDead(monsterHp)) {
				this.victory(monster);
				if(isBoss) this.gameClear();
				return;
			}
			
			this.hp -= monsterAtt;
			if(isDead(this.hp)) {
				this.dead(monster.name);
				return;
			}
			
			// 두번째 전투 그 이후
			while(true) {
				monsterHp -= this.getDamage(this.getTotalAtk(), monster.def);
				if(isDead(monsterHp)) {
					this.victory(monster);
					if(isBoss) this.gameClear();
					return;
				}
				
				this.hp -= this.getDamage(monster.att, this.getTotalDef());
				if(isDead(this.hp)) {
					this.dead(monster.name);
					return;
				}
			}
		}
		
		public void trap() {
			if(accessories.contains("DX")) {
				this.hp -= 1;
			} else {
				this.hp -= 5;
			}
			if(this.hp <= 0) this.dead("SPIKE TRAP");
		}
		
		public void item() {
			String s = itemMap.get(this.r * M + this.c);
			String item = s.substring(1);
			switch(s.charAt(0)) {
			case 'W':
				getWeapon(readInt(item));
				break;
			case 'A':
				getArmor(readInt(item));
				break;
			case 'O':
				getAccessory(item);
				break;
			}
			map[this.r][this.c] = GROUND;
		}
		
		public void victory(Monster monster) {
			map[r][c] = GROUND;
			this.getExp(monster.exp);
			if(accessories.contains("HR")) {
				this.hp += 3;
				if(this.hp > this.maxHp) this.hp = this.maxHp;
			}
		}
		
		public void getExp(int exp) {
			if(accessories.contains("EX")) {
				exp *= 1.2;
			}
			this.exp += exp;
			if(this.exp >= this.maxExp) {
				this.levelUp();
			}
		}
		
		public void dead(String S) {
			if(accessories.contains("RE")) {
				accessories.remove("RE");
				this.hp = this.maxHp;
				this.r = this.sr;
				this.c = this.sc;
			} else {
				this.hp = 0;
				this.theEnd = true;
				this.message = "YOU HAVE BEEN KILLED BY " + S + "..";
			}
		}
		
		public void levelUp() {
			this.exp = 0;
			this.maxExp = ++this.level * 5;
			this.hp = (this.maxHp += 5);
			this.att += 2;
			this.def += 2;
		}
		
		public void gameClear() {
			this.theEnd = true;
			this.message = "YOU WIN!";
			map[this.r][this.c] = START;
		}
		
		private boolean isValid(int r, int c) {
			return r < N && c < M && r >= 0 && c >= 0;
		}
		
		private boolean isDead(int hp) {
			return hp <= 0;
		}
		
		private int getDamage(int att, int def) {
			return Math.max(1, att - def);
		}
	}
}
