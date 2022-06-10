package 프로그래머스.크레인_인형뽑기_게임;

import java.util.*;

// return: 터트려서 사라진 인형의 개수

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> bucket = new Stack<Integer>();

        // moves를 loop돌아 x축 몇번째 칸에서 뽑을지 정한다
        for(int midx = 0; midx < moves.length; midx++) {
            // board 2차원 배열에서 위에서부터 moves칸에 있는 인형이 있다면 옮길 작업을 한다.
            for(int k = 0; k < board.length; k ++) {
                if(board[k][moves[midx]-1] != 0) {
                    int onCrain = board[k][moves[midx]-1];
                    board[k][moves[midx]-1] = 0;
                    if((!bucket.empty()) && bucket.peek() == onCrain) {
                        bucket.pop();
                        answer += 2;
                        break;
                    } else {
                        bucket.push(onCrain);
                        break;
                    }
                }
            }
        }

        return answer;
    }
}