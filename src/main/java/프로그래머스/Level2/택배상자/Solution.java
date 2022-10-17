package 프로그래머스.Level2.택배상자;

import java.util.*;



class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int n = order.length;
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        int o = 0;
        for(int box = 1; box <= n; box++) {
            stack.add(box);
            while(!stack.isEmpty()) {
                if(stack.peek() == order[o]) {
                    result.add(stack.pop());
                    ++o;
                } else break;
            }
        }

        return answer = result.size();
    }
}