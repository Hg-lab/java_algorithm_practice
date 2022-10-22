package 프로그래머스.Level2.숫자_블록;

class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end-begin)+1];
        for(long i = begin; i <= end; i++) {
            answer[(int)(i-begin)] = getNum(i);
        }
        return answer;
    }

    private int getNum(long n) {
        if(n == 1) return 0;
        for(long i = 2; i <= Math.sqrt(n) ; i++) {
            if(n % i == 0 && n/i <= 10000000) return (int)(n/i);
        }
        return 1;
    }
}