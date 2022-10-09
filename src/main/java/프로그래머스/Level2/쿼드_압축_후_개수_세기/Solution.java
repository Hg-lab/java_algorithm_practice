package 프로그래머스.Level2.쿼드_압축_후_개수_세기;
import java.util.*;

class Solution {
    int[][] arr;
    boolean[][] dupl;
    public int[] solution(int[][] arr) {
        this.arr = arr;
        this.dupl = new boolean[arr.length][arr[0].length];
        int[] answer = new int[2];
        recurse(0, arr.length, 0, arr.length);

        for(int i = 0; i < dupl.length; i++) {
            for(int j = 0; j < dupl[0].length; j++) {
                if(dupl[i][j] == true) continue;
                if(arr[i][j] == 0) ++answer[0];
                else if(arr[i][j] == 1) ++answer[1];
            }
        }

        return answer;
    }

    private void recurse(int rowStart, int rowEnd, int colStart, int colEnd) {
        if(rowStart == rowEnd || colStart == colEnd) return;

        int target = arr[rowStart][colStart];
        for(int r = rowStart; r < rowEnd; ++r) {
            for(int c = colStart; c < colEnd; ++c) {
                if(target != arr[r][c]) {
                    recurse(rowStart, (rowStart+rowEnd)/2, colStart, (colStart+colEnd)/2);
                    recurse(rowStart, (rowStart+rowEnd)/2, (colStart+colEnd)/2, colEnd);
                    recurse((rowStart+rowEnd)/2, rowEnd, colStart, (colStart+colEnd)/2);
                    recurse((rowStart+rowEnd)/2, rowEnd, (colStart+colEnd)/2, colEnd);
                    return;
                }
                target = arr[r][c];
            }
        }

        for(int r = rowStart; r < rowEnd; ++r) {
            for(int c = colStart; c < colEnd; ++c) {
                dupl[r][c] = true;
            }
        }
        dupl[rowStart][colStart]=false;
    }
}