package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class bj12852 {
	
	int N;
	int[] dp;
	
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1];
		
		bfs();
		
		Stack<Integer> stack = new Stack<>();
		
		int now = 1;
		
		while (now != N) {
			stack.add(now);
			now = dp[now];
		}
		
		System.out.println(stack.size());
		
		stack.add(N);
		

		StringBuilder sb = new StringBuilder();
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		
		System.out.println(sb.toString());
		
	}
	
	void bfs() {
		
		Queue<Integer> q = new LinkedList<>();
		
		q.add(N);
		
		while(!q.isEmpty()) {
			
			int now = q.poll();
			
			if(now % 3 == 0 && dp[now/3] == 0) {
				dp[now/3] = now;
				q.add(now/3);
			}
			
			if(now % 2 == 0 && dp[now/2] == 0) {
				dp[now/2] = now;
				q.add(now/2);
			}
			
			if(now - 1 >= 1 && dp[now -1 ] == 0 ) {
				dp[now - 1 ] = now;
				q.add(now -1);
			}
		}
		
	}
	
	
	public static void main(String[]args) throws Exception{
		new bj12852().solution();
	}
}
