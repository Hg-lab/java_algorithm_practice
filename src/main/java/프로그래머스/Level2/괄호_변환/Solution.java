package 프로그래머스.Level2.괄호_변환;

import java.util.*;


class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("()))((()");
    }
}

class Solution {
    boolean[] checked;
    String p;
    public String solution(String p) {
        this.p = p;
        String answer = "";
        boolean[] checked = new boolean[p.length()];
        Stack<Character> stack = new Stack<>();
        if(isProperBracket(p, stack)) return answer = p;

        answer = sliceRecursion(p);
        return answer;
    }

    private String sliceRecursion(String p) {
        // 1
        if(p.equals("")) return "";

        // 2
        String u = ""; String v = "";
        for(int i = 1; i <= p.length(); i++) {
            if(isBalanced(p.substring(0,i))) {
                u = p.substring(0,i);
                v = p.substring(i);
                break;
            }
        }

        // 3-1
        if(isProperBracket(u, new Stack<>())) return u + sliceRecursion(v);
        // 4
        StringBuilder sb = new StringBuilder();
        // 4-1
        sb.append("(");
        // 4-2
        sb.append(sliceRecursion(v));
        // 4-3
        sb.append(")");
        // 4-4
        sb.append(getReverse(u.substring(1, u.length()-1)));
        // 4-5
        return sb.toString();
    }

    private String getReverse(String s) {
        char[] charArrayS = s.toCharArray();
        for(int i = 0; i < charArrayS.length; i++) {
            if(charArrayS[i] == '(') charArrayS[i] = ')';
            else if(charArrayS[i] == ')') charArrayS[i] = '(';
        }
        return new String(charArrayS);
    }

    private boolean isBalanced(String s) {
        char[] charArrayS = s.toCharArray();
        int openingNum = 0; int closingNum = 0;
        for(char bracket : charArrayS) {
            if(bracket == '(') ++openingNum;
            if(bracket == ')') ++closingNum;
        }
        if(openingNum == closingNum) return true;
        return false;
    }

    private boolean isProperBracket(String p, Stack<Character> stack) {
        char[] charArrayP = p.toCharArray();
        if(charArrayP[0] == ')') return false;
        for(int i = 0; i < charArrayP.length; i++) {
            if(charArrayP[i] == '(') {
                stack.push(charArrayP[i]);
            }
            if(!stack.isEmpty() && stack.peek() == '(' && charArrayP[i] == ')') {
                stack.pop();
            }
        }
        if(stack.isEmpty()) return true;
        return false;
    }
}
