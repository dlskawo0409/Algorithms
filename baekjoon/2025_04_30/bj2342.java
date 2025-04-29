import java.io.InputStream;

public class Main {
    static final int INF = Integer.MAX_VALUE / 2;
    static final int[][] cost = {
        {0, 2, 2, 2, 2},
        {2, 1, 3, 4, 3},
        {2, 3, 1, 3, 4},
        {2, 4, 3, 1, 3},
        {2, 3, 4, 3, 1}
    };

    public static void main(String[] args) throws Exception {
        InputStream in = System.in;
        int[][] prev = new int[5][5];
        int[][] curr = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                prev[i][j] = INF;
            }
        }
        prev[0][0] = 0;

        int b;
        while ((b = in.read()) != -1) {
            if (b == '0') break;
            if (b == ' ' || b == '\n') continue; // 공백, 개행 무시

            int next = b - '0'; // char to int (1~4)

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    curr[i][j] = INF;
                }
            }

            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    int now = prev[l][r];
                    if (now == INF) continue;
                    if (next != r) {
                        curr[next][r] = Math.min(curr[next][r], now + cost[l][next]);
                    }
                    if (next != l) {
                        curr[l][next] = Math.min(curr[l][next], now + cost[r][next]);
                    }
                }
            }

            int[][] temp = prev;
            prev = curr;
            curr = temp;
        }

        int ans = INF;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.min(ans, prev[i][j]);
            }
        }
        System.out.println(ans);
    }
}
