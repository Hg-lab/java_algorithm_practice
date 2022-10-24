package 프로그래머스.Level2.괄호_회전하기;

import java.util.*;

class Solution {
    Character[] arr = {'[','(','{',']',')','}'};
    Set<Character> openSet = new HashSet<>(Arrays.asList(arr).subList(0,3));
    Set<Character> closeSet = new HashSet<>(Arrays.asList(arr).subList(3,6));

    public int solution(String s) {
        int answer = 0;

        for(int i = 0; i < s.length(); i++) {
            if(check(s)) ++answer;

            s = s.substring(1,s.length()) + s.substring(0,1);
        }

        return answer;
    }

    private boolean check(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(stack.empty() && !isOpen(c)) return false;

            if(isOpen(c)) stack.push(c);
            else if(stack.peek() == arr[Arrays.asList(arr).indexOf(c) - 3]) stack.pop();
        }

        if(stack.empty()) return true;

        return false;
    }

    private boolean isOpen(char c) {
        if(openSet.contains(c)) return true;
        return false;
    }
}