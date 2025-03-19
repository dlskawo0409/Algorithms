
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
   
   int N;
   int M;

   int[][] map;
   int[] rowAppend = {-1, 1, 0, 0}; // 상 하 좌 우
   int[] colAppend = {0, 0, -1, 1};
   
   int[][][]direction = {
		   {},
		   {{0},{1},{2},{3}}, 
		   {{0,1},{2,3}},
		   {{0,3},{1,3}, {1,2},{0,2}}, // 상우, 하우, 하좌 상좌 
		   {{1,2,3},{0,2,3},{0,1,3},{0,1,2}},
		   {{0,1,2,3}}
		   };

   // 상좌0 2, 하우1 3 , 2 0 상좌
   
   class Position {
       int row, col;
       Position(int row, int col) {
           this.row = row;
           this.col = col;
       }
   }
   
   ArrayList<Position> cctvList;
   int result;
   
   public void solution() throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      
      map = new int[N][M];
      cctvList = new ArrayList<>();
      result = N*M;
      
      for(int i = 0; i<N; ++i) {
         st = new StringTokenizer(br.readLine());
         for(int j = 0; j<M; ++j) {
        	 
        	 int now = Integer.parseInt(st.nextToken());
        	 if(1<= now && now <= 5) {
        		 cctvList.add(new Position(i, j));
        	 }
        	 
            map[i][j] = now;
         }
      }

      dfs(0);
      
      System.out.println(result);
   }
   
   
   void dfs(int cctvCount) {
	   if(cctvCount == cctvList.size()) {
		   
		   result = Math.min(result, countMap(map));
		   return;
	   }
	   
	   Position cctvPosition = cctvList.get(cctvCount);
	   int cctvKind = map[cctvPosition.row][cctvPosition.col];
	  
	   for(int i = 0; i<direction[cctvKind].length; ++i) {
		   
		   Queue<Position> q = new LinkedList<>();

		   for(int appendD : direction[cctvKind][i]) {
			   
			   
			   int tempRow = cctvPosition.row + rowAppend[appendD];
			   int tempCol = cctvPosition.col + colAppend[appendD];
			   while(isInMap(tempRow, tempCol)) {
			
				   if(map[tempRow][tempCol] == 0) {
					   map[tempRow][tempCol] = -1;
					   q.add(new Position(tempRow, tempCol));
				   }
				   
				   if(map[tempRow][tempCol] == 6) {
					   break;
				   }
				   
				   tempRow += rowAppend[appendD];
				   tempCol += colAppend[appendD];
			   }
		   }
		   
		   dfs(cctvCount +1);
		   
		   while(!q.isEmpty()) {
			   Position now = q.poll();
			   
			   map[now.row][now.col] = 0;
		   }
		   
	   }
	 
   }
   
   
   int countMap(int[][] map) {
	   int result = 0;
	   for(int i = 0; i<N; ++i) {
		   for(int j = 0; j<M; ++j) {
			   if(map[i][j] == 0) {
				   ++result;
			   }
			   
		   }
	   }
	   
	   return result;
   }
   
   boolean isInMap(int row, int col) {
	   if(0<= row && row <N && 0<= col && col <M) {
		   return true;
	   }
	   return false;
   }
   
   
   public static void main(String[]args) throws Exception{
      new Main().solution();
   }
}
