package 프로그래머스.Level1.숫자_문자열과_영단어;
// 1 <= s.length() <= 50
//  s[0] != 0 || zero
// 1 <= return <= 2,000,000,000

import java.util.*;
import java.util.regex.Pattern;

class Solution {
    public int solution(String s) {
        int answer = 0;
        String regexAlphabet = "^[a-zA-Z]$";
        String regexNumber = "^[0-9]$";
        String answerString = "";
        String[] numDict = {"zero", "one", "two", "three",
                "four", "five", "six", "seven", "eight", "nine"};
        List<String> numDictList = Arrays.asList(numDict);

        for(int i = 0; i < s.length(); i++) {
            // 알파벳인 경우
            if(Pattern.matches(regexAlphabet, s.substring(i,i+1))) {
                int k = i+1;
                while(numDictList.indexOf(s.substring(i,k)) < 0 && k < s.length()) {
                    k++;
                    if(numDictList.indexOf(s.substring(i,k)) > -1) {
                        // int -> String 형변환
                        answerString += Integer.toString(numDictList.indexOf(s.substring(i,k)));
                    }
                }
            } else {
                // 숫자인 경우 그냥 추가
                answerString += s.substring(i,i+1);
            }
        }

        System.out.println(answerString);

        // String -> int 형변환
        answer = Integer.parseInt(answerString);

        return answer;
    }
}
