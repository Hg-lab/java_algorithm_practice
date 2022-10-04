package 프로그래머스.Level2.영어_끝말잇기;

import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>();
        boolean isOnGame = true;
        int cycle = 0;
        int loser = 0;
        char previous = words[0].charAt(0);
        for(int i = 0; i < words.length; i++) {
            if(set.contains(words[i]) || previous != words[i].charAt(0)) {
                loser = i % n + 1;
                cycle = i / n + 1;
                break;
            }
            else {
                set.add(words[i]);
            }

            previous = words[i].charAt(words[i].length()-1);

        }


        answer[0] = loser;
        answer[1] = cycle;
        return answer;
    }
}
