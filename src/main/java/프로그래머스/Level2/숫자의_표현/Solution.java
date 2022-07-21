package 프로그래머스.Level2.숫자의_표현;

class Solution {
    public int solution(int n) {
        int answer = 0;
        int sum = 0;

        // Define starting number
        for(int i = 1; i <= n; i++) {
            sum = 0;

            // Loop and add from starting number
            for(int j = i; j <= n; j++) {
                sum += j;
                // Check if sum equals n
                if(sum == n) {
                    ++answer;
                    break;
                    // Do not need add more
                } else if (sum > n) {
                    break;
                }
            }
        }

        return answer;
    }
}