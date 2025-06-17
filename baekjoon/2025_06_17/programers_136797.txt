import java.util.*;

class Info {
    int row, col;
    Info(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
    int[][] costMap = new int[10][10];
    Map<Integer, Info> locationMap = new HashMap<>();
    int[][][] dp;  // 메모이제이션
    String numbers;

    int[] dRow = {-1, 1, 0, 0, -1, -1, 1, 1};  // 상하좌우 + 대각
    int[] dCol = {0, 0, -1, 1, -1, 1, -1, 1};
    int[] dCost = {2, 2, 2, 2, 3, 3, 3, 3};

    public int solution(String numbers) {
        this.numbers = numbers;
        dp = new int[10][10][numbers.length()];
        for (int[][] arr : dp) {
            for (int[] row : arr) Arrays.fill(row, -1);
        }

        setLocationMap();
        setCostMap();

        return dfs(4, 6, 0);  // 시작 위치: 왼손 4, 오른손 6
    }

    void setLocationMap() {
        locationMap.put(0, new Info(3, 1));
        for (int i = 1; i <= 9; i++) {
            int row = (i - 1) / 3;
            int col = (i - 1) % 3;
            locationMap.put(i, new Info(row, col));
        }
    }

    void setCostMap() {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (i == j) {
                    costMap[i][j] = 1;
                } else {
                    costMap[i][j] = bfs(i, j);
                }
            }
        }
    }

    int bfs(int from, int to) {
        Info start = locationMap.get(from);
        Info end = locationMap.get(to);
        boolean[][] visited = new boolean[4][3];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start.row, start.col, 0});
        visited[start.row][start.col] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0], col = cur[1], cost = cur[2];

            if (row == end.row && col == end.col) {
                return cost;
            }

            for (int i = 0; i < 8; i++) {
                int nr = row + dRow[i], nc = col + dCol[i];
                if (0 <= nr && nr < 4 && 0 <= nc && nc < 3 && !(nr == 3 && nc != 1) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc, cost + dCost[i]});
                }
            }
        }
        return Integer.MAX_VALUE;  // 절대 도달 불가 상황은 없음
    }

    int dfs(int left, int right, int index) {
        if (index == numbers.length()) return 0;
        if (dp[left][right][index] != -1) return dp[left][right][index];

        int next = numbers.charAt(index) - '0';
        int res = Integer.MAX_VALUE;

        // 왼손으로 누를 수 있으면
        if (next != right) {
            res = Math.min(res, dfs(next, right, index + 1) + costMap[left][next]);
        }

        // 오른손으로 누를 수 있으면
        if (next != left) {
            res = Math.min(res, dfs(left, next, index + 1) + costMap[right][next]);
        }

        return dp[left][right][index] = res;
    }
}
