package 프로그래머스.짝지어_제거하기.Solution_stack;

import java.util.*;
// return : 0 or 1
// "baabaa" -> "bbaa" -> "aa" -> ""
// 1,000,000
class Solution
{
    public int solution(String s)
    {
        int answer = -1;

        Stack<String> stk = new Stack<String>();
        boolean same = false;


        int i = 0;
        while(i < s.length()) {
            if(stk.isEmpty()) {
                stk.push(Character.toString(s.charAt(i)));
                i++;
                continue;
            }
            if(stk.peek().equals(Character.toString(s.charAt(i)))) {
                stk.pop();
                i++;
                continue;
            } else {
                stk.push(Character.toString(s.charAt(i)));
                i++;
            }

        }


        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.

        System.out.println(stk.isEmpty());
        if(stk.isEmpty()) answer = 1;
        else answer= 0;

        return answer;
    }
}