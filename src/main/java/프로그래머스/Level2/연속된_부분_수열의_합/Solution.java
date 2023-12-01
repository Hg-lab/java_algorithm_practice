package 프로그래머스.Level2.연속된_부분_수열의_합;
import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};

        int sum = 0;
        int end = 0;
        Queue<Integer> q = new LinkedList<>();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < sequence.length; i++) {
            if(k < sequence[i]) break;

            sum += sequence[i];
            q.add(sequence[i]);

            while(!q.isEmpty() && sum > k) {
                sum -= q.poll();
            }

            if(sum == k && q.size() < min) {
                min = q.size();
                end = i;
            }

        }

        return answer = new int[]{end - min + 1, end};
    }
}