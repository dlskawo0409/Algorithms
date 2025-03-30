package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1327 {
	
	int N;
	int K;
	
	HashMap<Integer, Integer> map; 
	
	int[] list;
	
	public void solution() throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new HashMap<Integer, Integer>();
		
		list = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int n = 0; n<N ; ++n) {
			list[n] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(bfs());
		
	}
	
	
	int bfs() {
	
		
		int start = listToInt(list);
		
		int[] copy = list.clone();
		Arrays.sort(copy);
		
		int end = listToInt(copy); 
		
		if(start == end) {
			return 0; 
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		q.add(start);
		
		map.put(start, 0);
		
		while(!q.isEmpty()) {
			
			int now = q.poll();
			
			int count = map.get(now) + 1;
			
			intToList(now);
			
			for(int i = 0; i<= N - K; ++i) {
				
				switchList(i);
				int temp = listToInt(list);
				if(!map.containsKey(temp)) {
					map.put(temp, count);
					
//					System.out.println(temp);
					
					q.add(temp);
				}
				
				switchList(i);
				
			}
			
		}
		
		return map.getOrDefault(end, -1);
		
	}
	
	
	int listToInt(int[] list) {
		int result = 0;
		
		int ten = 1;
		
		for(int i = list.length -1; i >= 0; --i) {
			result += ten * list[i];
			ten *= 10;
		}
		
//		System.out.println(result);
		
		return result;
	}
	
	void intToList(int now) {
		
		for(int i = list.length -1; i >= 0; --i) {
			list[i] = now % 10;
			now /= 10;
		}
		
	}
	
	void switchList(int start) {
		
		for(int i = 0 ; i < K/2 ; ++i) {
			int left = start + i;
			int right =  start + K - i - 1;
			
			int temp = list[right];
			list[right] = list[left];
			list[left] = temp;
			
		}
		
//		System.out.println(Arrays.toString(list));
	}
	
	
	public static void main(String [] args) throws Exception{
		new bj1327().solution();
	}
}
