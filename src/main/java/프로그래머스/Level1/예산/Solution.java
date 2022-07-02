package 프로그래머스.Level1.예산;
import java.util.Arrays;
// 최대한 많은 부서지원 필요
// 신청한 금액만큼 모두 지원해주어야함, 적은금액은 지원 불가
// d: 부서별로 신청한 금액, budget: 예산
// return: 최대 몇개 부서 지원?
// 1 <= d.length <= 100
// 1 <= d[i] <= 100,000
// 1 <= budget <= 10,000,000 int
class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;

        Arrays.sort(d);

        int sum = 0;

        for(int i = 0; i < d.length; i++) {
            System.out.println("i = "+i+"// sum = "+sum);
            sum += d[i];
            if(sum > budget) {
                sum -= d[i];
                break;
            } else answer++;
        }

        return answer;
    }
}