package 프로그래머스.Level2.점_찍기;

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        for(int a = 0; a * k <= d; a++) {
            int x = a * k;
            double y = getY(x, d);
            answer += (int)(y/(double)k);
            answer += 1;
        }
        return answer;
    }

    private double getY(int x, int d) {
        return Math.sqrt(Math.pow(d,2) - Math.pow(x, 2));
    }
}