package 프로그래머스.Level2._3차_n진수_게임;

import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        int totalLength = 0;
        int i = 0;
        List<Character> list = new ArrayList<>();
        while(totalLength < t * m) {

            String s = toNotationNum(i++, n);
            // String s = Integer.toString(i++, n);
            s = s.toUpperCase();
            for(char sChar: s.toCharArray()) {
                list.add(sChar);
            }
            totalLength += s.length();
        }

        int index = p - 1;
        while(answer.length() < t) {
            answer += list.get(index);
            index += m;
        }

        return answer;
    }

    private String toNotationNum(int i, int n) {
        return Integer.toString(i, n);
//         StringBuilder sb = new StringBuilder();
//         Map<Integer,String> map = new HashMap<>();
//         for(int k = 'A'; k <= 'F'; ++k) {
//             map.put(k-55, String.valueOf((char)k));
//         }
//         while(i > 0) {
//             String x = String.valueOf(i%n);
//             if(Integer.parseInt(x) >= 10) x = map.get(Integer.parseInt(x));
//             sb.insert(0,x);
//             i /= n;
//         }

//         return sb.toString();
    }
}