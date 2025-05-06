
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static ArrayList<int[]> emptyList = new ArrayList<>();
    static boolean[][] visited;
    static int[] rowAppend = {-1, 1, 0, 0};
    static int[] colAppend = {0, 0, -1, 1};
    static int maxKill = 0;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    emptyList.add(new int[]{i, j});
                }
            }
        }

        // 모든 빈칸 중 2개 선택 (조합)
        int size = emptyList.size();
        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size; ++j) {
                int[] first = emptyList.get(i);
                int[] second = emptyList.get(j);

                // 돌 두기
                map[first[0]][first[1]] = 1;
                map[second[0]][second[1]] = 1;

                // 죽일 수 있는 상대 돌 수 계산
                int kill = countKill();

                maxKill = Math.max(maxKill, kill);

                // 원상복구
                map[first[0]][first[1]] = 0;
                map[second[0]][second[1]] = 0;
            }
        }

        System.out.println(maxKill);
    }
    

     int countKill() {
        visited = new boolean[N][M];
        int total = 0;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (!visited[i][j] && map[i][j] == 2) {
                    int killed = bfs(i, j);
                    total += killed;
                }
            }
        }

        return total;
    }

     int bfs(int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        ArrayList<int[]> group = new ArrayList<>();
        boolean isSurrounded = true;

        visited[row][col] = true;
        queue.add(new int[]{row, col});
        group.add(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int d = 0; d < 4; ++d) {
                int tempRow = now[0] + rowAppend[d];
                int tempCol = now[1] + colAppend[d];

                if (!isInMap(tempRow, tempCol)) continue;

                if (map[tempRow][tempCol] == 0) {
                    isSurrounded = false; // 빈칸과 접함 → 생존
                }

                if (!visited[tempRow][tempCol] && map[tempRow][tempCol] == 2) {
                    visited[tempRow][tempCol] = true;
                    queue.add(new int[]{tempRow, tempCol});
                    group.add(new int[]{tempRow, tempCol});
                }
            }
        }

        return isSurrounded ? group.size() : 0;
    }
    
    boolean isInMap(int row, int col) {
    	if(0 <= row && row < N && 0 <= col && col <M) {
    		return true;
    	}
    	return false;
    }
    
    public static  void main(String[] args) throws Exception {
    	new Main().solution();
    }
}
