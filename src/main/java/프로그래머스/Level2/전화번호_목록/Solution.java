package 프로그래머스.Level2.전화번호_목록;

import java.util.*;
// 접두어가 있으면 false
// 없으면 true
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        for(int i = 0; i < phone_book.length-1; i++) {
            if(phone_book[i+1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return true;
    }
}