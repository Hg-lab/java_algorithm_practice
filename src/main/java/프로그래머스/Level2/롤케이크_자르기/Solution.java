package 프로그래머스.Level2.롤케이크_자르기;

import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> whole = new HashMap<>();

        for(int t: topping) {
            whole.put(t, whole.getOrDefault(t, 0) + 1);
        }

        Set<Integer> half = new HashSet<>();
        for(int i = 0; i < topping.length; i++) {
            int t = topping[i];
            half.add(t);

            if(whole.get(t) == 1) whole.remove(t);
            else whole.put(t, whole.get(t) - 1);

            if(half.size() == whole.size()) {
                answer++;
            }
        }

        return answer;
    }
}