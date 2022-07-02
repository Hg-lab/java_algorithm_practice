package 프로그래머스.Level1.음양_더하기;

class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 123456789;

        // 1 <= absolutes.length == signs.length <= 1000

        int signIdx = 0;
        int sum = 0;

        for(boolean sign : signs) {

            if(!sign){
                absolutes[signIdx] *= -1;
            }
            signIdx++;
        }

        for(int realNum : absolutes){
            sum += realNum;
        }

        answer = sum;
        return answer;
    }
}