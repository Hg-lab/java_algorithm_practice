package 프로그래머스.Level1._2016년;

class Solution {
    public String solution(int a, int b) {
        String answer = "";

        final String[] WEEK = {"THU","FRI","SAT","SUN","MON","TUE","WED"};
        final int[] MONTH_DAYS = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int sumDays = 0;
        for(int m = 0; m < a-1; m++) {
            if(a == 1) break;
            sumDays += MONTH_DAYS[m];
        }
        sumDays += b;

        int index = sumDays % 7;

        return answer = WEEK[index];
    }
}