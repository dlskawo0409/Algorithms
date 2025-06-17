import java.util.*;

class Solution {

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public int solution(String[] storage, String[] requests) {

        final int n = storage.length;
        final int m = storage[0].length();
        char[][] board = new char[n][m];

        // ① 문자열 배열 → 2-차원 문자 배열
        for (int i = 0; i < n; ++i) board[i] = storage[i].toCharArray();

        for (String req : requests) {
            char ch = req.charAt(0);

            /* ② 크레인 : 같은 글자 두 번 ⇒ 모든 ch 제거 */
            if (req.length() == 2) {
                for (int i = 0; i < n; ++i)
                    for (int j = 0; j < m; ++j)
                        if (board[i][j] == ch) board[i][j] = '.';
                continue;
            }

            /* ③ 포크리프트 : 길이 1 ⇒ 접근 가능한 ch 제거 */
            boolean[][] outside = new boolean[n][m];
            ArrayDeque<int[]> q = new ArrayDeque<>();

            /* ③-1 외부와 맞닿은 빈 칸( board == '.' )을 모두 큐에 넣어 flood-fill */
            for (int i = 0; i < n; ++i) {
                if (board[i][0] == '.') { outside[i][0] = true; q.add(new int[]{i, 0}); }
                if (board[i][m - 1] == '.') { outside[i][m - 1] = true; q.add(new int[]{i, m - 1}); }
            }
            for (int j = 0; j < m; ++j) {
                if (board[0][j] == '.') { outside[0][j] = true; q.add(new int[]{0, j}); }
                if (board[n - 1][j] == '.') { outside[n - 1][j] = true; q.add(new int[]{n - 1, j}); }
            }

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r = cur[0], c = cur[1];
                for (int k = 0; k < 4; ++k) {
                    int nr = r + dr[k], nc = c + dc[k];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    if (!outside[nr][nc] && board[nr][nc] == '.') {
                        outside[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }

            /* ③-2 삭제될 위치 표시 */
            boolean[][] remove = new boolean[n][m];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (board[i][j] != ch) continue;

                    // 테두리에 있으면 바로 접근 가능
                    if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                        remove[i][j] = true;
                        continue;
                    }

                    // 테두리가 아니면 외부 공기와 연결된 빈 칸과 맞닿았는지 확인
                    for (int k = 0; k < 4; ++k) {
                        int nr = i + dr[k], nc = j + dc[k];
                        if (board[nr][nc] == '.' && outside[nr][nc]) {
                            remove[i][j] = true;
                            break;
                        }
                    }
                }
            }

            /* ③-3 실제로 제거( '.' 로 표시 ) */
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < m; ++j)
                    if (remove[i][j]) board[i][j] = '.';
        }

        /* ④ 남은 컨테이너 개수 세기 */
        int left = 0;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (board[i][j] != '.') ++left;

        return left;
    }
}
