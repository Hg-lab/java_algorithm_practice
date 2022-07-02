package 프로그래머스.Level2.기능개발;
import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        int[] workingDays = new int[progresses.length];
        int day = 0;
        ArrayList<Integer> answerList = new ArrayList<Integer>();
        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < progresses.length; i++) {
            workingDays[i] = (100 - progresses[i])/speeds[i];
            if((100 - progresses[i])%speeds[i] != 0) workingDays[i]++;

            System.out.println(workingDays[i]);
        }

        for(int i = 0; i < workingDays.length; i++) {
            if(q.isEmpty()) {
                q.offer(workingDays[i]);
                continue;
            }

            if(!q.isEmpty() && q.peek() >= workingDays[i]) {
                q.offer(workingDays[i]);
            }

            if(!q.isEmpty() && q.peek() < workingDays[i]) {
                while(!q.isEmpty()) {
                    q.poll();
                    day++;
                }
                answerList.add(day);
                day = 0;
                q.offer(workingDays[i]);
            }
        }

        while(!q.isEmpty()) {
            q.poll();
            day++;
        }

        answerList.add(day);

        answer = answerList.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }
}