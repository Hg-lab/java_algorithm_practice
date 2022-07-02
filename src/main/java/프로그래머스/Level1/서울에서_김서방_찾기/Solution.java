package 프로그래머스.Level1.서울에서_김서방_찾기;

import java.util.*;

class Solution {
    public String solution(String[] seoul) {
        String answer = "";
        int idx = Arrays.asList(seoul).indexOf("Kim");

        answer = "김서방은 " + Integer.toString(idx) + "에 있다";

        return answer;
    }
}