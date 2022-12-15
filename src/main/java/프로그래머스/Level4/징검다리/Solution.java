package 프로그래머스.Level4.징검다리;

import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        int[] distArr = getDistances(distance, rocks);
        long left = 1; long right = distance;
        while(left <= right) {
            int removed = 0;
            int current = 0;
            long mid = (left+right)/2;

            for(int dist : distArr) {
                current += dist;
                if(current < mid) removed++;
                else current = 0;
            }

            if(removed <= n) {
                left = mid + 1;
                answer = (int)mid;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }

    private int[] getDistances(int distance, int[] rocks) {
        int[] distArr = new int[rocks.length+1];
        distArr[0] = rocks[0];
        distArr[rocks.length] = distance - rocks[rocks.length-1];
        for(int i = 1; i < rocks.length; i++) distArr[i] = rocks[i] - rocks[i-1];
        return distArr;
    }
}