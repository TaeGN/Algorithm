package programmers.lv3.ok.베스트앨범;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution1 {
	public static void main(String[] args) {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		System.out.println(Arrays.toString(Solution(genres, plays)));
	}
	
    public static int[] Solution(String[] genres, int[] plays) {
    	int len = genres.length;
    	Music[] musics = new Music[len];
    	for(int i = 0; i < len; i++) {
    		musics[i] = new Music(i, genres[i], plays[i]);
    	}
    	Map<String, Integer> totalPlay = new HashMap<>();
    	for(Music music : musics) {
    		totalPlay.compute(music.genre, (k, v) -> v == null ? music.play : v + music.play);
    	}
    	
    	Arrays.sort(musics, (o1, o2) -> {
    		if(!o1.genre.equals(o2.genre)) return totalPlay.get(o2.genre) - totalPlay.get(o1.genre);
    		if(o1.play != o2.play) return o2.play - o1.play;
    		return o1.idx - o2.idx;
    	});
    	
    	String s; int i = 0, j = 0;
        int[] answer = new int[totalPlay.size() * 2];
        while(i < len) {
        	s = musics[i].genre;
        	answer[j++] = musics[i].idx;
        	if(++i < len && s.equals(musics[i].genre)) {
        		answer[j++] = musics[i].idx;
        		while(++i < len && s.equals(musics[i].genre));
        	} 
        }
        return Arrays.copyOfRange(answer, 0, j);
    }
    
    static class Music{
    	int idx;
    	String genre;
    	int play;
    	
		public Music(int idx, String genre, int play) {
			super();
			this.idx = idx;
			this.genre = genre;
			this.play = play;
		}
    	
    }
}
