package 프로그래머스.Level2.전화번호_목록;

import java.util.*;

class Solution_Hash {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Set<String> set = new HashSet<>(Arrays.asList(phone_book));

        for(String p : phone_book) {
            for(int i = 1; i < p.length(); i++) {
                if(set.contains(p.substring(0,i))) return false;
            }
        }
        return true;
    }
}
