package 프로그래머스.Level2.다리를_지나는_트럭;

import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();
        int sum = 0;
        int i = 0;

        while(true) {
            answer++;
            sum += truck_weights[i];
            if(sum <= weight) {
                q.offer(truck_weights[i]);
                i++;
                if(i == truck_weights.length) {
                    answer += bridge_length;
                    break;
                }
            } else {
                q.offer(0);
                sum -= truck_weights[i];
            }

            if(q.size() == bridge_length) {
                sum -= q.peek();
                q.poll();
            }
        }

        return answer;
    }
}