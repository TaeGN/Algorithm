package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_휴대폰자판 {
	
	static final int ALL_WORDS = 26;
	static final int MAX_SUM_WORD_LEN = 1000000;
	static TriePool triePool = new TriePool(MAX_SUM_WORD_LEN);
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int MAX_N = 100000;
		String[] words = new String[MAX_N];
		try {
			while(true) {
				String input = br.readLine();
				System.out.println(input);
				if(input == null) throw new IOException();
				
				// 초기화
				triePool.init();
				
				int N = Integer.parseInt(input);
				int wordIdx = -1;
				
				Trie root = triePool.get("", null);
				for (int i = 0; i < N; i++) {
					root.add(words[++wordIdx] = br.readLine());
				}
				double avg = 0;
				for (int i = 0; i < N; i++) {
					avg += root.getCnt(words[i]);
				}
				avg /= N;
				sb.append(avg).append("\n");
			}
		} finally {
			br.close();
			System.out.println(sb);
		}
	}
	
	static class Trie {
		Trie parent;
		Trie[] children;
		String word;
		boolean isWord;
		int childCnt;
		public Trie() {
			this.children = new Trie[ALL_WORDS];
		}
		public void init() {
			Arrays.fill(children, null);
			this.isWord = false;
			this.childCnt = 0;
		}
		public void set(String word, Trie parent) {
			this.word = word;
			this.parent = parent;
		}
		public void add(String word) {
			this.add(word, 0, word.length() - 1, this);
		}
		private void add(String word, int start, int end, Trie trie) {
			if(start > end) {
				this.isWord = true;
				return;
			}
			
			int end1 = this.word.length();
			int end2 = end - start + 1;
			int i = 0;
			// 저장된 단어 비교
			for (int len = Math.min(end1, end2); i < len; i++) {
				if(this.word.charAt(i) != word.charAt(start + i)) break;
			}
			
			// 단어 분리 ex) hello, hell -> parent : "hell", child1 : "o", child2 : "";
			Trie parent = triePool.get(this.word.substring(0, i), this.parent);
			if(i <= end1) {
				Trie child1 = this;
				child1.set(i == end1 ? "" : this.word.substring(i + 1, end1), parent);
				parent.addChild(this.word.charAt(i) - 'a', child1);
				this.add(this.word, i + 1, end1, child1);
			} else {
				this.isWord = true;
			}
			
			if(i <= end2) {
				Trie child2 = triePool.get(i == end2 ? "" : word.substring(start + i + 1, end), parent);
				parent.addChild(word.charAt(start + i) - 'a', child2);
				this.add(word, start + i + 1, end, child2);
			} else {
				this.isWord = true;
			}
			
			if(trie.children[word.charAt(start)] == null) {
				trie.children[word.charAt(start)] = triePool.get(word.substring(start, end), trie);
			} else {
				this.add(word, start + 1, end, trie.children[word.charAt(start)]);
			}
		}
		private void addChild(int idx, Trie child) {
			this.children[idx] = child;
			this.childCnt++;
		}
		public int getCnt(String word) {
			return this.getCnt(word, 1, word.length() - 1, this.children[word.charAt(0) - 'a']);
		}
		private int getCnt(String word, int start, int end, Trie trie) {
			if(start > end) return 0;
			return this.getCnt(word, start + this.word.length() + 1, end, this.children[word.charAt(start + this.word.length()) - 'a']) + 1;
		}
	}
	
	static class TriePool {
		Trie[] pool;
		int idx;
		public TriePool(int size) {
			this.pool = new Trie[size];
			for (int i = 0; i < size; i++) {
				this.pool[i] = new Trie();
			}
			this.init();
		}
		public void init() {
			this.idx = -1;
		}
		public Trie get(String word, Trie parent) {
			Trie trie = this.pool[++idx];
			trie.init();
			trie.set(word, parent);
			return trie;
		}
	}
}
