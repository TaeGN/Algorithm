package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_구간합구하기_seg4 {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);
		
		STree sTree = new STree(N);
		
		long[] arr = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		print("init 실행 전", sTree);
		sTree.init(arr);
		print("init 실행 후", sTree);
		
		print("update 실행 전", sTree);
		for (int i = 0; i < M + K; i++) {
			input = br.readLine().split(" ");
			if(input[0].equals("1")) {
				int idx = Integer.parseInt(input[1]);
				long val = Integer.parseInt(input[2]);
				long diff = val - arr[idx];
				sTree.update(idx, diff);
			} else {
				sb.append(sTree.sum(input)).append("\n");
			}
		}
		print("update 실행 후", sTree);
		
		br.close();
		System.out.println(sb);
	}

	static void print(STree sTree) {
		long[] tree = sTree.tree;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= sTree.h; i++) {
			int start = (int) Math.pow(2, i);
			int end = (int) Math.pow(2, i + 1) - 1;
			for (int j = start; j <= end; j++) {
				sb.append(tree[j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void print(String msg, STree sTree) {
		System.out.println("\n" + msg + " : ");
		print(sTree);
	}
	
	static class STree{
        // 세그먼트 트리를 구현할 배열
        private long[] tree;
        private int h, treeSize;

        // 생성자에서 세그먼트 트리의 전체노드 수 계산 (즉, 배열 길이)
        STree(int n) {
            // 트리의 높이 계산
//            double treeHeight = Math.ceil(Math.log(n)/Math.log(2))+1;
//            this.h = 
//            // 트리의 노드 수 계산
//            long treeNodeCount = Math.round(Math.pow(2, treeHeight));
//            // 트리의 길이 설정
//            tree = new long[Math.toIntExact(treeNodeCount)];
			this.h = (int) Math.ceil(Math.log(n) / Math.log(2));
			this.treeSize = (int) Math.pow(2, this.h + 1);
			this.tree = new long[this.treeSize];
        }

        public void update(int idx, long diff) {
			update(1, 1, N, idx, diff);
		}

		// 세그먼트 트리의 노드 값 초기화
        long init(long[] arr, int node, int start, int end ){
            // 세그먼트 트리의 리프노드인 경우
            if (start == end) {
                // 리프노드에 배열의 값 저장 후 리턴
                return tree[node] = arr[start];
            }else{
                // 리프노드가 아닌 경우에는 자식노드의 값을 더해서 노드의 값 초기화 후 리턴
                return tree[node] = init(arr, node*2, start, (start+end)/2)
                        + init(arr, node*2+1, (start+end)/2+1, end);
            }
        }
        
        void init(long[] arr) {
        	init(arr,1,1,N);
        }

        // 배열의 특정 구간 합을 세그먼트 트리로 구하기
        long sum(int node, int start, int end, int left, int right) {
            // 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 속하지 않는 경우 0리턴
            if (end < left || right < start) {
                return 0;
            } else if (left <= start && end <= right) {
                // 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 속하는 경우 노드 값 리턴
                return tree[node];
            } else {
                // 그 외는 2가지 경우가 존재
                // 1. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 일부는 속하고 일부는 속하지 않는 경우
                // 2. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간을 모두 포함하는 경우
                // 이와 같은 경우에는 자식노드를 탐색해서 값을 리턴
                return sum(node*2, start, (start+end)/2, left, right)
                        + sum(node*2+1, (start+end)/2+1, end, left, right);
            }
        }
        
        long sum(String[] input) {
        	return sum(1,1,N,Integer.parseInt(input[1]),Integer.parseInt(input[2]));
        }

        // 배열의 특정 인데스의 값이 변경 될 경우 세그먼트 트리의 노드 값 변경(차이 값을 더하는 방법)
        void update(int node, int start, int end, int index, long diff) {
            // 노드가 가지는 값의 구간에 배열의 인덱스(값이 변경 될 인덱스)가 포함되지 않을 경우7
            if (index < start || end < index) {
                // 아무것도 안함
                return;
            }else {
                // 노드가 가지는 값의 구간에 배열의 인덱스(값이 변경 될 인덱스)가 포함되는 경우
                // 노드의 값 + 차이값(변경할 값-기존값)
                tree[node] = tree[node] + diff;

                // 노드가 리프노드가 아닌 경우
                if (start != end) {
                    // 리프노드까지 계속 자식노드를 탐색
                    update(node*2, start, (start+end)/2, index, diff) ;
                    update(node*2+1, (start+end)/2+1, end, index, diff) ;
                }
            }
        }
        

        // 배열의 특정 인데스의 값이 변경 될 경우 세그먼트 트리의 노드 값 변경(노드 값을 직접 변경)
        long update2(int node, int start, int end, int index, long changeValue) {
            // 노드가 가지는 값의 구간에 배열의 인덱스(값이 변경 될 인덱스)가 포함되지 않을 경우
            if (index < start || end < index) {
                // 트리의 노드 값 리턴
                return tree[node];
            } else if (start == index && end == index) {
                // 노드가 가지는 값의 구간과 배열의 인덱스(값이 변경 될 인덱스)값이 같은 경우
                // 노드의 값을 변경 될 값으로 변경
                return tree[node] = changeValue;
            } else {
                // 노드가 가지는 값의 구간에 배열의 인덱스(값이 변경 될 인덱스)값이 포함되는 경우(같은 경우는 제외)
                // 자식 노드를 탐색 후 값을 더해서 리턴
                return tree[node] = update2(node * 2, start, (start + end) / 2, index, changeValue) +
                        update2(node * 2 + 1, (start + end) / 2 + 1, end, index, changeValue);
            }
        }
    }
}
