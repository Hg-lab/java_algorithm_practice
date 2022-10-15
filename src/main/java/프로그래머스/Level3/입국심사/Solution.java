package 프로그래머스.Level3.입국심사;

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = times[0];
        long right = (long)times[times.length-1] * n;

        while(left <= right) {
            long mid = (right + left) / 2;
            long processed = 0;

            for(int time : times) {
                processed += mid / (long)time;
            }

            if(processed < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}

class Solution2 {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);

        long left = (long)times[0];
        long right = (long)times[times.length-1] * n;

        while(left <= right) {
            long mid = left + (right-left)/2;
            long processed = 0;

            for(int time: times)
                processed += mid/time;
            if(processed < n)
                left = mid + 1;
            else right = mid - 1;
        }

        return answer = left;
    }
}