package 프로그래머스.Level2.최댓값과_최솟값;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] numArr = new String[s.length()];
        numArr = s.split(" ");

        int minNum = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE;

        for(int i = 0; i < numArr.length; i++) {
            minNum = Math.min(minNum, Integer.parseInt(numArr[i]));
            maxNum = Math.max(maxNum, Integer.parseInt(numArr[i]));
        }

        answer = Integer.toString(minNum) + " " + Integer.toString(maxNum);

        return answer;
    }
}