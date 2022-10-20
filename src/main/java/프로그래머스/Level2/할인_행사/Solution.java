package 프로그래머스.Level2.할인_행사;
import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < want.length; ++i) {
            map.put(want[i],number[i]);
        }
        for(int i = 0; i < discount.length-9; ++i) {
            Map<String, Integer> mapCopied = new HashMap<>();
            mapCopied.putAll(map);

            for(int j = i; j < i+10; ++j) {
                String s = discount[j];
                if(!mapCopied.containsKey(s)) continue;
                if(mapCopied.get(s) > 0) {
                    mapCopied.put(s, mapCopied.get(s) - 1);
                }
                if(mapCopied.get(s) == 0)
                    mapCopied.remove(s);
                if(mapCopied.isEmpty()){
                    answer++;
                    break;
                }
            }
        }

        return answer;
    }
}