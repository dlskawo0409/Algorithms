import java.util.*;

class Solution {
    static final int INF = 1000000;

    public int solution(int[][] info, int n, int m) {
        int[][] dp = new int[m][info.length + 1];
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[0][0] = 0; // 초기 상태

        for (int i = 0; i < info.length; i++) {
            int[][] next = new int[m][info.length + 1];
            for (int[] row : next) Arrays.fill(row, INF);
            
            for (int bTrace = 0; bTrace < m; bTrace++) {
                if (dp[bTrace][i] >= INF) continue;

                int aTrace = dp[bTrace][i];

                // 도둑 A가 i번째 아이템을 훔치는 경우
                int newATrace = aTrace + info[i][0];
                if (newATrace < n) {
                    next[bTrace][i + 1] = Math.min(next[bTrace][i + 1], newATrace);
                }

                // 도둑 B가 i번째 아이템을 훔치는 경우
                int newBTrace = bTrace + info[i][1];
                if (newBTrace < m) {
                    next[newBTrace][i + 1] = Math.min(next[newBTrace][i + 1], aTrace);
                }
            }

            dp = next;
        }

        int result = INF;
        for (int b = 0; b < m; b++) {
            result = Math.min(result, dp[b][info.length]);
        }

        return result == INF ? -1 : result;
    }
}
