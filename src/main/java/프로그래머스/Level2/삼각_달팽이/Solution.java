package 프로그래머스.Level2.삼각_달팽이;

import java.util.*;

class Solution {
    public int[] solution(int n) {
        int max = n*(n+1)/2;
        int[] answer = new int[max];
        int[][] tri = new int[n][];
        for(int i = 0; i < n; ++i) tri[i] = new int[i+1];

        int row = 0; int col = 0; int input = 1;
        tri[0][0] = 1;
        while(input < max) {
            while(row < n-1 && tri[row+1][col] == 0) {
                tri[++row][col] = ++input;
            }

            while(col < n-1 && tri[row][col+1] == 0) {
                tri[row][++col] = ++input;
            }

            while(row-1 > 0 && col-1 > 0 && tri[row-1][col-1] == 0) {
                tri[--row][--col] = ++input;
            }
        }

        int index = 0;
        for(int i = 0; i < tri.length; i++) {
            for(int j = 0; j < tri[i].length; j++)
                answer[index++] = tri[i][j];
        }


        return answer;
    }
}