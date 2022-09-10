package 프로그래머스.Level2.다음_큰_숫자;

class Solution {
    public int solution(int n) {
        int answer = 0;
        String binN = Integer.toBinaryString(n);
        answer = n;

        while(!checkCondition(n, answer)) {
            answer++;
        }

        return answer;
    }

    private boolean checkCondition(int n, int answer) {
        if(n >= answer) return false;
        String binAnswer = Integer.toBinaryString(answer);
        String binN = Integer.toBinaryString(n);
        if(getNumberOf1(binAnswer) != getNumberOf1(binN)) return false;
        return true;
    }

    private int getNumberOf1(String n) {
        int count = 0;
        for(int i = 0; i < n.length(); i++) {
            if(n.charAt(i) == '1') {
                ++count;
            }
        }
        return count;
    }
}