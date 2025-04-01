import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    
    int[][] map;
    
    int[] rowAppend = {-1, 1, 0, 0}; // 상 하 좌 우
    int[] colAppend = {0, 0, -1, 1};
    boolean[][] visited;
    
    class Point{
    	int row;
    	int col;
    	
    	Point(int row, int col){
    		this.row = row;
    		this.col = col;
    	}
    	
    }
    
    int result;
    
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        map = new int[12][6];
        result = 0;
        visited = new boolean[12][6];
        
        for(int i = 0; i<12; ++i) {
            
            String input = br.readLine();
            
            for(int j = 0; j<6; ++j) {
                char now = input.charAt(j);
                
                switch(now) {
                    case('.'):
                        map[i][j] = 0;
                        break;
                    
                    case('R'):
                        map[i][j] = 1;
                        break;
                        
                    case('G'):
                        map[i][j] = 2;
                        break;
                        
                    case('B'):
                        map[i][j] = 3;
                        break;
                        
                    case('P'):
                        map[i][j] = 4;
                        break;
                        
                    case('Y'):
                        map[i][j] = 5;
                        break;
                
                }
                
            }
        }
        
        int before = -1;
        
        goDownAll();
        
        while(result != before) {
        	
        	before = result;
        	
        	while(pop()) {
        		goDownAll();
        		++result;
        	};
        	
        	
        }
        
        System.out.println(result);
        
    }
    
    
    void goDownAll() {
    	for(int i = 11; i >= 0; --i) {
    		for(int j = 0; j<6; ++j) {
    			goDown(i, j);
    		}
    	}
    }
    
    void goDown(int row, int col) {
    	
    	for(int i = row + 1; i < 12; ++i) {
    		if(map[i][col] != 0) {
    			break;
    		}
    		
    		map[i][col] = map[i - 1][col];
    		map[i - 1][col] = 0;
    	}
    	
    }
    
    
    boolean pop() {

    	visited = new boolean[12][6];
    	
    	boolean poping = false;
    
	        for(int i = 0; i<12; ++i) {
	        	
	            for(int j = 0; j<6; ++j) {
	                if(map[i][j] == 0 || visited[i][j]) {
	                    continue;
	                }
	                
	                if(bfs(i, j) >= 4) {
	                	poping = true;
	                }
	                
	                
	            }
	           
	        }

        return poping;
    }
    
    int bfs(int row, int col) {
    	
    	int count = 1;
    	
    	Queue<Point> q = new LinkedList<>();
    	Queue<Point> history = new LinkedList<>();

    	int num = map[row][col];
    	
    	Point start = new Point(row, col);
    	visited[row][col] = true;
    	
    	q.add(start);
    	history.add(start);
    	
    	while(!q.isEmpty()) {
    		
    		Point now = q.poll();

    		for(int i = 0; i<4; i++) {
    			int tempRow = now.row + rowAppend[i];
    			int tempCol = now.col + colAppend[i];
    			
    			if(isInMap(tempRow, tempCol) 
    					&& !visited[tempRow][tempCol] 
    							&& map[tempRow][tempCol] == num) {
    				visited[tempRow][tempCol] = true;
    				
    				Point next = new Point(tempRow, tempCol);
    				
    				q.add(next);
    				history.add(next);
    				++count;
    			}
    		}
    		
    	}
    	
    	if(count >= 4 ) {
    		while(!history.isEmpty()) {
    			
    			Point now = history.poll();
    			
    			map[now.row][now.col] = 0;
    			visited[now.row][now.col] = true;
    			
    		}
    	}
    	
    	return count;
    }
    
    boolean isInMap(int row, int col) {
    	if(0 <= row && row <12 && 0 <=col && col < 6) {
    		return true;
    	}
    	
    	return false;
    }
    
    
    
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}