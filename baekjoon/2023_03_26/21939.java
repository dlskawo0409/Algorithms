
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;



public class Main {
	
	int N;
	int M;
	
	class Problem implements Comparable<Problem>{
		int L;
		int P;
		
		Problem(int L, int P){
			this.L = L;
			this.P = P;
		}

		@Override
		public int compareTo(Problem o) {
			// TODO Auto-generated method stub
			return o.L == this.L ? o.P - this.P : o.L - this.L;
		}
		
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Problem)) return false;
	        Problem p = (Problem) o;
	        return this.L == p.L && this.P == p.P;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(L, P);
	    }
	}
	
	HashMap<Integer, Problem> hashMap;
	TreeSet<Problem> problemSet;
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		hashMap = new HashMap<>();
		problemSet = new TreeSet<>();
		
		for(int n = 0; n < N; ++n) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken()); 
			
			Problem now = new Problem(L, P);
			
			problemSet.add(now);
			hashMap.put(P, now);
		}
		
		M = Integer.parseInt(br.readLine());
		
		for(int m = 0; m<M; ++m) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String query = st.nextToken();
			
			switch(query) {
				case "recommend" : 
					int x = Integer.parseInt(st.nextToken());
					if(x == 1) {
						Problem problem1 = problemSet.first();
						System.out.println(problem1.P);
					}
					else {
						Problem problem2 = problemSet.last();
						System.out.println(problem2.P);
					}
					
					break;
				
				case "add" :
					int P1 = Integer.parseInt(st.nextToken());
					int L = Integer.parseInt(st.nextToken());
					Problem problem3 = new Problem(L, P1);
					
					problemSet.add(problem3);
					hashMap.put(P1, problem3);
					
					break;
				
				case "solved" :
					
					int P2 = Integer.parseInt(st.nextToken());
					
					Problem problem4 = hashMap.get(P2);
					
					hashMap.remove(P2);
					problemSet.remove(problem4);
					
					break;
			
			}
		}
		
	}
	
	
	public static void main(String[]args) throws Exception{
		new Main().solution();
	}
}
