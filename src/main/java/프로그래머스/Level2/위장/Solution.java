package 프로그래머스.Level2.위장;

import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        for(String[] c : clothes) {
            map.put(c[1], map.getOrDefault(c[1], 0) + 1);
        }

        Iterator<Integer> it = map.values().iterator();
        answer = 1;
        while(it.hasNext()) {
            answer *= it.next().intValue()+1;
        }

        return answer - 1;
    }
}