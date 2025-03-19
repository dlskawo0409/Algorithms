import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	int N;
	int K;
	
	
	int[] list;
	HashMap<Long, Integer> prefixSumCount;
	
	
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list = new int[N];
		st = new StringTokenizer(br.readLine());
		
		prefixSumCount = new HashMap<Long, Integer>();
		
        for (int i = 0; i < N; i++) {
        	list[i] = Integer.parseInt(st.nextToken());
        }
		
        prefixSumCount.put(0L, 1); 

        long prefixSum = 0; 
        long count = 0; 

        for (int i = 0; i < N; i++) {
            prefixSum += list[i];

       
            if (prefixSumCount.containsKey(prefixSum - K)) {
                count += prefixSumCount.get(prefixSum - K);
            }

 
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }

        System.out.println(count);
	}
	

	
	
	public static void main(String[]args) throws Exception{
		new Main().solution();
	}
}
