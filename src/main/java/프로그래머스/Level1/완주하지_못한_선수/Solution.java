package 프로그래머스.Level1.완주하지_못한_선수;
import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        // 1 <= participant.length <= 100000
        // completion.length = participant.lenth-1
        // participant 원소 중복가능, 1~20 자리 소문자 알파벳

        Arrays.sort(participant);
        Arrays.sort(completion);

        // System.out.println("participant  "+ Arrays.toString(participant));
        // System.out.println("completion  "+ Arrays.toString(completion));

        // System.out.println("completion  "+ Arrays.toString(completion));


        for(int participantIdx = 0; participantIdx < participant.length-1; participantIdx++) {
            if(!participant[participantIdx].equals(completion[participantIdx])
                    && participantIdx < completion.length) {

                System.out.println("participant = "+participant[participantIdx]);
                System.out.println("completion = "+completion[participantIdx]);
                System.out.println(participant[participantIdx].equals(completion[participantIdx]));

                answer = participant[participantIdx];
                return answer;
            }
        }
        answer = participant[participant.length-1];

        return answer;
    }
}