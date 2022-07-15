package 프로그래머스.Level2.더_맵게;

import java.util.*;


// 2 <= scoville.length <= 1,000,000
// 0 <= k <= 1,000,000,000
// 0 <= scoville[i] <= 1,000,000
// return: minimum numbers of mix to make it over than k
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int mixed = 0;
        int rtn = 0;
        for(int i = 0; i < scoville.length; i++) {
            pq.offer(scoville[i]);
        }
        while(pq.peek() < K && !pq.isEmpty()) {
            if(pq.size() == 1) {
                rtn = -1;
                break;
            }
            int frst = pq.peek();
            pq.poll();
            int scnd = pq.peek();
            pq.poll();
            mixed = frst + (scnd * 2);
            pq.offer(mixed);
            rtn++;
        }
        answer = rtn;
        return answer;
    }

}