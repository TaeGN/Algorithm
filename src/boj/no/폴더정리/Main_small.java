package boj.no.폴더정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_small {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		for (int i = 0; i < N + M; i++) {
			input = br.readLine().split(" ");
			String parentName = input[0];
			String childName = input[1];
			
			// 부모 폴더가 folderMap에 없을 경우 생성
			if(!folderMap.containsKey(parentName)) {
				folderMap.put(parentName, new Folder());
			}
			Folder parent = folderMap.get(parentName);
			
			// child가 폴더일 경우
			if(input[2].equals("1")) {
				
				// 자식 폴더가 folderMap에 없을 경우 생성
				if(!folderMap.containsKey(childName)) {
					folderMap.put(childName, new Folder());
				}
				Folder child = folderMap.get(childName);
				
				// 부모 폴더 내부에 자식 폴더 넣기
				parent.folders.add(child);
			} else {
				// child가 파일일 경우
				// 부모 폴더 내부에 자식 파일 넣기
				parent.files.put(childName, 1);
			}
		}
		
		// main부터 file정리
		setFiles(folderMap.get("main"));
		
		int Q = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < Q; i++) {
			input = br.readLine().split("/");
			Folder folder = folderMap.get(input[input.length - 1]);
			sb.append(folder.files.size()).append(" ");
			int totalCnt = 0;
			for (int cnt : folder.files.values()) {
				totalCnt += cnt;
			}
			sb.append(totalCnt).append("\n");
		}
		
		br.close();
		System.out.println(sb);
		
	}
	
	public static Map<String, Integer> setFiles(Folder parent) {
		Map<String, Integer> files = parent.files;
		
		for (Folder child : parent.folders) {
			if(!child.setOk) setFiles(child);
			for (Map.Entry<String, Integer> entry : child.files.entrySet()) {
				files.compute(entry.getKey(), (key, cnt) -> cnt == null ? entry.getValue() : cnt + entry.getValue());
			}
		}
		
		parent.setOk = true;
		return parent.files;
	}
	
	static Map<String, Folder> folderMap = new HashMap<>();
	
	static class Folder {
		List<Folder> folders;
		Map<String, Integer> files;
		boolean setOk;
		
		public Folder() {
			this.folders = new ArrayList<>();
			this.files = new HashMap<>();
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Folder [folders=").append(folders).append(", files=").append(files).append("]");
			return builder.toString();
		}
		
		
	}
}
