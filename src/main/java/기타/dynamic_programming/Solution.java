package 기타.dynamic_programming;

import java.util.Stack;

// 개미 수열 문제
// parameters: int n
// return: String s - n 번째 수열
public class Solution {
    public static void main(String[] args) {
        System.out.println("1 = " + solution2(1));
        System.out.println("11 = " + solution2(2));
        System.out.println("12 = " + solution2(3));
        System.out.println("1121 = " + solution2(4));
//        System.out.println("122111 = " + solution(5));
//        System.out.println("112213 = " + solution(6));
//        System.out.println("12221131 = " + solution(7));
    }

    public static String solution(int n) {
        String[] dp = new String[n+1];
        dp[0] = "";
        dp[1] = "1";
        for(int i = 1; i < n; i++) {
            dp[i+1] = count(dp[i]);
        }
        return dp[n];
    }
    private static String count(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(!stack.empty() && stack.peek() != s.charAt(i))  {
                int cnt = 0;
                char c = ' ';
                while(!stack.empty()) {
                    c = stack.pop();
                    cnt++;
                }
                sb.append(c);
                sb.append(Integer.toString(cnt));
            }

            if(stack.empty() || stack.peek() == s.charAt(i)) {
                stack.push(s.charAt(i));
            }
        }

        if(!stack.empty()) {
            int cnt = 0;
            char c = ' ';
            while(!stack.empty()) {
                c = stack.pop();
                cnt++;
            }
            sb.append(c);
            sb.append(Integer.toString(cnt));
        }

        return sb.toString();
    }

    public static String solution2(int n) {
        String[] dp = new String[n+1];
        dp[0] = "";
        dp[1] = "1";
        for(int i = 1; i < n; i++) {
            dp[i+1] = count2(dp[i]);
        }
        return dp[n];
    }

    private static String count2(String s) {
        char previous = ' ';
        int count = 0;
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(previous != ' ' && previous != c) {
                result.append(String.valueOf(previous) + String.valueOf(count));
                count = 1;
            } else {
                count++;
            }
            previous = c;
        }
        result.append(String.valueOf(previous) + String.valueOf(count));
        return result.toString();
    }
}
