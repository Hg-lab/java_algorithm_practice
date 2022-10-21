package 프로그래머스.Level2.큰_수_만들기;

import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        StringBuilder sb= new StringBuilder();
        int start = 0;
        int end = k;
        int max = 0;
        int maxIndex = 0;
        int rest = k;

        while(sb.length() < number.length() - k && start < number.length()) {

            for(int i = start; i <= end; i++) {
                if(max < number.charAt(i)-48) {
                    maxIndex = i;
                    max = number.charAt(i)-48;
                }
            }
            sb.append(max);

            rest = rest - (maxIndex - start);

            start = maxIndex + 1;
            end = (start + rest >= number.length()) ? number.length()-1 : start + rest;
            max = 0;


            if(rest <= 0) {
                for(int i = start; i < number.length(); i++) {
                    sb.append(number.charAt(i)-48);
                }
            }
        }

        return answer = sb.toString();
    }
}