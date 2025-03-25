import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class Main {
	
	int N;
	int[] input;
	int [][] dp;
	int M;
	
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		input = new int[N+1];
		dp = new int[N+1][N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int n = 1; n <= N; n++) {
			input[n] = Integer.parseInt(st.nextToken());
			Arrays.fill(dp[n], -1);
		}
		
		
		
		for(int j = 1; j <= N; ++j ) {
			for(int i = 1; i <= j; ++i) {
				if(i == j)dp[i][j] = 1;
				else if(j - i ==1) dp[i][j] = input[i] == input [j] ? 1 : 0;
				else {
					dp[i][j] = (input[i]  == input[j] && dp[i+1][j-1] == 1) ? 1: 0;
				}
				
			}
		}
		
		M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int m = 0; m<M; ++m) {
			st = new StringTokenizer(br.readLine());
			
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			sb.append(dp[S][E]).append('\n');

		}
		
		System.out.print(sb);
	}
	
  
	
	public static void main(String[]args) throws Exception{
		new Main().solution();
	}
}
