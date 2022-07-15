package 프로그래머스.Level2.문자열_압축;

import java.util.*;
// aabbaccc -> 2a2ba3c
// ababcdcdababcdcd -> 2ab2cd2ab2cd -> 2ababcdcd
// abcabcdede -> abcabc2de -> 2abcde
// 몇개의 문자열 단위로 자르는지는 고정임
// 1 <= s.length() <= 1000
// s is only lowercases
class Solution {
    public int solution(String s) {
        int answer = s.length();
        int cutNum = 1;
        int duplNum = 0;


        Stack<String> stk = new Stack<>();
        StringBuilder answerSb = new StringBuilder();

        while(cutNum <= s.length()/2) {
            for(int i = 0; i + cutNum <= s.length(); i = i + cutNum) {
                // i ~ i + cutNum 만큼 잘라서 비교한 뒤 그 다음부터 루프 돌아야함.
                String comparedStr = s.substring(i,i + cutNum);

                if((!stk.empty() && !stk.peek().equals(comparedStr))) {
                    String duplicatedString = stk.peek();
                    stk.clear();
                    if(duplNum != 1){
                        answerSb.append(Integer.toString(duplNum));
                    }
                    answerSb.append(duplicatedString);
                    duplNum = 0;
                }


                if(stk.empty() || stk.peek().equals(comparedStr)) {
                    stk.push(comparedStr);
                    duplNum++;
                }
            }

            if(!stk.empty()) {
                if(stk.size() != 1) answerSb.append(stk.size());
                answerSb.append(stk.peek());
                stk.clear();
            }

            if(s.length() % cutNum != 0) {
                answerSb.append(s.substring(s.length() - s.length()%cutNum));
            }


            answer = Math.min(answer,answerSb.length());
            cutNum++;
            answerSb = new StringBuilder();
            duplNum = 0;



        }

        return answer;
    }
}