package 프로그래머스.Level2.모음사전;

import java.util.*;

class Solution {
    public int solution(String word) {
        int answer = 1;
        Character[] alpha = {'A','E','I','O','U'};
        for(int i = 0; i < word.length(); i++) {
            int index = Arrays.asList(alpha).indexOf(word.charAt(i));
            if(index > -1)
                answer += index*(accumulatePow(5,4 - i));
        }

        answer += word.length() - 1;
        return answer;
    }

    private int accumulatePow(int a, int b) {
        int result = 0;
        for(int i = 0; i <= b; i++) {
            result += (int)Math.pow(a,i);
        }
        return result;
    }
}