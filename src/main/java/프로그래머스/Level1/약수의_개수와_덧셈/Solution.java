package 프로그래머스.Level1.약수의_개수와_덧셈;
class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        int sum = 0;

        for(int num = left; num <= right; num++) {
            int numSqrt = (int)Math.sqrt(num);
            System.out.println(numSqrt + "^2 = " + (int)Math.pow(numSqrt,2) + "/ num = "+ num);
            System.out.println((int)Math.pow(numSqrt,2) == num);
            System.out.println("sum = "+sum);
            if((int)Math.pow(numSqrt,2) == num) {
                sum -= num;
                continue;
            }
            sum += num;
        }

        //1 ≤ left ≤ right ≤ 1,000

        answer = sum;
        return answer;
    }
}