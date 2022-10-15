package 프로그래머스.Level2.주식가격;

import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

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

class Solution2 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        // prices[i] compare to prices[k]
        for(int i = 0; i < prices.length; i++) {
            int count = 0;
            for(int k = i + 1; k < prices.length; k++) {
                count++;
                if(prices[i] > prices[k]) break;
            }
            if(i == prices.length-1) count = 0;
            answer[i] = count;
        }
        return answer;
    }
}