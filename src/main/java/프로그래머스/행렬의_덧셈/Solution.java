package 프로그래머스.행렬의_덧셈;

import java.util.*;

class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int xLength = arr1[0].length;
        int yLength = arr1.length;

        int[][] answer = new int[yLength][xLength];


        for(int i = 0; i < yLength; i++) {
            for(int j = 0; j < xLength; j++){
                answer[i][j] = arr1[i][j] + arr2[i][j];
            }
        }

        return answer;
    }
}