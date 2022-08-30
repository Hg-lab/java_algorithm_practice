package 프로그래머스.Level2.두_큐_합_같게_만들기;

// queue1.length == queue2.length

import java.util.Arrays;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long sum1 = 0L, sum2 = 0L;
        int maxNum = 0;

        // Compute sum
        for(int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            sum2 += (long)queue2[i];
            maxNum = Math.max(maxNum, queue1[i]);
            maxNum = Math.max(maxNum, queue2[i]);
        }

        if(maxNum > (sum1+sum2)/2 || (sum1+sum2)%2 == 1 ) return -1;
        if(sum1==sum2) return 0;

        int index1 = 0, index2 = 0;
        while(true) {
            if(index1 == queue1.length) index1 = 0;
            if(index2 == queue1.length) index2 = 0;
            long diff = Math.abs(sum1-sum2)/2;

            if(queue1[index1] == diff || queue2[index2] == diff) {
                ++answer;
                break;
            }

            if(sum2>sum1) {
                sum2 -= queue2[index2];
                sum1 += queue2[index2];
                ++index2;
                ++answer;
            } else {
                sum1 -= queue1[index1];
                sum2 += queue1[index1];
                ++index1;
                ++answer;
            }

        }

        return answer;
    }
}