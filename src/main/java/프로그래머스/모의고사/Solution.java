package 프로그래머스.모의고사;
import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};

        int[] frst = {1,2,3,4,5};
        int[] scnd = {2,1,2,3,2,4,2,5};
        int[] thrd = {3,3,1,1,2,2,4,4,5,5};

        int[] solved = {0,0,0};

        // 0:0 1:1 4:4 5:0
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == frst[i%5]) solved[0]++;
        }
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == scnd[i%8]) solved[1]++;
        }
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == thrd[i%10]) solved[2]++;
        }

        int maxSolved = Arrays.stream(solved).max().getAsInt();
        ArrayList<Integer> preAnswer = new ArrayList<Integer>();

        for(int i = 0; i < solved.length; i++) {
            if(solved[i] == maxSolved) {
                preAnswer.add(i+1);
            }
        }

        answer = new int[preAnswer.size()];

        for(int i = 0; i < answer.length; i++) {
            answer[i] = (int)preAnswer.get(i);
        }

        Arrays.sort(answer);

        return answer;
    }
}
