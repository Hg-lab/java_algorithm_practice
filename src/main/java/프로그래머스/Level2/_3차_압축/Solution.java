package 프로그래머스.Level2._3차_압축;

import java.util.*;

class Solution {
    public int[] solution(String msg) {
        int[] answer = {};
        Map<String, Integer> map = new HashMap<>();
        List<Integer> ansList = new ArrayList<>();
        for(int i = 1; i <= 26; i++) {
            map.put(String.valueOf((char)(i+64)), i);
        }


        int index = 26;
        int size = msg.length();
        for(int start = 0; start < size; ++start) {
            int add = 1;
            // 한글자씩 검사하기 때문에 무조건 진입하는 while 문, add는 2로 시작한다. 두글자짜리 w가 나오면 add=3 이고, 마지막에 건너뛰는 작업이 필요함
            while(start + add <= size && map.containsKey(msg.substring(start, start+add))) {
                ++add;
            }

            // 이미 사이즈를 초과해서 while문을 나온 경우
            if(start + add > size) {
                ansList.add(map.get(msg.substring(start)));
                break;
            }

            String w = msg.substring(start,start+add);
            map.put(w, ++index);
            ansList.add(map.get(w.substring(0,w.length()-1)));

            //
            if(add > 1) start += add - 2;
        }

        answer = new int[ansList.size()];
        return answer = ansList.stream().mapToInt(Integer::intValue).toArray();
    }
}
