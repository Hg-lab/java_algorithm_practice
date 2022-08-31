package 프로그래머스.Level2.두_큐_합_같게_만들기;
import java.util.Queue;
import java.util.LinkedList;

public class Solution_1 {

        public int solution(int[] queue1, int[] queue2) {
            int answer = 0;
            // Integer[] queueInteger1 = Arrays.stream(queue1).boxed().toArray(Integer[]::new);
            Queue<Integer> q1 = new LinkedList<>();
            Queue<Integer> q2 = new LinkedList<>();

            long sum1 = 0, sum2 = 0; int maxNum = 0;
            for(int i = 0; i < queue1.length; i++) {
                q1.offer(queue1[i]);
                q2.offer(queue2[i]);
                sum1 += queue1[i];
                sum2 += queue2[i];

                maxNum = Math.max(maxNum,Math.max(queue1[i],queue2[i]));
            }

            if(maxNum > (sum1+sum2)/2) return answer = -1;

            int countCycle = 0;
            while(true) {
                if(countCycle >= queue1.length*3) return -1;
                if(sum1 == sum2) break;
                if(sum1 > sum2) {
                    sum1 -= q1.peek();
                    sum2 += q1.peek();
                    q2.offer(q1.poll());
                    ++answer;
                    ++countCycle;
                    continue;
                }
                if(sum1 < sum2) {
                    sum2 -= q2.peek();
                    sum1 += q2.peek();
                    q1.offer(q2.poll());
                    ++answer;
                    ++countCycle;
                }
            }

            return answer;
        }

}
