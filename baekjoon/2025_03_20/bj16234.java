
import java.io.*;
import java.util.*;

public class Main {

    int N, L, R;
    int[][] map;
    int[] dRow = {-1, 1, 0, 0}; // 상하좌우
    int[] dCol = {0, 0, -1, 1};

    class Position {
        int row, col;
        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0;

        while (true) {
            boolean[][] visited = new boolean[N][N];
            boolean moved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i, j, visited)) {
                            moved = true;
                        }
                    }
                }
            }

            if (!moved) {
                break;
            }
            days++;
        }

        System.out.println(days);
    }

    boolean bfs(int startRow, int startCol, boolean[][] visited) {
        Queue<Position> q = new LinkedList<>();
        List<Position> union = new ArrayList<>();
        q.add(new Position(startRow, startCol));
        union.add(new Position(startRow, startCol));
        visited[startRow][startCol] = true;

        int sum = map[startRow][startCol];
        while (!q.isEmpty()) {
            Position now = q.poll();

            for (int i = 0; i < 4; i++) {
                int newRow = now.row + dRow[i];
                int newCol = now.col + dCol[i];

                if (isInMap(newRow, newCol) && !visited[newRow][newCol]) {
                    int diff = Math.abs(map[newRow][newCol] - map[now.row][now.col]);
                    if (diff >= L && diff <= R) {
                        q.add(new Position(newRow, newCol));
                        union.add(new Position(newRow, newCol));
                        visited[newRow][newCol] = true;
                        sum += map[newRow][newCol];
                    }
                }
            }
        }

        if (union.size() > 1) {
            int newPopulation = sum / union.size();
            for (Position pos : union) {
                map[pos.row][pos.col] = newPopulation;
            }
            return true;
        }
        return false;
    }

    boolean isInMap(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
