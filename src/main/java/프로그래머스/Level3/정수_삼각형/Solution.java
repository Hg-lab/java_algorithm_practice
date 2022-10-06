package 프로그래머스.Level3.정수_삼각형;

import java.util.*;
//0,0 -> 1,0 / 1,1
//1,0 -> 2,0 / 2,1
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < triangle.length; i++)
            list.add(new int[triangle[i].length]);

        list.get(0)[0] = triangle[0][0];
        //next = row + 1, col = previous Col or prev col + 1

        for(int row = 1; row < triangle.length; row++) {
            int sum = 0;
            int[] arr = new int[triangle[row].length];
            for(int col = 0; col < triangle[row].length; col++) {
                if(col == 0) {
                    arr[col] = list.get(row-1)[col] + triangle[row][col];
                    continue;
                } else if (col == triangle[row].length-1) {
                    arr[col] = list.get(row-1)[col-1] + triangle[row][col];
                    continue;
                }
                int sum1 = list.get(row-1)[col-1] + triangle[row][col];
                int sum2 = list.get(row-1)[col] + triangle[row][col];
                arr[col] = Math.max(sum1,sum2);
            }
            list.set(row, arr);
        }

        int max = Integer.MIN_VALUE;
        for(int n : list.get(triangle.length-1))
            max = Math.max(max, n);

        return answer = max;
    }
}