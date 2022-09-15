package 프로그래머스.Level2.주식가격;

import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        // for(int i = 0; i < prices.length-1; i++) {
        //     for(int future = i; future < prices.length-1; future++) {
        //         if(prices[i] <= prices[future]) answer[i]++;
        //         else break;
        //     }
        // }

        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < prices.length; i++) {
            while(!stack.isEmpty()) {
                if(prices[stack.peek()] > prices[i]){
                    answer[stack.peek()] = i - stack.peek();
                    stack.pop();
                } else break;
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            answer[stack.peek()] = prices.length - 1 - stack.peek();
            stack.pop();
        }

        return answer;
    }
}