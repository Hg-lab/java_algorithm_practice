package others.문자열_괄호값;
/*
* 우리가 흔히 사용하고 있는 괄호는 (), {}, [], 3종류이다.
* 이 괄호로 올바르게 짝이 지워진 문자열이 있다.
* 이 문자열에 대한 괄호 값을 다음과 같이 정의한다
* () = 2, {} = 3, [] = 5
* (){}[] = 2 + 3 + 5 = 10
* [(){}][] = 5 * (2 + 3) + 5 = 30
* */

import java.util.*;

class Solution {
    Map<Character, Integer> map;

    public void mapInit() {
        this.map = new HashMap<>();
        map.put('(', 2);
        map.put(')', 2);
        map.put('{', 3);
        map.put('}', 3);
        map.put('[', 5);
        map.put(']', 5);
    }

    public int solution(String brackets) {
        mapInit();
        Stack<Character> stack = new Stack<>();

        int answer = 0;
        int temp = 1;
        char prev = ' ';

        for(int i = 0; i < brackets.length(); i++) {
            char c = brackets.charAt(i);
            if(!isClose(c)) {
                temp *= map.get(c);
                stack.push(c);
                prev = c;
                continue;
            }

            if(!stack.isEmpty()) {
                if(getOpposite(c) == stack.peek()) {
                    stack.pop();
                    if(!isClose(prev)) answer += temp;
                    temp /= map.get(c);
                    prev = c;
                }
            }
        }

        if(!stack.isEmpty()) return -1;

        return answer;
    }

    public boolean isClose(char c) {
        Set<Character> openBracket = new HashSet<>();
        openBracket.add(')');
        openBracket.add('}');
        openBracket.add(']');

        if(openBracket.contains(c)) return true;
        else return false;
    }

    public char getOpposite(char c) {
        if(c == ')') return '(';
        if(c == '}') return '{';
        if(c == ']') return '[';

        return ' ';
    }
}