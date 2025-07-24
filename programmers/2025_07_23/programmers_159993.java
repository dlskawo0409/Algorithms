import java.util.*;

class Pair{
    int row;
    int col; 
    int count = 0;
    
    Pair(int row, int col){
        this.row = row; 
        this.col = col;
    }
}

class Solution {
    
    int[] rowAppend = {-1, 1, 0, 0}; // 상 , 하 , 좌, 우
    int[] colAppend = {0, 0, -1, 1};
    String[] Map;
    
    public int solution(String[] maps) {
        int answer = 0;
        Map= maps;
        
        Pair start = new Pair(0,0);
        Pair lever = new Pair(0,0);
        Pair end = new Pair(0,0);
        for(int i = 0; i<maps.length; i++){
            for (int j = 0; j<maps[i].length(); j++){
                if(maps[i].charAt(j) == 'S'){
                    start = new Pair(i,j);
                }
                else if(maps[i].charAt(j) == 'L'){
                    lever = new Pair(i,j);
                }
                else if(maps[i].charAt(j) == 'E'){
                    end = new Pair(i,j);
                }
            }
        }
        int temp = bfs(start,lever);
        if(temp == -1){
            return -1;
        }

        
        answer += temp;
        temp = bfs(lever, end);
    
        if(temp == -1){
            return -1;
        }
        answer += temp;
        
        
        return answer;
    }
    
    protected int bfs( Pair start, Pair end ){
        start.count = 0;
        Queue<Pair> q = new LinkedList<Pair>();
        boolean [][] visited = new boolean[Map.length][Map[0].length()];
        visited[start.row][start.col] = true;
        q.add(start);

        while(!q.isEmpty()){
            Pair now = q.poll();
           
            if( now.row == end.row && now.col == end.col){
                return now.count;
            }
            
            for(int i = 0; i<4; i++){
                int tempRow = now.row + rowAppend[i];
                int tempCol = now.col + colAppend[i];
             
                if(isInMap(tempRow,tempCol) && !visited[tempRow][tempCol] &&!isWall(tempRow,tempCol) ){
                    Pair temp = new Pair(tempRow, tempCol);
                    visited[tempRow][tempCol] = true;
         
                    temp.count = now.count +1;
                    q.add(temp);
                }
                
            }
        }
        return -1;
    }
    
    protected boolean isWall(int row, int col){
        if(Map[row].charAt(col)== 'X')
            return true;
        return false;
    }
    
    protected boolean isInMap(int row, int col){
        if(0<= row && row <Map.length && 0 <= col && col <Map[row].length()){
            return true;
        }
        return false;
    }
    
    
}