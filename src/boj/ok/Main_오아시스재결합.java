package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_오아시스재결합 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Deque<Person> stack = new ArrayDeque<>();
		stack.push(new Person(Integer.MAX_VALUE));
		
		long res = 0;
		for (int i = 0; i < N; i++) {
			Person person = new Person(Integer.parseInt(br.readLine()));
			while(stack.peek().h <= person.h) {
				if(stack.peek().h == person.h) {
					person.cnt += stack.peek().cnt;
				}
				res += stack.pop().cnt;
			}
			if(stack.size() != 1) res++;
			System.out.println(res);
			stack.push(person);
			System.out.println(stack);
		}
		
		System.out.println(res);
	}
	
	static class Person {
		int h, cnt;
		public Person(int h) {
			this.h = h;
			this.cnt = 1;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Person [h=").append(h).append(", cnt=").append(cnt).append("]");
			return builder.toString();
		}
		
	}
}
	
