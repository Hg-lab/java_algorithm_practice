package 프로그래머스.Level2.n_2_배열_자르기;

class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)right - (int)left + 1];

        int index = 0;
        for(long i = left; i <= right; i++) {
            long row = i % (long)n;
            long col = i / (long)n;
            answer[index++] = Math.max((int)row,(int)col) + 1;
        }

        return answer;
    }
}