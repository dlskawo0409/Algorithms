import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        Arrays.sort(money);
        
        int[] dp = new int[n+1]; // 인덱스에 해당하는 거스름돈을 만드는 경우의 수
        dp[0] = 1;
        for(int i = 0; i < money.length; i++){
            for(int j = money[i]; j <= n; j++){
                dp[j] += dp[j - money[i]] % 1000000007;
            }
        }

        answer = dp[n];
        return answer;
    }
}