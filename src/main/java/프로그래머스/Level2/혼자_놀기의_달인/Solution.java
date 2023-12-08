package 프로그래머스.Level2.혼자_놀기의_달인;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;

        for(int i = 0; i < cards.length; i++) {
            answer = Math.max(answer, getPoint(new boolean[cards.length], cards, i, 1));
        }

        return answer;
    }

    private int getPoint(boolean[] checked, int[] cards, int idx, int groupNo) {
        int res = 0;
        while(!checked[idx]) {
            checked[idx] = true;
            idx = cards[idx] - 1;
            res++;
        }

        if(groupNo == 2) return res;

        int group2 = 0;
        for(int i = 0; i < cards.length; i++) {
            if(checked[i]) continue;
            group2 = Math.max(group2, getPoint(checked, cards, i, 2));
        }

        return res * group2;

    }
}