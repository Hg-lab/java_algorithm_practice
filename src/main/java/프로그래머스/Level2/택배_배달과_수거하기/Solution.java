package 프로그래머스.Level2.택배_배달과_수거하기;

import java.util.*;

class Main {
    public static void main(String[] args) {
        new Solution().solution(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0});
    }
}

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        for(int i = n-1; i >= 0; i--) {
            int delivery = cap;
            int pickup = cap;
            int count = 0;
            while (deliveries[i] > 0 || pickups[i] > 0) {
                deliveries[i] -= delivery;
                pickups[i] -= pickup;
                count++;
            }
            if(i>0) {
                deliveries[i-1] += deliveries[i];
                pickups[i-1] += pickups[i];
            }

            answer += count * 2 * (i+1);
        }

        return answer;
    }
}

class Solution1 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int delivery = 0;
        int pickup = 0;

        for(int i = n-1; i >= 0; i--) {
            int count = 0;

            delivery -= deliveries[i];
            pickup -= pickups[i];

            while (delivery < 0 || pickup < 0) {
                delivery += cap;
                pickup += cap;
                count++;
            }

            answer += count * 2 * (i+1);
        }

        return answer;
    }
}