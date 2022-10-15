package 프로그래머스.Level2._3xn타일링;

class Solution {
    public int solution(int n) {
        int answer = 0;
        long[] dp = new long[n+1];
        if(n % 2 == 1) return 0;
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 3;
        for(int i = 2; i <= n-2; i += 2) {
            dp[i+2] = dp[i] * 3;
            for(int k = 0; k < i; k+=2) {
                dp[i+2] += 2 * dp[k];
            }
            dp[i+2] %= 1000000007;
        }

        return answer = (int)dp[n];
    }
}