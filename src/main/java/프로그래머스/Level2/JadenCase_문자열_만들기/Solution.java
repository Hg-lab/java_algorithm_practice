import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        StringBuilder sb = new StringBuilder(s.toLowerCase());
        for(int i = 0; i < sb.length(); i++) {
            if(i == 0) sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
            if(i != 0) {
                if(sb.charAt(i-1) == ' ') {
                    sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
                }
            }
        }
        answer = sb.toString();
        return answer;
    }
}