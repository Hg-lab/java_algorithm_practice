package 프로그래머스.K번째수;
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        System.out.println("commands.length = " + commands.length);

        int answerIdx = 0;
        for(int[] command : commands){
            int[] copiedArray = {};
            copiedArray = Arrays.copyOfRange(array,command[0]-1,command[1]);

            Arrays.sort(copiedArray);
            System.out.println(Arrays.toString(copiedArray));
            int answerNum = copiedArray[command[2]-1];

            answer[answerIdx] = answerNum;
            answerIdx += 1;
        }

        return answer;
    }

}