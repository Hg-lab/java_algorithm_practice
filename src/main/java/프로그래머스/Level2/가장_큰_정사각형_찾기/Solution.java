package 프로그래머스.Level2.가장_큰_정사각형_찾기;

class Solution
{
    int h, w;
    public int solution(int [][]board)
    {
        int answer = 1234;

        this.h = board.length; this.w = board[0].length;
        int[][] dp = new int[h][w];
        int max = 0;

        for(int r = 0; r < board.length; ++r) {
            for(int c = 0; c < board[r].length; ++c) {
                int prevR = r - 1, prevC = c - 1;
                if(!isOnBoundary(prevR, prevC)) {
                    dp[r][c] = board[r][c];
                    max = Math.max(max, dp[r][c]);
                    continue;
                }

                if(board[r][c] == 1) {
                    dp[r][c] = Math.min(dp[prevR][prevC], Math.min(dp[prevR][c], dp[r][prevC])) + 1;
                    max = Math.max(max, dp[r][c]);
                }
            }
        }

        return answer = max*max;
    }
    private boolean isOnBoundary(int r, int c) {
        return(r >= 0 && c >= 0 && r < h && c < w);
    }
}