package 프로그래머스.Level2._3차_압축;

import java.util.*;

class Solution_2 {
    public int[] solution(String msg) {
        int[] answer = {};
        Map<String, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();

        for(int i = 'A'; i <= 'Z'; ++i) {
            map.put(String.valueOf((char)i), i-64);
        }

        int size = msg.length();
        int max = 26;
        int idx = 0;
        while(idx < size){
            String w = "";
            while(idx < size) {
                if(!map.containsKey(w + msg.charAt(idx))) break;
                w += msg.charAt(idx++);
            }

            ans.add(map.get(w));
            if(idx < msg.length()) {
                char c = msg.charAt(idx);
                map.put(w+c, ++max);
            }
        }

        System.out.println(ans.toString());
        answer = new int[ans.size()];
        return answer = ans.stream().mapToInt(Integer::intValue).toArray();
    }
}