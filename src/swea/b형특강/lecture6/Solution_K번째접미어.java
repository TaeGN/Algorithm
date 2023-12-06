package swea.b형특강.lecture6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_K번째접미어 {
	
	static final int OFFSET = 'a';
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int MAX_WORD_LEN = 400;
		int[] resArr = new int[MAX_WORD_LEN];
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			sb.append("#").append(tc).append(" ");
			
			int K = Integer.parseInt(br.readLine());
			
			char[] word = br.readLine().toCharArray();
			int len = word.length;
			
			if(K > len) {
				sb.append("none").append("\n");
				continue;
			}
			
			Trie root = new Trie();
			for (int i = 0; i < len; i++) {
				Trie trie = root;
				for (int j = i; j < len; j++) {
					int idx = word[j] - OFFSET;
					if(trie.children[idx] == null) {
						trie.children[idx] = new Trie();
					}
					trie = trie.children[idx];
					trie.wordCnt++;
				}
				trie.isWord = true;
			}
			
			getWord(K, root, resArr, 0, sb);
			sb.append("\n");
		}
		br.close();
		System.out.println(sb);
	}
	
	static boolean getWord(int K, Trie trie, int[] word, int wordIdx, StringBuilder sb) {
 		// K번째 문자열에 도달하면 재귀 종료
		if(K == 0) {
			for (int i = 0; i < wordIdx; i++) {
				sb.append((char) (word[i] + OFFSET));
			}
			return true;
		}
		
		Trie child;
		for (int i = 0; i < 26; i++) {
			child = trie.children[i];
			if(child == null) continue;
			word[wordIdx] = i;
			if(child.wordCnt < K) {
				K -= child.wordCnt;
				continue;
			}
			if(getWord(child.isWord ? K - 1 : K, child, word, wordIdx + 1, sb)) return true;
		}
		
		return false;
	}
	
	static class Trie {
		int wordCnt;
		boolean isWord;
		Trie[] children;
		public Trie() {
			this.children = new Trie[26];
		}
	}
}
