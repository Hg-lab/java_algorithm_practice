package 프로그래머스.Level2.행렬_테두리_회전하기;

import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] matrix = new int[rows][columns];
        List<Integer> answerList = new ArrayList<>();

        // make matrix
        int number = 1;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = number++;
            }
        }
        for(int[] query : queries) {
            int[][] rotatingMatrix = new int[matrix.length][matrix[0].length];
            for(int i = 0; i < rotatingMatrix.length; i++) {
                rotatingMatrix[i] = matrix[i].clone();
            }
            int upperLimit = query[0]-1;
            int lefterLimit = query[1]-1;
            int lowerLimit = query[2]-1;
            int righterLimit = query[3]-1;
            int min = rows*columns;
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[i].length; j++) {
                    // moveRight
                    if(i == upperLimit && lefterLimit <= j && j < righterLimit) {
                        rotatingMatrix[i][j+1] = matrix[i][j];
                        min = Math.min(min, matrix[i][j]);
                        continue;
                    }
                    // moveDown
                    if(j == righterLimit && upperLimit <= i && i < lowerLimit) {
                        rotatingMatrix[i+1][j] = matrix[i][j];
                        min = Math.min(min, matrix[i][j]);
                        continue;
                    }
                    // moveLeft
                    if(i == lowerLimit && lefterLimit < j && j <= righterLimit ) {
                        rotatingMatrix[i][j-1] = matrix[i][j];
                        min = Math.min(min, matrix[i][j]);
                        continue;
                    }
                    // moveUp
                    if(j == lefterLimit && upperLimit < i && i <= lowerLimit ) {
                        rotatingMatrix[i-1][j] = matrix[i][j];
                        min = Math.min(min, matrix[i][j]);
                        continue;
                    }
                }
            }
            matrix = rotatingMatrix;
            answerList.add(min);
        }
        answer = answerList.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}