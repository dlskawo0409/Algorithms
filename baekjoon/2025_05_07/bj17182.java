 import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[][] timeMap;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        timeMap = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                timeMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드-워셜: 모든 쌍 간 최단 거리로 갱신
        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    timeMap[i][j] = Math.min(timeMap[i][j], timeMap[i][k] + timeMap[k][j]);

        visited[K] = true;
        dfs(K, 1, 0);

        System.out.println(min);
    }

    static void dfs(int current, int count, int time) {
        if (count == N) {
            min = Math.min(min, time);
            return;
        }

        for (int next = 0; next < N; next++) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, count + 1, time + timeMap[current][next]);
                visited[next] = false;
            }
        }
    }
}
