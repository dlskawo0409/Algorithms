import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
	int [] nodeList;
	ArrayList[] AList;
	
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		nodeList = new int [N+1];
		AList = new ArrayList[N+1];
		
		for(int i = 0; i<=N;i++) {
			AList[i] =new ArrayList<Integer>();
		}
		
	//	for (int i =1 ; i<=N ; i++) {
	//		nodeList[i] = 0;
	//	}
		
		for(int i = 0 ; i<M; i++) {
			st  = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			nodeList [B] ++; 
			AList[A].add(B);
			
		}
		
		 Queue<Integer> q = new LinkedList<>();
		
		for(int i = 1 ; i<=N; i++) {
			if (nodeList[i] == 0)
				q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			System.out.print(temp);
			System.out.print(" ");
			
			for (int i = 0; i<AList[temp].size(); i++) {
				int num = (int) AList[temp].get(i);
				nodeList[num]--;
				if(nodeList[num]==0)
					q.offer(num);
						
			}
		}
		
	
		
	}

	
	public static void main(String[] args) throws Exception{
		new Main().solution();
	}

}