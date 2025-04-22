
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	int N;
	long[] liquid;
	long[] selected = new long[3];
	long min = Long.MAX_VALUE;

	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		liquid = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			liquid[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(liquid);
		twoPoint();
		
		System.out.println(selected[0] + " " + selected[1] + " " + selected[2]);
	}

	void twoPoint() {
		for (int i = 0; i < N - 2; i++) {
			int left = i + 1;
			int right = N - 1;

			while (left < right) {
				long sum = liquid[i] + liquid[left] + liquid[right];

				if (Math.abs(sum) < min) {
					min = Math.abs(sum);
					
					selected[0] = liquid[i];
					selected[1] = liquid[left];
					selected[2] = liquid[right];
					// 오름차순 정렬 (swap 방식)
					if (selected[0] > selected[1]) swap(0, 1);
					if (selected[1] > selected[2]) swap(1, 2);
					if (selected[0] > selected[1]) swap(0, 1);
				}

				if (sum < 0) {
					left++;
				} else if (sum > 0) {
					right--;
				} else {
					// sum == 0인 경우 바로 정답
					return;
				}
			}
		}
	}

	void swap(int i, int j) {
		long temp = selected[i];
		selected[i] = selected[j];
		selected[j] = temp;
	}

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}
