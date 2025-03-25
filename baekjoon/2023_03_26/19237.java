 


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    int N;
    int M;
    int k;
    
    int[][][] map;
    int[] rowAppend = {0, -1, 1, 0, 0}; // 빈칸 상 하 좌 우
    int[] colAppend = {0, 0 , 0, -1, 1};
    
    
    class Shark{
        
        int row;
        int col;
        int[][] direction;
        int lookingAt;
        boolean dead = false;
        
        Shark(int row, int col){
            this.row = row;
            this.col = col;
        }
        
    }
    
    Shark[] sharkList;
    
    public void solution() throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        
        map = new int[N][N][2];
        sharkList = new Shark[M+1];
        
        
        for(int i = 0; i<N; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N; ++j) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp != 0) {
                    sharkList[temp] = new Shark(i,j);
                }
                map[i][j][0] = temp;
                map[i][j][1] = k;
            }
        }
        
        st = new StringTokenizer(br.readLine());
        
        for(int m = 1; m <= M; ++m) {
            sharkList[m].lookingAt = Integer.parseInt(st.nextToken());
        }
        
        
        for(int m = 1 ; m <= M; ++m ) {
            int[][] direction = new int[5][4];
            for(int i = 1; i<=4; ++i) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<4; ++j) {
                    direction[i][j] = Integer.parseInt(st.nextToken());
                }
                
            }
            
            sharkList[m].direction = direction;
         
        }
        System.out.println(move());
        
        
        
    }
    
    
    int move() {

        
        boolean onlyOne = false;
        
        int count = 0;
        
        while(!onlyOne) {
            int[][][] newMap = new int[N][N][2];
            
            for(int m = 1; m <= M; ++m) {
                
                Shark now = sharkList[m];
                
                if(now.dead) {
                    continue;
                }
                
                
                boolean moved = false;
                
                for(int i = 0; i <4; ++i) {

                    int tempRow 
                    = now.row + rowAppend[now.direction[now.lookingAt][i]];
                    int tempCol 
                    = now.col + colAppend[now.direction[now.lookingAt][i]];
                    
                    if(isInMap(tempRow, tempCol) 
                            && map[tempRow][tempCol][0] == 0 ) {
        
                        if(newMap[tempRow][tempCol][0] == 0 ) {
                            newMap[tempRow][tempCol][0] = m;
                            newMap[tempRow][tempCol][1] = k;
                            
                            now.row = tempRow;
                            now.col = tempCol;
                            
                            now.lookingAt = now.direction[now.lookingAt][i];
                           
                            
                        }
                        else {
                            now.dead = true;
                        }

                        moved = true;
                        break;
                    }
                    
                }

                
                if(moved) {
                    continue;
                }
                
                //빈칸이 없을 경우
                for(int i = 0; i <4; ++i) {
                    
                    int tempRow 
                    = now.row + rowAppend[now.direction[now.lookingAt][i]];
                    int tempCol 
                    = now.col + colAppend[now.direction[now.lookingAt][i]];
                    
                    if(isInMap(tempRow, tempCol) 
                            && map[tempRow][tempCol][0] == m ) {
                   
                        newMap[tempRow][tempCol][0] = m;
                        newMap[tempRow][tempCol][1] = k;
                        
                        now.row = tempRow;
                        now.col = tempCol;
                        
                        now.lookingAt = now.direction[now.lookingAt][i];
                        
                        moved = true;
                        break;
                    }
                    
                }
                
                if(!moved) {
                    now.dead = true;
                }

            }
            
            if(++count > 1000) {
                return -1;
            }
            
            //맵 복사
            for(int i = 0; i < N; ++i) {
                for(int j = 0; j < N; ++j) {
                    if(newMap[i][j][0] == 0 
                            && map[i][j][0] != 0
                            && map[i][j][1] > 1) {
                        
                        newMap[i][j][0] = map[i][j][0];
                        newMap[i][j][1] = map[i][j][1] -1;
                    }
                }
            }
            
            map = newMap;
            
           
            onlyOne = checkSharkLive();
            
        }
        
        return count;
        
    }
    
    
    boolean isInMap(int row, int col) {
        if(0<= row && row < N && 0<= col && col < N) {
            return true;    
        }
        
        return false;
    }
    
    boolean checkSharkLive() {
        
        for(int i = 2; i<=M; ++i) {
            if(!sharkList[i].dead) {
                return false;
            }
        }
        return true;
    }
    
    
    public static void main(String[]args) throws Exception{
        new Main().solution();
    }
}