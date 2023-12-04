package 프로그래머스.Level2.귤_고르기;

import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < tangerine.length; i++) {
            map.put(tangerine[i], map.getOrDefault(tangerine[i],0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a1, a2) -> a2-a1);
        for(Integer t: map.values()) {
            pq.offer(t);
        }

        while(!pq.isEmpty()) {
            k -= pq.poll();
            answer++;
            if(k <= 0) break;
        }
        return answer;
    }

}