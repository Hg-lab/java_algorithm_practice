package 프로그래머스.Level2.뒤에_있는_큰_수_찾기;

import java.util.*;

class Main {
    public static void main(String[] args) {
        int[] solution = new Solution().solution(new int[]{9, 1, 5, 3, 6, 2});
    }
}

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = numbers.length - 1; i > 0; i--) {
            if (i == numbers.length - 1) {
                stack.push(numbers[i]);
                answer[i] = -1;
                continue;
            }

            if (numbers[i] < stack.peek()) {
                answer[i] = stack.peek();
            } else {
                while (!stack.isEmpty()) {
                    stack.pop();
                    if(stack.isEmpty()) {
                        stack.push(numbers[i]);
                        answer[i] = -1;
                        break;
                    }
                    if (numbers[i] < stack.peek()) {
                        answer[i] = stack.peek();
                        break;
                    }
                }
            }
            if(numbers[i] > numbers[i-1]) stack.push(numbers[i]);


        }

        if(numbers[0] < stack.peek()) {
            answer[0] = stack.peek();
        } else {
            while (!stack.isEmpty()) {
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(numbers[0]);
                    answer[0] = -1;
                    break;
                }
                if (numbers[0] < stack.peek()) {
                    answer[0] = stack.peek();
                    break;
                }
            }

        }

        return answer;
    }
}

class Solution_Refactored {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = numbers.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && numbers[i] >= stack.peek()) {
                stack.pop();
            }

            answer[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(numbers[i]);
        }

        return answer;
    }
}
