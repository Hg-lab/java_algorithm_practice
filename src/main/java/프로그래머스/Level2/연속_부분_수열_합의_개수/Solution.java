package 프로그래머스.Level2.연속_부분_수열_합의_개수;

import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();

        for(int i = 1; i <= elements.length; i++) {
            set.addAll(sumByLength(elements, i));
        }

        return set.size();
    }

    private Set sumByLength(int[] elements, int length) {
        Set<Integer> res = new HashSet<>();
        for(int i = 0; i < elements.length; i++) {
            int sum = 0;
            for(int j = i; j < i + length; j++) {
                int idx = j;
                if(idx >= elements.length) {
                    idx -= elements.length;
                }
                sum += elements[idx];
            }
            res.add(sum);
        }
        return res;
    }
}