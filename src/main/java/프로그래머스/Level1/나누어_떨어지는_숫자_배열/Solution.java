package 프로그래머스.Level1.나누어_떨어지는_숫자_배열;
import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {};

        Arrays.sort(arr);
        boolean canDivide = false;

        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < arr.length; i++) {
            if((arr[i] % divisor) == 0) {
                list.add(arr[i]);
                System.out.println(arr[i]);
                canDivide = true;
            }
        }

        if(!canDivide) list.add(-1);

        answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++) answer[i] = list.get(i);


        return answer;
    }
}