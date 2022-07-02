package 프로그래머스.Level1.로또의_최고_순위와_최저_순위;
import java.util.Arrays;
// lottos 내의 0은 낙서된 번호
// lottos.length == win_nums.lengt == 6
// win_nums = 1 ~ 45
// 정렬안되어있음
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        Arrays.sort(lottos);
        Arrays.sort(win_nums);

        int zero = 0;// zero: 낙서 수
        for(int lotto : lottos) {
            if(lotto == 0) {
                zero++;
            }
        }
        System.out.println("zero = "+zero);
        int correct = 0;// correct
        for(int i = 0; i < lottos.length; i++) {
            for(int j = 0; j < win_nums.length; j++) {
                if(lottos[i] == win_nums[j]) {
                    correct++;
                }
            }
        }
        System.out.println("correct = "+correct);

        // 6,0 -> 0개맞거나 6개맞거나, 5,0 -> 0개맞거나, 다섯개맞거나
        int maxCorr = 0, minCorr = 0;
        if((correct + zero) > 6 ) maxCorr = 6;
        else maxCorr = correct + zero;
        minCorr = correct;
        answer[0] = 7-maxCorr;
        answer[1] = 7-minCorr;

        for(int i = 0; i<answer.length; i++) {
            if(answer[i] == 7) answer[i] = 6;
        }

        return answer;
    }
}