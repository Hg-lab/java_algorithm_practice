package 프로그래머스.같은_숫자는_싫어;

import java.util.*;


public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        ArrayList<Integer> answerList = new ArrayList<Integer>();

        for(int i = 0; i < arr.length-1; i++) {
            if(arr[i] != arr[i+1]) {
                answerList.add(arr[i]);
            }
        }

        answerList.add(arr[arr.length-1]);

        answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}