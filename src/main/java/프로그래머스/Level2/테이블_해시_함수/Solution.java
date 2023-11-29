package 프로그래머스.Level2.테이블_해시_함수;

import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = -1;

        Arrays.sort(data, (d1, d2) -> {
            if(d1[col-1] == d2[col-1]) return d2[0] - d1[0];
            else return d1[col-1] - d2[col-1];
        });

        for(int i = row_begin-1; i < row_end; i++) {
            int hashRes = hash(data[i], i+1);
            if(answer == -1) {
                answer = hashRes;
                continue;
            }
            answer ^= hashRes;
        }

        return answer;
    }

    private int hash(int[] arr, int idx) {
        int res = 0;
        for(int i = 0; i < arr.length; i++) {
            res += arr[i] % idx;
        }
        return res;
    }
}