package 프로그래머스.Level2.조이스틱;

class Solution {
    public int solution(String name) {
        int answer = 0;

        int length = name.length();
        int move = length-1;
        for(int i = 0; i < length; i++) {
            int upDown = Math.min(name.charAt(i) - 'A', 26 - name.charAt(i) + 'A');
            answer += upDown;

            // next
            int next = i+1;
            while(next < length && name.charAt(next) == 'A') {
                next++;
            }

            move = Math.min(move, i*2 + length - next);
            move = Math.min(move, (length - next) * 2 + i);
        }

        return answer + move;
    }
}