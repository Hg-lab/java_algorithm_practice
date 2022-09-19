package 프로그래머스.Level2.이진_변환_반복하기;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        StringBuilder sb = new StringBuilder(s);
        int zeroCount = 0;
        int count = 0;

        while(!sb.toString().equals("1")) {
            int ones = 0;
            for(int i = 0; i < sb.length(); i++) {
                if(sb.charAt(i) == '1') ++ones;
            }
            zeroCount += sb.length() - ones;
            ++count;
            sb = new StringBuilder(Integer.toBinaryString(ones));
        }

        answer[0] = count;
        answer[1] = zeroCount;

        return answer;
    }
}