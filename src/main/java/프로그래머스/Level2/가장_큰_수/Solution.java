package 프로그래머스.Level2.가장_큰_수;

import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] stringNumbers = Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new);

        Arrays.sort(stringNumbers, (o1, o2) -> (o2+o1).compareTo(o1+o2));
        StringBuilder sb = new StringBuilder();
        for(String s : stringNumbers)
            sb.append(s);
        if(sb.charAt(0) == '0') return "0";
        return answer = sb.toString();
    }
}