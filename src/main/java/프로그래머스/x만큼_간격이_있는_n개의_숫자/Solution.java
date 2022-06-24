package 프로그래머스.x만큼_간격이_있는_n개의_숫자;


// -10,000,000 <= x <= 10,000,000
// n <= 1000
import java.util.ArrayList;
import java.util.List;

class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];

        for(int i = 1; i <= n; i++) {
            answer[i-1]=(long)x*i;
        }

        return answer;
    }
}
