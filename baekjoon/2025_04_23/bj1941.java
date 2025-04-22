
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
	
	int[][] map = new int[5][5];
	int result = 0;
	
	int[] rowAppend = {-1, 1, 0, 0}; // 상 하 좌 우
	int[] colAppend = {0, 0, -1, 1};
	
	int[] visited;
	
	class Info{
		int row;
		int col;
		
		Info(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	

	public void solution() throws Exception{
		
		visited = new int[7];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 5 ; ++i) {
			String line = br.readLine(); 
				
			for(int j = 0; j < 5; ++j) {
				if(line.charAt(j) == 'S') {
					map[i][j] = 1;
				}
				else{
					map[i][j] = 0;
				}
			}
		}
		
		per(0,0);
		

		System.out.println(result);
		
	}

	void per(int start, int count) {
		if(count == 7) {
			if(isGroup()) {
				++result;
			}
			
			return;
		}
		
		for(int i = start; i < 25; ++i) {
			visited[count] = i;
			per(i + 1, count + 1);
			
		}
	}

	
	boolean isGroup() {
		Queue<Info> q = new LinkedList<>();
		
		int row = visited[0] / 5;
		int col = visited[0] % 5;
		
		
		q.add(new Info(row, col));
		
		boolean[] visitBoolean = new boolean[7];
		visitBoolean[0] = true;
		
		int count = 1;
		int edasom = map[row][col];
		
		while(!q.isEmpty()) {
			
			Info now = q.poll();
			
			for(int i = 0; i<4 ; ++i) {
				int tempRow = now.row + rowAppend[i];
				int tempCol = now.col + colAppend[i];
				
				if(isInMap(tempRow, tempCol)) {
					int temp = isInVisited(tempRow, tempCol);
					if(temp != -1 && !visitBoolean[temp]) {
						++count;
						edasom += map[tempRow][tempCol];
						q.add(new Info(tempRow, tempCol));
						visitBoolean[temp] = true;
					}
				}
				
			}
			
		}
		
//		System.out.println ("count " + count + " edasom : "+ edasom);
		
		if(count == 7 && edasom >= 4) {
//			System.out.println("true");
			return true;
		}
		
		return false;
		
	}
	
	boolean isInMap(int row, int col) {
		if(0 <= row && row < 5 && 0<= col && col < 5) {
			return true;
		}
		return false;
	}
	
	int isInVisited(int row, int col) {
		int temp = row * 5 + col;
		for(int i = 0; i < visited.length; ++i) {
			if(temp == visited[i]) {
				return i;
			}
		}
		return -1;
		
	}
	
	
	public static void main(String[]args) throws Exception{
		new Main().solution();
	}
}
