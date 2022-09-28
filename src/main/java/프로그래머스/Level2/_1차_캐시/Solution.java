package 프로그래머스.Level2._1차_캐시;

import java.util.*;

// cacheSize [0,30]
// cities.length 100,000]
// cities : 영문
// cache hit -> 1
// cache miss -> 5
// return 총 실행시간

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();

        if(cacheSize == 0) return cities.length * 5;

        for(int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            if(map.keySet().contains(city)) {
                map.put(city,i);
                answer += 1;
                continue;
            }
            if(map.size() < cacheSize) {
                map.put(city,i);
                answer += 5;
                continue;
            }
            String leastUsed = getKeyWithMin(map);
            map.remove(leastUsed);
            map.put(city, i);
            answer += 5;
        }

        return answer;
    }

    private String getKeyWithMin(Map<String,Integer> map) {
        int min = Integer.MAX_VALUE;
        String result = "";
        for(String s: map.keySet()) {
            if(min > map.get(s)) {
                min = map.get(s);
                result = s;
            }
        }
        return result;
    }
}