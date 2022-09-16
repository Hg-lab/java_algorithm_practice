package 프로그래머스.Level2.하노이의_탑;

import java.util.*;

class Solution {
    List<int[]> answerList = new ArrayList<>();
    public int[][] solution(int n) {
        int[][] answer = {};

        // n 홀수이면 [1,3]
        // n 짝수이면 [1,2]
        hanoi(n, 1, 3, 2);
        answer = new int[answerList.size()][2];
        for(int i = 0; i < answer.length; i++) {
            for(int j = 0; j < 2; j++) {
                answer[i][j] = answerList.get(i)[j];
            }
        }

        return answer;
    }

    private void move(int movingObject, int from, int to) {
        answerList.add(new int[]{from, to});
        return ;
    }

    private void hanoi(int movingObject, int from, int to, int via) {
        if(movingObject == 1) {
            move(movingObject, from, to);
            return;
        }
        hanoi(movingObject-1, from, via, to);
        move(movingObject, from, to);
        hanoi(movingObject-1, via, to, from);

    }
}