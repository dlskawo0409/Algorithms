import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	int N, M;
	char[][] map;
	int[][] visited;
	int answer = 0;

	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; ++i) {
			String input = br.readLine();
			for (int j = 0; j < M; ++j) {
				map[i][j] = input.charAt(j);
			}
		}

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (visited[i][j] == 0) {
					dfs(i, j);
				}
			}
		}

		System.out.println(answer);
	}

	void dfs(int r, int c) {
		visited[r][c] = 1; // 방문 중

		int nr = r, nc = c;
		switch(map[r][c]) {
			case 'U': nr--; break;
			case 'D': nr++; break;
			case 'L': nc--; break;
			case 'R': nc++; break;
		}

		if (visited[nr][nc] == 0) {
			dfs(nr, nc);
		} else if (visited[nr][nc] == 1) {
			answer++; // 현재 경로 중 다시 방문 → 사이클 발생
		}

		visited[r][c] = 2; // 방문 완료
	}

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}
